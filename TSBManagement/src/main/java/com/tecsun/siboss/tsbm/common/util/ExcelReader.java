package com.tecsun.siboss.tsbm.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by huanghailiang on 15-12-29.
 */
public class ExcelReader {

   private static Logger logger = Logger.getLogger(ExcelReader.class);
    /**
     * 创建工作簿对象
     * @param filePath
     * @return
     * @throws IOException
     */
    public static final Workbook createWb(String filePath) throws IOException {

        if(StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("参数错误!!!") ;
        }
        if(filePath.trim().toLowerCase().endsWith("xls")) {
            return new HSSFWorkbook(new FileInputStream(filePath)) ;

        } else if(filePath.trim().toLowerCase().endsWith("xlsx")) {
            return new XSSFWorkbook(new FileInputStream(filePath)) ;

        } else {
            throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!") ;
        }
    }

    public static final Sheet getSheet(Workbook wb ,String sheetName) {
        return wb.getSheet(sheetName) ;
    }

    public static final Sheet getSheet(Workbook wb ,int index) {
        return wb.getSheetAt(index) ;
    }


    public static final List<Object[]> listFromSheet(Sheet sheet) {
        int rowTotal = sheet.getPhysicalNumberOfRows() ;
        logger.info(sheet.getSheetName()+"共有"+rowTotal+"行记录！"); ;
        List<Object[]> list = new ArrayList<Object[]>() ;
        for(int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r) ;
            if(row == null){
                continue ;
            }
            // 不能用row.getPhysicalNumberOfCells()，可能会有空cell导致索引溢出
            // 使用row.getLastCellNum()至少可以保证索引不溢出，但会有很多Null值，如果使用集合的话，就不说了
            Object[] cells = new Object[row.getLastCellNum()] ;
            StringBuilder builder = new StringBuilder();
            for(int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c);

                if(cell == null){
                    continue ;
                }
                cells[c] = getValueFromCell(cell) ;
                builder.append(cells[c]);
            }
            if(builder.toString().length() != 0){
                list.add(cells) ;
            }
            /*if(!"".equals(builder.toString())){
                list.add(cells) ;
            }*/
        }

        return list ;
    }


    /**
     * 获取单元格内文本信息
     * @param cell
     */
    public static final String getValueFromCell(Cell cell) {
        if(cell == null) {
            logger.info("Cell is null !!!"); ;
            return null ;
        }
        String value = null ;
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC :   // 数字
                if(HSSFDateUtil.isCellDateFormatted(cell)) {        // 如果是日期类型
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date) ;
                } else  value = String.valueOf(cell.getNumericCellValue()) ;
                break ;
            case Cell.CELL_TYPE_STRING:     // 字符串
                value = cell.getStringCellValue() ;
                break ;
            case Cell.CELL_TYPE_FORMULA:    // 公式
                // 用数字方式获取公式结果，根据值判断是否为日期类型
                double numericValue = cell.getNumericCellValue() ;
                if(HSSFDateUtil.isValidExcelDate(numericValue)) {   // 如果是日期类型
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date) ;
                } else  value = String.valueOf(numericValue) ;
                break ;
            case Cell.CELL_TYPE_BLANK:              // 空白
                value = "" ;
                break ;
            case Cell.CELL_TYPE_BOOLEAN:            // Boolean
                value = String.valueOf(cell.getBooleanCellValue()) ;
                break ;
            case Cell.CELL_TYPE_ERROR:              // Error，返回错误码
                value = String.valueOf(cell.getErrorCellValue()) ;
                break ;
            default:value = StringUtils.EMPTY ;break ;
        }
        // 使用[]记录坐标
        //return value + "["+cell.getRowIndex()+","+cell.getColumnIndex()+"]" ;
        return  value;
    }

    public static final <T> List<T> getExcelLists(String path,T data,String filed) throws IOException, IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {
        String[] fileds=filed.split(",");
        List<T> listvos=new ArrayList<T>();
        Workbook wb = createWb(path);
        Sheet sheet = getSheet(wb,0);
        List<Object[]> list1 = listFromSheet(sheet);
        for(int i = 2;i< list1.size();i++){
            T data1 = (T) data.getClass().newInstance();
            Object[] objects = list1.get(i);
            //最后一个字段值为空时
            if(fileds.length > objects.length){
                for (int j = 0; j < objects.length; j++) {
                    if(fileds[j] != null && !fileds[j].equals("")) {
                        String objStr = objects[j] == null ? "" : objects[j].toString();
                        setObjctValue(fileds[j].toString(), objStr, data1);
                    }
                }
                setObjctValue(fileds[fileds.length - 1].toString(), "", data1);
            }else {
                for (int j = 0; j < fileds.length; j++) {
                    if(fileds[j] != null && !fileds[j].equals("")) {
                        String objStr = objects[j] == null ? "" : objects[j].toString();
                        setObjctValue(fileds[j].toString(), objStr, data1);
                    }
                }
            }

            listvos.add(data1);
        }

        return listvos;
    }
    public static final <T> void setObjctValue(String filed, Object str, T data) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Class tClass = data.getClass();
        PropertyDescriptor pd = new PropertyDescriptor(filed, tClass);
        Method method = pd.getWriteMethod();
        method.invoke(data, str);
    }
}