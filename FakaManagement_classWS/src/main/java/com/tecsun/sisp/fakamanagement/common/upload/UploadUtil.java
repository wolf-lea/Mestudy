package com.tecsun.sisp.fakamanagement.common.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
/**
 * 上传文件工具类
 * @author po_tan
 *
 */
public class UploadUtil {
	
    public static String getFile(HttpServletRequest request , String path) throws Exception {
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();//取得当前上传文件的文件名称
                    if (StringUtils.isNotBlank(myFileName.trim())) {
                    	InputStream in = file.getInputStream();
                    	String filename = myFileName;//multiRequest.getParameter("key");
                    	return writeFile(request, path, in , filename);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("获取文件失败");
        }
        return null;
    }
    
    public static String writeFile(HttpServletRequest req,String path , InputStream in , String filename)throws Exception{
        String fileName = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String uploadTo = req.getSession().getServletContext().getRealPath("/") +path+"/";
            filename = filename.split("\\.")[1];
            fileName = format.format(new Date())+"."+filename;
            String _fileName = uploadTo + fileName;
            File tempPathFile=new File(uploadTo);
            if (!tempPathFile.exists()) {
                tempPathFile.mkdirs();
            }
            OutputStream out=new FileOutputStream(new File(_fileName));
            byte[] b=new byte[1024];
            int len;
            while((len=in.read(b))>=0){
                out.write(b,0,len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("写入缓存文件到服务器失败");
        }
        return fileName;
    }
    
    public static void export(String filename , String path , String txt , HttpServletRequest request , HttpServletResponse response) {
        try {
            String fileName = filename;
            String xpath = request.getSession().getServletContext().getRealPath("/") +path+"/";
            InputStream inStream = new FileInputStream(xpath+fileName+"." + txt);// 文件的存放路径
            //tomcate 6:response.setContentType("application/vnd.ms-excel,charset=UTF-8");
            response.setContentType("multipart/form-data;charset=UTF-8");//tomcate 7
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        	
        }
    }
}
