package com.tecsun.sisp.iface.server.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.util.JsonMapper;
import com.tecsun.sisp.iface.common.vo.BaseVO;
import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.employment.param.job.JobBasicVo;
import com.tecsun.sisp.iface.common.vo.faceverify.ResultVerify;

public class JobUtil {

	private static Logger logger = LoggerFactory.getLogger(JobUtil.class);
	
	/**
	 * 对《孝感自助一体机前后端交互接口.doc》接口返回值进行 数据格式 转换
	 * 
	 * @param json
	 */
	public static Result convertValue(String json) {
		try {
			JsonMapper mapper = new JsonMapper(Inclusion.ALWAYS);
			Map jsonMap = mapper.fromJson(json, Map.class);

			boolean ok = (boolean) (jsonMap.get("IsOK").equals(true));
			Integer i = (Integer) jsonMap.get("RowCount");
			String msg = (String) jsonMap.get("Message");
			if (ok && i > 0) {
				return doConvert(mapper, jsonMap);
			} else if(ok && i == 0) {
				msg = "请求成功，但没有查到数据";
			}
			return new ResultVerify(Constants.RESULT_MESSAGE_ERROR, msg, null, 0);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVerify(Constants.RESULT_MESSAGE_EXCEPTION, "抱歉，数据转换出错了",null, 0);
		}
	}

	/**
	 * 把数据格式转换成 {"statusCode":"200","message":"XXX","data":XXX,"total":0} 形式
	 * <p>
	 * 并对个别字符进行 Escape 解码
	 */
	private static Result doConvert(JsonMapper mapper, Map jsonMap) throws IOException {
		
		String msg = (String) jsonMap.get("Message");
		Integer i = (Integer) jsonMap.get("RowCount");
		
		
		LinkedHashMap<String, Object> tempMap = null;
		Object data = null;
		Integer total = 0;
		
		//
		jsonMap.remove("IsOK");
		jsonMap.remove("RowCount");
		jsonMap.remove("Message");
		
		Map<String, Object> resultMap= (Map<String, Object>) jsonMap.get("Result");
		ArrayList<LinkedHashMap<String,Object>> rowsList = (ArrayList<LinkedHashMap<String,Object>>) resultMap.get("rows");
		
		if(rowsList==null || "null".equals(rowsList)||rowsList.isEmpty()){//没有rows元素，则取 Result
			tempMap = new LinkedHashMap<String, Object>();
			Set<String> keySet = resultMap.keySet();
			for (String key : keySet) {
				Object value = resultMap.get(key);
				if (value instanceof String) {
					String s = decode(value);
					tempMap.put(key, s);
				}else {
					tempMap.put(key, value);
				}
			}
			resultMap.remove("Result");
			data = tempMap;
			total = i;
		}else{
			ArrayList<LinkedHashMap<String,Object>> tempList = new ArrayList<LinkedHashMap<String,Object>>();
			Object obj_total = resultMap.get("total");
			total = (Integer) (obj_total!=null?obj_total:1);
			
			for (LinkedHashMap<String, Object> rowMap : rowsList) {
				tempMap = new LinkedHashMap<String, Object>();
				Set<String> keySet = rowMap.keySet();
				for (String key : keySet) {
					Object value = rowMap.get(key);
					if (value instanceof String) {
						String s = decode(value);
						tempMap.put(key, s);
					}else {
						tempMap.put(key, value);
					}
				}
				tempList.add(tempMap);
			}
			rowsList = null;
			resultMap.remove("rows");
			data = tempList;
		}
		
		return new ResultVerify(Constants.RESULT_MESSAGE_SUCCESS, msg, data, total);
	}

	/**
	 * Escape解码
	 */
	public static String decode(Object value) {
		String s = (String) value;
		if (!StringUtils.isBlank(s) && s.contains("%")) {
			return Escape.unescape(s);
		}
		return s;
	}
	
	/**
	 * 进行简单的值转换
	 * 
	 * @param json
	 * @return
	 */
	public static Result convertValue_simple(String json) {
		try {
			JsonMapper mapper = new JsonMapper(Inclusion.ALWAYS);
			Map jsonMap = mapper.fromJson(json, Map.class);

			boolean ok = (boolean) (jsonMap.get("IsOK").equals(true));
			Integer i = (Integer) jsonMap.get("RowCount");
			String msg = (String) jsonMap.get("Message");
			
			if (ok && i > 0) {
				return doConvert__simple(mapper, jsonMap);
			} 
			return new ResultVerify(Constants.RESULT_MESSAGE_ERROR, msg, null, i);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVerify(Constants.RESULT_MESSAGE_EXCEPTION, "抱歉，数据转换出错了",null, 0);
		}
	}
	
	public static Result doConvert__simple(JsonMapper mapper, Map jsonMap) {
		String msg = (String) jsonMap.get("Message");
		Integer i = (Integer) jsonMap.get("RowCount");
		jsonMap.remove("IsOK");
		jsonMap.remove("Message");
		jsonMap.remove("RowCount");
		return new ResultVerify(Constants.RESULT_MESSAGE_SUCCESS, msg, jsonMap, i);
	}

	/**
	 * 公共方法，把非空的前端参数值添加到 JsonObject 中，作请求的查询参数用.<p>
	 * 
	 * 类似于做很多如下操作：<p>
	 * jo.addProperty(key, value);<p>
	 * jo.addProperty(key, value);<p>
	 * jo.addProperty(key, value);<p>
	 * jo.addProperty(key, value);<p>
	 * jo.addProperty(key, value);<p>
	 * jo.addProperty(key, value);<p>
	 * ......
	 * 
	 * @param param	JobBasicVo及其子类，所以要使用此方法的Vo都应该继承 JobBasicVo 
	 * @return
	 * @throws Exception
	 */
	public static JsonObject constructParam(JobBasicVo param) {
		try {
			JsonObject jo = new JsonObject();
			Class<?> clazz = param.getClass();
			// 除本身的属性外，父类的属性也加载
			for (; clazz != BaseVO.class; clazz = clazz.getSuperclass()) {
				try {
					Field[] fields = clazz.getDeclaredFields();
					for (Field field : fields) {
						jo = addJsonObjectValueFromField(jo,field, param);
					}
				} catch (Exception e) {

				}
			}
			return jo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("构造请求参数时出错了", e);
			return null;
		}
	}


	/**
	 * 把 参数值添加到 JsonObject 中</p>
	 * 
	 * 类似 jo.addProperty(key, value);
	 */
	private static JsonObject addJsonObjectValueFromField(JsonObject jo,Field field,Object param) throws IllegalArgumentException, IllegalAccessException{
		
		field.setAccessible(true);
		String filedName = field.getName();
		Object object = field.get(param);
		
		//这里没有做类型的判断
		//TODO.....
		
		if(object !=null){
			jo.addProperty(filedName.toUpperCase(), String.valueOf(object));
		}
        
		return jo;
	}
	
	/**
	 * 公共的非空校验</p> 
	 * 
	 * 排序字段、排序方式、页码、每页行数
	 * 
	 * @param param
	 * @return
	 */
	public static Result checkParamIsEmpty(JobBasicVo param) {
		String msg = "";
		if (StringUtils.isBlank(param.getPxzd())) {
			msg = "排序字段（pxzd）不能为空";
		} else if (StringUtils.isBlank(param.getPxfs())) {
			msg = "排序方式(pxfs)不能为空";
		} else if (param.getYm() < 1) {
			msg = "页码(ym)是整数，且大于0";
		} else if (param.getMyhs() < 1) {
			msg = "每页行数(myhs),大于0";
		} else {
			return new Result(Constants.RESULT_MESSAGE_SUCCESS, null, null);
		}
		return new Result(Constants.RESULT_MESSAGE_ERROR, msg, null);
	}

}
