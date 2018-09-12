/**
 * 版权所有(C) 广东德生科技有限公司 2013-2020<br>
 * 创建日期 2011-5-14
 */
package com.tecsun.sisp.fakamanagement.modules.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Element;

import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.SpeedOfCardVO;

import oracle.net.aso.e;
import redis.clients.jedis.Jedis;

/**
 * 功能说明：公共类
 *
 * @author wuzhongliang 2014年9月17日
 */
public class PublicMethod {
    protected PublicMethod() {
    }

    /**
     * 获取IP地址
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 模糊查询不同datasource的key
     *
     * @param jedis
     * @param tableName
     * @param unique
     * @param id
     * @return
     * @author wuzhongliang 2014年7月8日
     */
    public static String getKey(Jedis jedis, String source, String tableName, String unique, String id) {
        String key = "";
        String uniqueOrId = isEmptyStr(unique) ? ":id:" + id : unique;
        if (!PublicMethod.isEmptyStr(source)) {
            key = source + ":" + tableName + uniqueOrId;
            return key;
        }
        String pkey = "*" + tableName + uniqueOrId;
        Set<String> set = jedis.keys(pkey);
        Iterator<String> iterator = set.iterator();
        if (iterator.hasNext())
            key = iterator.next();
        return key;
    }

    /**
     * 判断一个字符串是否为空 null 和 空字符
     *
     * @param str String
     * @return Boolean 为空返回True 不为空返回 False
     */
    public static boolean isEmptyStr(Object str) {
        return str == null || str.toString().trim().length() < 1;
    }
    
    public static String getCookie(String key , HttpServletRequest request) {
        String value = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie c : cookies) {
                if (key.equals(c.getName())) {
                    value = c.getValue();
                    break;
                }
            }
        return value;
    }
    
    /**
     * 将一个对象的值转移到另一个对象  属性名要相同
     * @param classes
     * @param object
     * @return
     */
    public static Object createBean(Class<?> classes,Object object) {
		try {
			Class<?> to = (Class<?>) Class.forName(classes.getName());
			Object toob = to.newInstance();
			Class<?> form=object.getClass();
			Field[] formfield=form.getDeclaredFields();
			for (Field field : formfield) {
				field.setAccessible(true);
				try {
					Field tofield = toob.getClass().getDeclaredField(field.getName());
					tofield.setAccessible(true);
					convertValue(tofield, toob, field.get(object));
				} catch (Exception e) {
					try{
						Field tofield = toob.getClass().getSuperclass().getDeclaredField(field.getName());//获取父类属性
						tofield.setAccessible(true);
						convertValue(tofield, toob, field.get(object));
					} catch (Exception e1) {
						System.out.println(field.getName()+"不存在");
					}
				}
			}
			return toob;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
    /**
     * xmlElemnt封装为对象  属性名需要和节点名相同 
     * @param rootElement 父节点
     * @param class1
     * @return
     * @throws Exception 
     */
    public static Object ElementToBean(Element rootElement, Class<?> class1) throws Exception {
		Object toob = class1.newInstance();
		List<Element>elements=rootElement.elements();
		Field[] field=class1.getDeclaredFields();
		for (Element element : elements) {
			String name=element.getName();
			for (Field field2 : field) {
				if(field2.getName().equals(name)){
					field2.setAccessible(true);
					convertValue(field2, toob, element.getText());
				}
			}
		}
    	return toob;
    }
    /**
     * 对象转xml
     * @param object
     * @param rootname
     * @return
     * @throws Exception
     */
    public static String objectToXml(Object object,String rootname) throws Exception {
    		Field[] field=object.getClass().getDeclaredFields();
    		StringBuffer xml=new StringBuffer();
    		xml.append("<"+rootname+">");
    		for (Field field2 : field) {
    			field2.setAccessible(true);
    			xml.append("<"+field2.getName()+">"+field2.get(object)+"</"+field2.getName()+">");
    		}
    		xml.append("</"+rootname+">");
    	return xml.toString();
    }
    
    /**
     * 反射设置实体不同类型字段的值 <暂时只支持 日期 字符串 boolean Integer值设置 待扩建>
     *
     * @param field
     * @param obj
     * @param value
     * @throws Exception
     */
    public static void convertValue(Field field, Object obj, Object valueo) throws Exception {
    	String value=String.valueOf(valueo);
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (field.getGenericType().toString().equals("class java.lang.Integer")) {
            field.set(obj, Integer.parseInt(value));
        } else if (field.getGenericType().toString().equals("boolean")) {
            field.set(obj, Boolean.parseBoolean(value));
        } else if (field.getGenericType().toString().equals("class java.util.Date")) {
            field.set(obj, sim.parse(value));
        } else if(field.getGenericType().toString().equals("double")){
            field.set(obj, Double.parseDouble(value));
        }else  {
            field.set(obj, value);
        }
    }


}
