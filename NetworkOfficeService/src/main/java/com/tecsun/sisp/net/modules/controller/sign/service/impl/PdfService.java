package com.tecsun.sisp.net.modules.controller.sign.service.impl;

import com.lowagie.text.pdf.BaseFont;
import com.tecsun.sisp.net.common.CommUtil;
import com.tecsun.sisp.net.common.Config;
import com.tecsun.sisp.net.common.DictionaryUtil;
import com.tecsun.sisp.net.common.JsonHelper;
import com.tecsun.sisp.net.modules.controller.sign.service.ITemplateService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lorn on 2018/7/17.
 */
@Service("pdfService")
public class PdfService implements ITemplateService {

    private static Logger logger = LoggerFactory.getLogger(PdfService.class);

    /**
     * 获取个人所对应的基本参保信息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, String> getData(Map<String, String> map) throws Exception {

        String preUrl = Config.getInstance().get("signpdf.data.url");

        if(preUrl == null || preUrl.trim().isEmpty()){
            logger.error("个人基本信息获取配置出错：" + preUrl +"\\n");
            throw new Exception("个人基本信息获取配置出错：" + preUrl);
        }

        //内网环境，不需要调用签到接口

        String param = JsonHelper.javaBeanToJson(map);
        //获取个人基本信息
        String url = preUrl + "/sisp/iface/rest/getPersonInfo";
        String jsonData = DictionaryUtil.postClientRequest(param, url);

        Map<String,Object> dataMap = JsonHelper.jsonToMap(jsonData);
        String statusCode = String.valueOf(dataMap.get("statusCode"));
        String message = String.valueOf(dataMap.get("message"));
        if(statusCode == null || !statusCode.equalsIgnoreCase("200" )){
            logger.error("获取个人基本信息出错：" + message +"\\n" + jsonData);
            throw new Exception("获取个人基本信息getPersonInfo出错：" + message);
        }

        dataMap = (HashMap)dataMap.get("data");

        Map<String, String> personInfoMap = new HashMap<>();
        personInfoMap.put("sfzh", String.valueOf(dataMap.get("sfzh")));
        personInfoMap.put("xm", String.valueOf(dataMap.get("xm")));
        personInfoMap.put("dwmc", String.valueOf(dataMap.get("dwmc")));

        //提取参保险种正常的险种
        url = preUrl + "/sisp/iface/rest/getPersonCvrgList";
        jsonData = DictionaryUtil.postClientRequest(param, url);
        dataMap = JsonHelper.jsonToMap(jsonData);
        statusCode = String.valueOf(dataMap.get("statusCode"));
        message = String.valueOf(dataMap.get("message"));
        if(statusCode == null || !statusCode.equalsIgnoreCase("200" )){
            logger.error("获取个人基本信息出错：" + message);
            throw new Exception("获取个人基本信息getPersonCvrgList出错：" + message);
        }

        StringBuilder sb = new StringBuilder();
        dataMap = (HashMap)dataMap.get("data");
        List<HashMap<String,String>> listMap = (List)dataMap.get("data");
        for(HashMap<String,String> map1 : listMap) {
            String cbztValue = map1.get("cbzt");
            String xzlxValue = map1.get("xzlx");
            if(cbztValue != null && !cbztValue.isEmpty() && cbztValue.equalsIgnoreCase("正常参保")){
                if(sb.length() == 0)
                    sb.append(xzlxValue);
                else
                    sb.append("、");

                sb.append(xzlxValue);
            }
        }

        if(sb.length() == 0)
            throw new Exception("没有正常参保的险种");

        personInfoMap.put("xzlx", sb.toString());
        return personInfoMap;
    }

    /***
     * 创建签章PDF文件
     * @param templateName 签章模板文件
     * @param map 签章相关信息参数
     * @return 签章文件下载路径
     * @throws Exception
     */
    public String createSignFile(String templateName, Map<String, String> map) throws Exception {

        if(templateName == null || templateName.isEmpty())
            return null;

        //读取模板文件
        // C:\Users\lorn\Desktop\孝感项目\参考\参保证明模板页面\insurance_proof_model.template
        String templateFilePath = Config.getInstance().get("signpdf.template.path") + templateName + ".template";
        //读取模板文件
        String templateString = readToString(templateFilePath);

        if(templateString == null || templateString.isEmpty()) {
            logger.error("模板文件加载失败");
            return null;
        }

        //读取签章文件
        //"file:///C:/Users/lorn/Desktop/孝感项目/参考/参保证明模板页面/gongzhang.png";
        String signPngPath = Config.getInstance().get("signpdf.template.path") + templateName + ".png";

        File signPngFile = new File(signPngPath);
        if(!signPngFile.exists()){
            logger.error("签章文件加载失败");
            return null;
        }

        if(map == null) {
            logger.error("个人信息为空");
            return null;
        }
        //查询数据
        HashMap<String, String> fieldValueMap = new HashMap<String, String>();
        fieldValueMap.put("xm", map.get("xm"));
        fieldValueMap.put("sfzh", map.get("sfzh"));
        fieldValueMap.put("dwmc", map.get("dwmc"));
        fieldValueMap.put("xzlx", map.get("xzlx"));
        fieldValueMap.put("date", CommUtil.formatDate(new Date(), "yyyy-MM-dd"));
        fieldValueMap.put("gongzhang","file:///" + signPngPath);

        //使用查询数据替换模板中的替换符
        fieldValueMap = replaceHashMap(fieldValueMap);
        for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
            System.out.println("key： " + entry.getKey() + " and value： " + entry.getValue());
            String key = entry.getKey();
            String value = entry.getValue();
            templateString = templateString.replace(key, value);
        }

        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(templateString);

            //字符处理，本处需要处理中文
            ITextFontResolver fontResolver = renderer.getFontResolver();
            //判断系统类型
            String osName = System.getProperty("os.name");
            if(osName == null || osName.isEmpty()) {
                logger.error("获取系统类型错误。" + osName);
                return null;
            }

            //设置系统字体
            String fontPath = Config.getInstance().get("signpdf.font.path");
            fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            if (osName.toLowerCase().startsWith("win"))
//                fontResolver.addFont("C:/Windows/Fonts/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            else
//                fontResolver.addFont("/usr/share/fonts/TTF/ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            renderer.layout();
            String pdfFileName = map.get("sfzh") + "_" + CommUtil.formatDate(new Date(), "yyyyMMddHHmmss") + ".pdf";
            String pdfFile2 = Config.getInstance().get("signpdf.save.path") + System.getProperty("file.separator") + pdfFileName;

//			OutputStream os = response.getOutputStream();      //输出到网页
            OutputStream os = new FileOutputStream(pdfFile2);  //输出到文件

            //根据renderer内容，创建PDF文件
            renderer.createPDF(os);

            //关闭输出流
            os.close();

            String requestFilePath =  Config.getInstance().get("signpdf.download.url") + pdfFileName;

            File pdfFile = new File(pdfFile2);
            if(pdfFile.exists())
                return requestFilePath;
            else
                return null;
        } catch (Exception e) {
            logger.error("生成签章PDF错误", e);
            return null;
        }
    }





    /**
     * 读取文本文件
     * @param fileName
     * @return
     */
    public String readToString(String fileName) throws Exception {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            throw new Exception(e);
        } catch (IOException e) {
            throw new Exception(e);
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            logger.error("The OS does not support " + encoding, e);
            return null;
        }
    }

    /***
     * 替换集合中字段的替换符
     * @param map
     * @return
     */
    public HashMap<String, String> replaceHashMap(HashMap<String, String> map){

        HashMap<String, String> oldMap = map;
        HashMap<String, String> newMap = new HashMap<>();

        for (Map.Entry<String, String> entry : oldMap.entrySet()) {
            String newFiledName = "";
            try {
                newFiledName = createReplaceChar(entry.getKey());
            }catch (Exception ex){
                newFiledName = "";
            }

            if(newFiledName == null || newFiledName.isEmpty())
                continue;

            newMap.put(newFiledName, entry.getValue());
        }

        return newMap;
    }

    public String createReplaceChar(String fieldName) throws Exception{

        if(fieldName == null || fieldName.isEmpty())
            throw new Exception("字段名不允许为空");

        String charValueStart = "{#";
        String charValueEnd = "}";

        String replaceChar = charValueStart + fieldName + charValueEnd;
        return replaceChar;
    }
}
