package com.tecsun.siboss.tsbm.common.util;

import com.tecsun.siboss.tsbm.common.bean.DictVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel导出工具类
 * Created by zhongzhiyong on 15-12-29.
 *
 */
public class ExcelExportUtil {
    /**
     * 导出成excel
     * @param fileName      文件名
     * @param sheetName     sheet名
     * @param titles        标题
     *                      String[] titles = {“id;编号”,"type;维护商类型;TSBM_AP_TYPE",.....};
     *                      说明：字段/属性;标题中文显示内容;字典组编码
     * @param dataList      数据
     * @param response      页面响应
     */
    public static void doExportXls(String fileName, String sheetName, String[] titles, List<?> dataList,
                                   HttpServletResponse response) throws  Exception {

        try {
            if (!dataList.isEmpty()) {
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet(sheetName);
                HSSFRow rowTitle = sheet.createRow(0);
                //excel 列名设置
                for (int i = 0; i < titles.length; i++) {
                    sheet.setColumnWidth(i, 4000);
                    HSSFCell headCell = rowTitle.createCell(i);
                    setCellStyle(workbook, headCell, null);
                    headCell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    headCell.setCellValue(new HSSFRichTextString(titles[i].split(";")[1]));
                }

                //表格内容设置
                Field[] fields = dataList.get(0).getClass().getDeclaredFields();
                int rowNum = 1;
                Map<String, String> dictMap = new HashMap<String, String>();
                for (Object object : dataList) {
                    int cellNum = 0;
                    HSSFRow rowData = sheet.createRow(rowNum); //行
                    for (int x = 0; x < titles.length; x++) {//列
                        String[] proArray = titles[x].split(";");
                        for (Field field : fields) {
                            field.setAccessible(true);
                            if (proArray[0].equals(field.getName())) {
                                //获取列表中英文名字
                                for (int t = 0; t < titles.length; t++) {
                                    String[] tArray = titles[t].split(";");
                                    if (tArray[0].equals(field.getName())) {
                                        if (3 == tArray.length) {//字典翻译
                                            dictMap = getDictGroup(tArray[3]);//bug?
                                        }
                                    }
                                }

                                HSSFCell cell = rowData.createCell(cellNum);
                                setCellStyle(workbook, null, cell);
                                Object cellObj = field.get(object);
                                if (cellObj != null) {
                                    if ("Date".equals(field.getType().getSimpleName())) {
                                        cell.setCellValue(DateFormatUtils.format((Date) cellObj));
                                    }
                                    else if ("Long".equals(field.getType().getSimpleName())) {
                                        cell.setCellValue(Long.parseLong(cellObj.toString()));
                                    }
                                    else if ("Boolean".equals(field.getType().getSimpleName())) {
                                        cell.setCellValue(Boolean.parseBoolean(cellObj.toString()));
                                    }
                                    else if ("Integer".equals(field.getType().getSimpleName()) || "int".equals(field.getType().getSimpleName())) {
                                        if (3 == proArray.length) {//字典翻译解释
                                            cell.setCellValue(dictMap.get(cellObj.toString()));
                                        }
                                        else {
                                            cell.setCellValue(Integer.parseInt(cellObj.toString()));
                                        }
                                    }
                                    else if ("String".equals(field.getType().getSimpleName())) {
                                        if (3 == proArray.length) {//字典翻译解释
                                            cell.setCellValue(dictMap.get(cellObj.toString()));
                                        }
                                        else {
                                            cell.setCellValue(cellObj.toString());
                                        }
                                    }
                                }
                                else {
                                    cell.setCellValue("");
                                }
                                cellNum++;
                                break;
                            }

                        }
                    }
                    rowNum++;
                }
                String fileNameOutPut = fileName + CommUtil.getNowDateLongStr() + ".xls";
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachement;filename=" + URLEncoder.encode(fileNameOutPut, "UTF-8"));
                OutputStream outputStream = response.getOutputStream();
                workbook.write(outputStream);
                outputStream.flush();
                outputStream.close();

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    private static void setCellStyle(HSSFWorkbook workbook, HSSFCell headCell, HSSFCell contentCell) {
        if (null != headCell) {
            HSSFFont headFont = workbook.createFont();
            //headFont.setFontName("宋体");
            //headFont.setFontHeight((short)14);

            HSSFCellStyle headCellStyle = workbook.createCellStyle();
            //headCellStyle.setFont(headFont);
            headCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
            headCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
            headCellStyle.setLocked(true);
            headCellStyle.setWrapText(false);
            headCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            headCell.setCellStyle(headCellStyle);
        }
        if (null != contentCell) {
            HSSFCellStyle columnStyle = workbook.createCellStyle();
            columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
            columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
            columnStyle.setWrapText(false);
            contentCell.setCellStyle(columnStyle);
        }
    }

    private static Map<String, String> getDictGroup(String groupId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            List<DictVO> list = JedisUtil.getDictList(groupId);
            for (DictVO dict : list) {
                map.put(dict.getCode(), dict.getName());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }





}
