package com.tecsun.sisp.net.export.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtil {
	public static void main(String args[]) {
		  
        
	}
	
	/**
	 * 创建横向多级表头
	 * @param header 表头结构，采用二维数组表示，按表格的空间结构从上到下从左到右排列，顺序不可颠倒
	 * +--------+-------+--------+
	 * |    1   |   2   |   3    |
	 * |--------+-------+--------|
	 * |    4   |   5   |   6    |
	 * +--------+-------+--------+
	 * 如果按以上的表头，header中数组的顺序应该为{{1,x,x,x}，{2,x,x,x}，{3,x,x,x}，{4,x,x,x}，{5,x,x,x}，{6,x,x,x}} 
	 * header中元素结构：{显示的值，当前节点ID,在第几行展示，父节点ID}。当前节点ID用所在数组下标标识
	 * @param sheet xlsx的sheet对象
	 * @param index 从第几行开始创建
	 * @param cellStyle 单元格样式
	 * @return
	 */
	private static void createHeader(String[][] header,XSSFSheet sheet,int index,XSSFCellStyle cellStyle){  
        //获取表头深度  
        int deep = getDeep(header);  
        //根据表头的深度创建行  
        List<XSSFRow> headerRows = new ArrayList<XSSFRow>();  
        for(int i =0;i<deep;i++){  
            XSSFRow headerRow = sheet.createRow((short) index+i);  
            headerRow.setHeight((short) 400);  
            headerRows.add(headerRow);  
        }  
        List<ExcelHeader> TableHeaderNodes = arrayToList(header);  
          
        //创建单元格  
        for(int i=0;i<TableHeaderNodes.size();i++){  
            ExcelHeader TableHeaderNode = TableHeaderNodes.get(i);  
            int level = TableHeaderNode.getLevel();  
            int myLeafCount = TableHeaderNode.getMyLeafCount();  
            int frontLeafCount = TableHeaderNode.getFrontLeafCount();  
            //sheet.setColumnWidth((short)frontLeafCount, (short)7000);  
            sheet.setColumnWidth(i,(short)(TableHeaderNode.getTitle().getBytes().length+4)*256);
            XSSFCell headerCell =   
                headerRows.get(level-1).createCell((short)frontLeafCount);  
            headerCell.setCellStyle(cellStyle);  
            headerCell.setCellValue(new XSSFRichTextString(TableHeaderNode.getTitle()));  
            if(TableHeaderNode.isLeaf()){  
                //为叶子节点  
                //向下合并单元格  
                CellRangeAddress region = new CellRangeAddress(index+level-1,index+deep-1,frontLeafCount,frontLeafCount);  
                sheet.addMergedRegion(region);  
            }else{  
                //为非叶子节点  
                //向右合并单元格  
            	CellRangeAddress region = new CellRangeAddress(index+level-1,index+level-1,frontLeafCount,frontLeafCount + myLeafCount-1);  
                sheet.addMergedRegion(region);  
            }  
        }
    }  
      
      
      
    /** 
     * 数组转化list集合 
     * @param header 
     * @return 
     */  
    private static List<ExcelHeader> arrayToList(String[][] header){  
        List<ExcelHeader> TableHeaderNodes = new ArrayList<ExcelHeader>();  
        for(String[] TableHeaderNode : header){  
            //获取此节点下的所有叶子节点数量  
            int count = getMyLeafNodeCount(TableHeaderNode,header);  
            int frontCount = getFrontLeafNodeCount(TableHeaderNode,header);  
            TableHeaderNodes.add(new ExcelHeader(TableHeaderNode[0], Integer.valueOf(TableHeaderNode[1]), Integer.valueOf(TableHeaderNode[2]), Integer.valueOf(TableHeaderNode[3]),count,isLeafNode(TableHeaderNode,header),frontCount));  
        }  
        return TableHeaderNodes;  
    }  
    /** 
     * 获取level层级下的所有表头节点 
     * @param header 
     * @param level 
     * @return 
     */  
    /*private static List<TableHeaderNode> arrayToListByLevel(String[][] header,int level){  
        List<TableHeaderNode> TableHeaderNodes = new ArrayList<TableHeaderNode>();  
        for(String[] TableHeaderNode : header){  
            if(TableHeaderNode[2].equals(level+"")){  
                //获取此节点下的所有叶子节点数量  
                int count = getMyLeafNodeCount(TableHeaderNode,header);  
                int frontCount = getFrontLeafNodeCount(TableHeaderNode,header);  
                TableHeaderNodes.add(new TableHeaderNode(TableHeaderNode[0], Integer.valueOf(TableHeaderNode[1]), Integer.valueOf(TableHeaderNode[2]), Integer.valueOf(TableHeaderNode[3]),count,isLeafNode(TableHeaderNode,header),frontCount));  
            }  
        }  
        return TableHeaderNodes;  
    }*/  
      
      
    /** 
     * 获取当前节点下的所有叶子节点数量 
     * @param TableHeaderNode 
     * @param header 
     * @return 
     */  
    private static int getMyLeafNodeCount(String[] TableHeaderNode,String[][] header){  
        int count = 0;  
        for(int i=0;i<header.length;i++){  
            if(header[i][3].equals(TableHeaderNode[1])){  
                if(isLeafNode(header[i], header)){  
                    count++;  
                }else{  
                    count += getMyLeafNodeCount(header[i], header);  
                }  
            }  
        }  
        return count;  
    }  
      
    /** 
     * 判断是否为叶子节点 
     * @param TableHeaderNode 
     * @param header 
     * @return 
     */  
    private static boolean isLeafNode(String[] TableHeaderNode,String[][] header){  
        for(int i=0;i<header.length;i++){  
            if(header[i][3].equals(TableHeaderNode[1])){  
                return false;  
            }  
        }  
        return true;  
    }  
      
      
    /** 
     * 获取当前节点空间顺序之前的所有叶子节点数量 
     * @param TableHeaderNode 
     * @param header 
     * @return 
     */  
    private static int getFrontLeafNodeCount(String[] TableHeaderNode,String[][] header){  
        int count = 0 ;  
        for(int i=0;i<header.length;i++){  
            if(header[i].equals(TableHeaderNode)){  
                break;  
            }  
            if(isLeafNode(header[i], header)){  
                count++;  
            }  
        }  
        return count;  
    }  
      
    private static int getDeep(String[][] header){  
        int deep = 0;  
        for(int i=0;i<header.length;i++){  
            if(Integer.parseInt(header[i][2])>deep){  
                deep = Integer.parseInt(header[i][2]);  
            }  
        }  
        return deep;  
    }

	@SuppressWarnings("unchecked")
	private static <T> int writeRow(List<T> list, String[] fields,
			XSSFSheet spreadsheet, int deep, XSSFCellStyle cellStyleForRow) {
		for(int i = 0,leni = list.size(); i < leni; i++){
			XSSFRow row= spreadsheet.createRow(deep++);
			Map<String, Object> t = (Map<String, Object>) list.get(i);
			for(short j = 0; j < fields.length; j++){
				XSSFCell cell = row.createCell(j);  
                cell.setCellStyle(cellStyleForRow);  
                String fieldName = fields[j];  
                try{  
                    Object value =t.get(fieldName);
                    String textValue = null;  
                    /*if (value instanceof Integer) {  
						int intValue = (Integer) value;  
                     	cell.setCellValue(intValue);  
					} else if (value instanceof Float) {  
                     	float fValue = (Float) value;  
                     	textValue = new XSSFRichTextString(  
                     	String.valueOf(fValue)).getString();  
                     	cell.setCellValue(textValue);  
					} else if (value instanceof Double) {  
                     	double dValue = (Double) value;  
                     	textValue = new XSSFRichTextString(  
                     	String.valueOf(dValue)).getString();  
                     	cell.setCellValue(textValue);  
					} else if (value instanceof Long) {  
                     	long longValue = (Long) value;  
                     	cell.setCellValue(longValue);  
					}  */
                    if (value instanceof Date){  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                        textValue = sdf.format(date);  
                    }/*else if (value instanceof byte[]){  
                        // 有图片时，设置行高为60px;  
                        row.setHeightInPoints(60);  
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算  
                        sheet.setColumnWidth(i, (short) (35.7 * 80));  
                        // sheet.autoSizeColumn(i);  
                        byte[] bsValue = (byte[]) value;  
                        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0,  
                                1023, 255, (short) 6, index, (short) 6, index);  
                        anchor.setAnchorType(2);  
                        patriarch.createPicture(anchor, workbook.addPicture(  
                                bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));  
                    }  */
                    else if (value == null){  
                    	textValue ="";  
                    }else{  
                        // 其它数据类型都当作字符串简单处理  
                        textValue = value.toString();  
                    }  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null){  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches()){  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }else{  
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);  
                            /*XSSFFont font3 = workbook.createFont();  
                            font3.setColor(XSSFColor.BLUE.index);  
                            richString.applyFont(font3); */ 
                            int len = richString.getString().getBytes().length;
                            if((short)len*256 > spreadsheet.getColumnWidth(j)){
                            	spreadsheet.setColumnWidth(j,(short)(len+2)*256);
                            }
                            cell.setCellValue(richString);  
                        }  
                    } 
                }catch(Exception e){
                	e.printStackTrace();
                }
			}
		}
		return deep;
	}
	
	public static <T> XSSFWorkbook doExport(Iterator<List<Map<String, Object>>> iterator,String[][] header,String[] fields,String title){
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(title);
		
		XSSFCellStyle cellStyleForHeader = workbook.createCellStyle();//只能通过workbook获取样式模板，不能使用new关键字，否则会报错
		XSSFFont font = workbook.createFont();    
		font.setFontName("宋体");    
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		font.setFontHeightInPoints((short) 12);    
		    
		cellStyleForHeader.setFont(font);//选择需要用到的字体格式  
		cellStyleForHeader.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyleForHeader.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		cellStyleForHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleForHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleForHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleForHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
		ExcelUtil.createHeader(header,spreadsheet,0,cellStyleForHeader);
		
		int deep = ExcelUtil.getDeep(header);
		XSSFCellStyle cellStyleForRow = workbook.createCellStyle();//只能通过workbook获取样式模板，不能使用new关键字，否则会报错
		cellStyleForRow.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyleForRow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		cellStyleForRow.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleForRow.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleForRow.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleForRow.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
		//cellStyleForRow.setWrapText(true);//自动换行
		
		while(iterator.hasNext()){
			List<Map<String, Object>> list=iterator.next();
			deep=ExcelUtil.writeRow(list,fields,spreadsheet,deep,cellStyleForRow);
		}
		return workbook;
	}
	
	
	public static <T> XSSFWorkbook doExport_(Iterator<T> iterator,String[][] header,String[] fields,String title) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(title);
		
		XSSFCellStyle cellStyleForHeader = workbook.createCellStyle();//只能通过workbook获取样式模板，不能使用new关键字，否则会报错
		XSSFFont font = workbook.createFont();    
		font.setFontName("宋体");    
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		font.setFontHeightInPoints((short) 12);    
		    
		cellStyleForHeader.setFont(font);//选择需要用到的字体格式  
		cellStyleForHeader.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyleForHeader.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		cellStyleForHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleForHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleForHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleForHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
		ExcelUtil.createHeader(header,spreadsheet,0,cellStyleForHeader);
		
		int deep = ExcelUtil.getDeep(header);
		XSSFCellStyle cellStyleForRow = workbook.createCellStyle();//只能通过workbook获取样式模板，不能使用new关键字，否则会报错
		cellStyleForRow.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyleForRow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		cellStyleForRow.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleForRow.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleForRow.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleForRow.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
		//cellStyleForRow.setWrapText(true);//自动换行
		
		while(iterator.hasNext()){
			List<T> list=(List<T>) iterator.next();
			//将T转map
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
			//将T转map
			for(T t:list){
				Map<String,Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) MapBeanConvert.objectToMap(t);
				listMap.add(map);
			}
			
			deep=ExcelUtil.writeRow_(listMap,fields,spreadsheet,deep,cellStyleForRow);
		}
		return workbook;
	}
	
	public static <T> void doExport2_(Iterator<T> iterator,ExcelCell excelCell,String[] fields,String title,HttpServletResponse response) throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(title);
		XSSFCellStyle cellStyleForHeader = workbook.createCellStyle();//只能通过workbook获取样式模板，不能使用new关键字，否则会报错
		XSSFFont font = workbook.createFont();    
		font.setFontName("宋体");    
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		font.setFontHeightInPoints((short) 12);    
		    
		cellStyleForHeader.setFont(font);//选择需要用到的字体格式  
		cellStyleForHeader.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyleForHeader.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		cellStyleForHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleForHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleForHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleForHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
		//写表头
		excelCell.wirteHeader(sheet,excelCell,cellStyleForHeader);
		
		int deep = excelCell.getTotalRowNumber();
		
		XSSFCellStyle cellStyleForRow = workbook.createCellStyle();//只能通过workbook获取样式模板，不能使用new关键字，否则会报错
		cellStyleForRow.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		cellStyleForRow.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		cellStyleForRow.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框    
		cellStyleForRow.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框    
		cellStyleForRow.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框    
		cellStyleForRow.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框
		//cellStyleForRow.setWrapText(true);//自动换行
		
		while(iterator.hasNext()){
			List<T> list=(List<T>) iterator.next();
			List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
			//将T转map
			for(T t:list){
				Map<String,Object> map = new HashMap<String, Object>();
				map = (Map<String, Object>) MapBeanConvert.objectToMap(t);
				listMap.add(map);
			}
			
			deep=ExcelUtil.writeRow_(listMap,fields,sheet,deep,cellStyleForRow);
		}
		
		/**
		 * 导出到页面操作
		 */
		response.setCharacterEncoding("UTF-8");
    	response.setHeader("Content-Disposition", "attchement;filename=" + new String(title.getBytes("UTF-8"),"iso-8859-1")+".xlsx");
    	response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		OutputStream out;
		try {
			out = response.getOutputStream();
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> int writeRow_(List<T> list, String[] fields,
			XSSFSheet spreadsheet, int deep, XSSFCellStyle cellStyleForRow) {
		for(int i = 0,leni = list.size(); i < leni; i++){
			XSSFRow row= spreadsheet.createRow(deep++);
			Map<String, Object> t = (Map<String, Object>) list.get(i);
			for(short j = 0; j < fields.length; j++){
				XSSFCell cell = row.createCell(j);  
                cell.setCellStyle(cellStyleForRow);  
                String fieldName = fields[j];  
                try{  
                    Object value =t.get(fieldName);
                    String textValue = null;  
                    if (value instanceof Date){  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                        textValue = sdf.format(date);  
                    }
                    else if (value == null){  
                    	textValue ="";  
                    }else{  
                        // 其它数据类型都当作字符串简单处理  
                        textValue = value.toString();  
                    }  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null){  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches()){  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }else{  
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);  
                            int len = richString.getString().getBytes().length;
                            if((short)len*256 > spreadsheet.getColumnWidth(j)){
                            	spreadsheet.setColumnWidth(j,(short)(len+2)*256);
                            }
                            cell.setCellValue(richString);  
                        }  
                    } 
                }catch(Exception e){
                	e.printStackTrace();
                }
			}
		}
		return deep;
	}
	
}
