package com.tecsun.sisp.net.modules.controller.sign.controller;

import com.tecsun.sisp.net.common.Config;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tecsun.sisp.net.common.Constants;
import com.tecsun.sisp.net.common.vo.faceverify.Result;
import com.tecsun.sisp.net.modules.controller.sign.service.impl.PdfService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lorn on 2018/7/27.
 */
@RestController
@RequestMapping("/adapter/sign")
public class SignController {

    private static Logger logger = LoggerFactory.getLogger(SignController.class);

    @Autowired
    private PdfService pdfService;

    @RequestMapping(value = "/signPdf", method = RequestMethod.POST, produces = "application/json")
    public Result SignPdf(@RequestBody Map<String,String> bean) {

        String templateName = bean.get("templateName");
        Map<String,String> dataFieldMap = new HashMap<>();
        dataFieldMap.put("id",bean.get("id"));
        dataFieldMap.put("name",bean.get("name"));
        dataFieldMap.put("channelcode",bean.get("channelcode"));
        Map<String,String> map = null;
        try {
            map = pdfService.getData(dataFieldMap);
        } catch (Exception e) {
            logger.error("生成签章证明失败.",e);
            return new Result("-100","生成签章证明失败." + e.getMessage(),"");
        }
        if(map == null || map.size() == 0)
            return new Result("-100","生成签章证明失败","");

        String pdfPath = null;
        try {
            pdfPath = pdfService.createSignFile(templateName, map);
        } catch (Exception e) {
            logger.error("生成签章证明失败.",e);
            return new Result("-100","生成签章证明失败。" + e.getMessage(),"");
        }

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("signfile",pdfPath);

        return new Result(Constants.RESULT_MESSAGE_SUCCESS,"生成签章证明成功",dataMap);
    }

    @RequestMapping(value = "/download/{fileName:.*}")
    public void downloadFile(@PathVariable String fileName,HttpServletResponse response) throws IOException {
        // 拼接真实路径
        String realPath = Config.getInstance().get("signpdf.save.path") + System.getProperty("file.separator") + fileName;
        // 读取文件
        File file = new File(realPath);
        if(file.exists()){
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            try {
                response.setContentType("application/octet-stream");
                fileName = URLEncoder.encode(fileName, "UTF-8");
                response.setHeader("Content-disposition", "attachment; filename="
                        + new String(fileName.getBytes("utf-8"), "ISO8859-1")); // 指定下载的文件名
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } catch (IOException e) {
                logger.error("签章PDF下载文件出错：",e);
            } finally {
                if(os != null){
                    os.close();
                }
            }
        }else{
            logger.error("签章PDF下载文件不存在。" + fileName);
        }
    }

}