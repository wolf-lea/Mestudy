package com.tecsun.sisp.net.modules.controller.business;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tecsun.sisp.net.common.Constants;
import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.export.PageIterator;
import com.tecsun.sisp.net.export.excel.ExcelCell;
import com.tecsun.sisp.net.export.excel.ExcelUtil;
import com.tecsun.sisp.net.modules.BaseController;
import com.tecsun.sisp.net.modules.entity.business.BusinessHandle;
import com.tecsun.sisp.net.modules.entity.business.BusinessHandleExportVo;
import com.tecsun.sisp.net.modules.service.business.BusinessHandleService;
import com.tecsun.sisp.net.modules.service.business.impl.BusinessHandleExportService;

@Controller
@RequestMapping("/net/business")
public class BusinessHandleController extends BaseController {

	@Autowired
	private BusinessHandleService businessHandleService;
	
	@Autowired
	private BusinessHandleExportService businessHandleExportService;
	
	/**
	 * 分页查询
	 * @param sxmc
	 * @param sxbm
	 * @param bjbh
	 * @param sfzh
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getHandlePage",method=RequestMethod.GET)
	@ResponseBody
	public Object getBusinessHandlePageByCondition(
			@RequestParam(value="sxmc",required=false)String sxmc,
			@RequestParam(value="sxbm",required=false)String sxbm,
			@RequestParam(value="bjbh",required=false)String bjbh,
			@RequestParam(value="sfzh",required=false)String sfzh,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="20")Integer pageSize
			){
		Page<BusinessHandle> data = businessHandleService.getBusinessHandlePageByCondition(sxmc,sxbm,bjbh,sfzh,pageNo,pageSize);
		
		return result(Constants.RESULT_MESSAGE_SUCCESS,"success",data);
	}
	
	@RequestMapping(value="/exportExcelByCondition",method=RequestMethod.POST)
	@ResponseBody
	public Object exportExcelByCondition(
			@RequestBody BusinessHandleExportVo businessHandleExportVo,
			HttpServletRequest request,HttpServletResponse response
			) throws Exception{
		
		String title = businessHandleExportVo.getTitle();
		ExcelCell excelCell = businessHandleExportVo.getExcelCell();
		BusinessHandle businessHandle = businessHandleExportVo.getBusinessHandle();
		String[] fields = businessHandleExportVo.getFields();
		if(StringUtils.isEmpty(title)){
			title="导出的excel";
		}
		
		long starts = System.currentTimeMillis();
		//导出全部(设置0)
		PageIterator<BusinessHandle> iterator = new PageIterator<BusinessHandle>(businessHandleExportService,businessHandle,0);
//		XSSFWorkbook workbook = ExcelUtil.doExport_(iterator, header, fields, title);
//		excelCell = new ExcelCell();
//		excelCell.setName("测试标题");
//		excelCell.setColspan(6);
//		excelCell.setRowspan(2);
//		excelCell.setCruuentRowIndex(0);
//		excelCell.setTotalRowNumber(3);
//		excelCell.setWirteColIndex(0);
//		
//		ExcelCell excelCell21 = new ExcelCell();
//		excelCell21.setName("列1");
//		excelCell21.setColspan(1);
//		excelCell21.setCruuentRowIndex(2);
//		excelCell21.setTotalRowNumber(3);
//		excelCell21.setWirteColIndex(0);
//		
//		ExcelCell excelCell22 = new ExcelCell();
//		excelCell22.setName("列2");
//		excelCell22.setColspan(1);
//		excelCell22.setCruuentRowIndex(2);
//		excelCell22.setTotalRowNumber(3);
//		excelCell22.setWirteColIndex(1);
//		
//		ExcelCell excelCell23 = new ExcelCell();
//		excelCell23.setName("列3");
//		excelCell23.setColspan(1);
//		excelCell23.setCruuentRowIndex(2);
//		excelCell23.setTotalRowNumber(3);
//		excelCell23.setWirteColIndex(2);
//		
//		ExcelCell excelCell24 = new ExcelCell();
//		excelCell24.setName("列4");
//		excelCell24.setColspan(1);
//		excelCell24.setCruuentRowIndex(2);
//		excelCell24.setTotalRowNumber(3);
//		excelCell24.setWirteColIndex(3);
//		
//		ExcelCell excelCell25 = new ExcelCell();
//		excelCell25.setName("列5");
//		excelCell25.setColspan(1);
//		excelCell25.setCruuentRowIndex(2);
//		excelCell25.setTotalRowNumber(3);
//		excelCell25.setWirteColIndex(4);
//		
//		ExcelCell excelCell26 = new ExcelCell();
//		excelCell26.setName("列6");
//		excelCell26.setColspan(1);
//		excelCell26.setCruuentRowIndex(2);
//		excelCell26.setTotalRowNumber(3);
//		excelCell26.setWirteColIndex(5);
//		
//		List<ExcelCell> list = new ArrayList<>();
//		list.add(excelCell21);
//		list.add(excelCell22);
//		list.add(excelCell23);
//		list.add(excelCell24);
//		list.add(excelCell25);
//		list.add(excelCell26);
//		
//		excelCell.setNextRow(list);
//		
//		Gson gson = new Gson(); 
//		String jsonStr = gson.toJson(excelCell);
//		System.out.println(jsonStr);
		
		ExcelUtil.doExport2_(iterator, excelCell, fields, title,response);
		
		long ends = System.currentTimeMillis();
		
		System.out.println(ends-starts);
    	
		return result(Constants.RESULT_MESSAGE_SUCCESS,"success");
	}
}
