package com.tecsun.sisp.fakamanagement.modules.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
* @ClassName: ExcelUtils 
* @Description: TODO(处理Excel文件) 
* @author huangtao
* @date 2017年7月10日 上午10:33:21 
*
 */
public class ExcelUtils {
	
	/**
	 * 判断excel文件版本
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getExcelType(String filePath) {
		String type="";
		if(filePath.matches("^.+\\.(?i)(xlsx)$")){
			type="2007";
		}else if (filePath.matches("^.+\\.(?i)(xls)$")) {
			type="2003";
		}
        return type;
    }
	
	/**
	 * 转换数据为String
	 * @param cell 
	 * @return
	 */
	public static String getValue(Cell cell){
		if(cell==null){
    		return "";
    	}else{
	        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {//Boolean类型
	            return String.valueOf(cell.getBooleanCellValue());
	        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {//Number类型
	        	double d=cell.getNumericCellValue();
	        	//如果直接tostring，数组大的将会转为科学计数法 1.0E10 所以需要format
	        	DecimalFormat df=new DecimalFormat("#");
	        	return df.format(d);
	        } else {
	            return String.valueOf(cell.getStringCellValue());
	        }
    	}
	}
	
	/**
	 * 获取Excel工作蒲
	 * @param filepath 文件路径
	 * @return
	 */
	public static Workbook getExcel(String filepath) {
		String filetype=getExcelType(filepath);
		Workbook workbook=null;
		 File file = new File(filepath);  
		 FileInputStream is;
		try {
			is = new FileInputStream(file);
			if (filetype.equals("2003")) {//xls文件
				workbook=new HSSFWorkbook(is);
			}else if (filetype.equals("2007")) {//xlsx文件 暂未支持
			}else {//其他类型的文件 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	/**
	 * 处理Excel的数据 将一行转换为一个map 将map存放在list里面
	 * @param wb Excel工作蒲
	 * @return 
	 */
	public static List<Map<String, String>> EecelToList(Workbook wb) {
		List<Map<String, String>> list=new ArrayList<>();
		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {//获取每个sheet
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            Row headerRow=sheet.getRow(0);//获取表头
            if (headerRow==null) {
            	continue;
			}
            String[] headers=new String[headerRow.getLastCellNum()];//创建一个数组存放表头 用于map的key
            for (int i = 0; i <headerRow.getLastCellNum(); i++) {
				headers[i]=getValue(headerRow.getCell(i));
			}
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {//获取数据行
            	Map<String, String> map=new HashMap<>();
				Row row=sheet.getRow(rowNum);
				if (row==null) {
	            	continue;
				}
				for (int i = 0; i <row.getLastCellNum(); i++) {
					map.put(headers[i], getValue(row.getCell(i)));//将数据存放到map里面 key为表头的值
				}
				list.add(map);
			}
		}
		return list;
	}
	
	public static void addHeader(String[] headers,HSSFSheet sheet) {
		HSSFRow hrow=sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell=hrow.createCell(i);
			cell.setCellValue(headers[i]);
		}
	}
	
	public static void addData(int row,int cell,String data,HSSFSheet sheet) {
		row=row+1;
		HSSFRow hrow=sheet.getRow(row);
		if (null==hrow) {
			hrow=sheet.createRow(row);
		}
		HSSFCell hcell=hrow.getCell(cell);
		if(null==hcell){
			hcell=hrow.createCell(cell);
		}
		hcell.setCellValue(data);
	}

}
