package com.tecsun.sisp.fakamanagement.modules.controller.statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.StatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.StatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.statistics.StatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;



@Controller
@RequestMapping(value = "/faka/statistics")
public class StatisticsController extends BaseController {
	
	public final static Logger logger = Logger.getLogger(StatisticsController.class);
	
	@Autowired
	private StatisticsServiceImpl statisticsService;
	@Autowired
	private LoginUserServiceImpl loginUserService;
	
	@RequestMapping(value = "/queryStatisticsCardStore", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardStore(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}
			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			bean.setFkwd(fkwd);
			list = statisticsService.statisticCardStore(bean);
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}
	@RequestMapping(value = "/queryStatisticsCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardReceive(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}
			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			bean.setFkwd(fkwd);
			list=statisticsService.statisticCardReceive(bean);
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}
	@RequestMapping(value = "/queryCurrentStatisticsCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCurrentStatisticsCardReceive(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}

			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startdate=sdf.format(d)+" 00:00:00";
			String enddate=sdf.format(d)+" 23:59:59";
			bean.setbDate(startdate);
			bean.seteDate(enddate);
			bean.setFkwd(fkwd);
			list=statisticsService.statisticCardReceiveTwo(bean);
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}

	@RequestMapping(value = "/queryStatisticsCardretention", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardretention(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}
			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			bean.setFkwd(fkwd);
		    list=statisticsService.statisticCardRetention(bean);
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}
	@RequestMapping(value = "/queryStatisticsCardActivation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardActivation(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}
			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			bean.setFkwd(fkwd);
			list=statisticsService.statisticCardActivation(bean);
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}
	@RequestMapping(value = "/queryStatisticsCardCabinet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardCabinet(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}
			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			bean.setFkwd(fkwd);
			list=statisticsService.statisticCardCabinet(bean);
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}
	@RequestMapping(value = "/queryStatisticsCardProblem", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryStatisticsCardProblem(@RequestBody StatisticsBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		Map<String, String> map=new HashMap<>();
		Integer sum=0;
        List<StatisticsVO> list;
		try {
			nullDate(bean);
			if(StringUtils.isEmpty(bean.getFkwd())){
				return error("当前登录用户id fkwd入参字段不能为空！");
			}
			String fkwd=loginUserService.getFkwd(bean.getFkwd());
			if(StringUtils.isEmpty(fkwd)){
				return error("获取发卡网点编码为空");
			}
			bean.setFkwd(fkwd);
			list=statisticsService.statisticCardProblem(bean);
			if(list.size()==0){
				map.put("1", "0");
				map.put("2", "0");
			}
			for (StatisticsVO statisticsVO : list) {
				//map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		return ok(sum,"成功",list);
	}
	
	private void nullDate(StatisticsBean vo) throws ParseException {
		vo.setStartdate(vo.getStartdate());
		vo.setEnddate(vo.getEnddate());
	}
	
	public String useridToFkwd(StatisticsBean vo) {
		if(null==vo.getFkwd()||vo.getFkwd().equals("")){
			return "";
		}
		String fkwd=loginUserService.getFkwd(vo.getFkwd());
		if(null==fkwd||fkwd.equals("")){
			return "获取发卡网点编码为空";
		}
		vo.setFkwd(fkwd);
		return "";
	}
	
	@RequestMapping(value = "/expStatisticsExcel")
	@ResponseBody
	public void expStatisticsExcel(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		 try {
			 String fkwd= request.getParameter("fkwd");
			 StatisticsBean sbvo=new StatisticsBean();
			 sbvo.setFkwd(fkwd);
			 useridToFkwd(sbvo);
			 nullDate(sbvo);
			 HSSFWorkbook wb=new HSSFWorkbook();
			 HSSFSheet sheet=wb.createSheet("数据");
			 String[] headers=new String[]{"类型","数量"};
			 ExcelUtils.addHeader(headers, sheet);
			 List<StatisticsVO> list=statisticsService.statisticCardStore(sbvo);
			 Integer sum=0;
			 for (StatisticsVO statisticsVO : list) {
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
			 ExcelUtils.addData(1, 0, "入库总量", sheet);
			 ExcelUtils.addData(1, 1, String.valueOf(sum), sheet);
			 list=statisticsService.statisticCardReceive(sbvo);
			 sum=0;
			 for (StatisticsVO statisticsVO : list) {
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
			 ExcelUtils.addData(2, 0, "发卡总量", sheet);
			 ExcelUtils.addData(2, 1, String.valueOf(sum), sheet);
			 list=statisticsService.statisticCardActivation(sbvo);
			 sum=0;
			 for (StatisticsVO statisticsVO : list) {
				 sum+=Integer.parseInt(statisticsVO.getValue());
			 }
			 ExcelUtils.addData(3, 0, "激活总量", sheet);
			 ExcelUtils.addData(3, 1, String.valueOf(sum), sheet);
			 list=statisticsService.statisticCardRetention(sbvo);
			 sum=0;
			 for (StatisticsVO statisticsVO : list) {
				 sum+=Integer.parseInt(statisticsVO.getValue());
			 }
			 ExcelUtils.addData(4, 0, "滞留卡总量", sheet);
			 ExcelUtils.addData(4, 1, String.valueOf(sum), sheet);
			 list=statisticsService.statisticCardProblem(sbvo);
			 Map<String, String> map=new HashMap<>();
			 sum=0;
			 if(list.size()==0){
				map.put("1", "0");
				map.put("2", "0");
			}
			for (StatisticsVO statisticsVO : list) {
				map.put(statisticsVO.getKey(), statisticsVO.getValue());
				sum+=Integer.parseInt(statisticsVO.getValue());
			}
			 ExcelUtils.addData(5, 0, "问题卡总量", sheet);
			 ExcelUtils.addData(5, 1, String.valueOf(sum), sheet);
			 ExcelUtils.addData(7, 0, "问题卡已处理总量", sheet);
			 ExcelUtils.addData(7, 1, map.get("2"), sheet);
			 ExcelUtils.addData(6, 0, "问题卡未处理总量", sheet);
			 ExcelUtils.addData(6, 1, map.get("1"), sheet);
			 String name="ExpRetention_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
			 String path=Config.getInstance().get("exp.temp.path");
			 File file=new File(path);
			 if (!file.exists()) {
				file.mkdirs();
			}
			 FileOutputStream fileOut = new FileOutputStream(path+name);  
			 wb.write(fileOut);  
			 fileOut.close();  
			 InputStream inStream = new FileInputStream(path+name);// 文件的存放路径
            //tomcate 6:response.setContentType("application/vnd.ms-excel,charset=UTF-8");
            response.setContentType("multipart/form-data;charset=UTF-8");//tomcate 7
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }

}
