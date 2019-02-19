package com.tecsun.sisp.net.modules.common;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lorn on 2018/8/3.
 */
public class FileFromUrlUtil {
    private static Logger logger = LoggerFactory.getLogger(FileFromUrlUtil.class);


    /**
     * 上传文件到服务器指定目录
     * @param savePath:保存路径
     * @param fileType:文件类型
     * @param partName 文件名需匹配字段
     * @param type 文件名已存在时操作 1、返回提示；2、重命名原文件； 3、删除原文件
     * @return
     */
    public static Map uploadFile(String savePath,String partName, String fileType,
                                 HttpServletRequest request,int type) {
        Map map=new HashMap();
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                map.put("isUpload","F");
                map.put("message","请上传正确文件");
                return map;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    map.put("isUpload","F");
                    map.put("message","请上传正确的文件");
                    return map;
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    map.put("fileName",filename);
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    if (org.apache.commons.lang.StringUtils.isNotBlank(partName)&&!filename.contains(partName)) {
                        map.put("isUpload","U");
                        map.put("message","请上传正确命名的文件,如："+   partName);
                        return map;
                    }
                    String uploadFilePath = savePath + File.separator + filename;
                    File uploadFile = new File(uploadFilePath);
                    //判断上传文件名是否存在并重命名原文件
                    if (uploadFile.exists() && uploadFile.isFile()) {
                        if (type == 1) {
                            map.put("isUpload", "R");
                            map.put("message", "同名文件已存在，请勿重复上传！");
                            return map;
                        } else if (type == 2) {
                            File oldfile = new File(uploadFilePath);
                            oldfile.renameTo(new File(uploadFilePath + oldfile.lastModified()));
                        } else if (type == 3) { //先删除原有数据
                            if (!FileFromUrlUtil.deleteDir(new File(savePath))) {
                                logger.error(savePath + "删除失败");
                                map.put("isUpload", "R");
                                map.put("message", "文件上传失败！");
                                return map;
                            }
                        }
                    }
                    //正式开始上传文件
                    //判断上传文件的保存目录是否存在,并新建
                    File file = new File(savePath);
                    //判断上传文件的保存目录是否存在
                    if (!file.exists() && !file.isDirectory()) {
                        //创建目录
                        file.mkdirs();
                    }

                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    if (StringUtils.isBlank(fileType)||fileExtName.equals(fileType)) {
                        //获取item中的上传文件的输入流
                        InputStream in = item.getInputStream();
                        //创建一个文件输出流
                        FileOutputStream out = new FileOutputStream(uploadFilePath);
                        //创建一个缓冲区
                        byte buffer[] = new byte[1024];
                        //判断输入流中的数据是否已经读完的标识
                        int len = 0;
                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                        while ((len = in.read(buffer)) > 0) {
                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录当中
                            out.write(buffer, 0, len);
                        }
                        //关闭输入流
                        in.close();
                        //关闭输出流
                        out.close();
                        //删除处理文件上传时生成的临时文件
                        item.delete();
                        map.put("isUpload","Y");
                        map.put("message","上传成功！");

                    } else {
                        map.put("isUpload","E");
                        map.put("message","文件的扩展名不对，后缀应为："+fileType);
                    }
                }
            }
        }catch (Exception e) {
            map.put("isUpload","N");
            map.put("message","上传失败");
            logger.error("上传失败",e);
        }
        return map;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param filePath 文件路径（包括文件名）
     * @param contentType 内容类型
     */
    public static int downloadFile(HttpServletRequest request,HttpServletResponse response, String filePath, String contentType) throws Exception {
        // path是指欲下载的文件的路径。
        File file = new File(filePath);
        if (!file.exists()) {
            return 1;//暂无该文件
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 取得文件名
            String fileName = file.getName();
            request.setCharacterEncoding("UTF-8");
            fileName = encodeChineseDownloadFileName(request, fileName);
            if (StringUtils.isBlank(contentType)) {
                response.setContentType("multipart/form-data;charset=UTF-8");//tomcate 7
            } else {
                response.setContentType(contentType + ";charset=UTF-8");
            }
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();

            return 200;
        } catch (Exception e) {
            logger.error("下载文件失败", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性
     */
    public static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName) throws UnsupportedEncodingException {
        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent&&pFileName!=null){
            if (-1 != agent.indexOf("Firefox")) {//Firefox
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8"))))+ "?=";
            }else if (-1 != agent.indexOf("Chrome")) {//Chrome
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {//IE7+
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");//替换空格
            }
        } else {
            filename = pFileName;
        }
        return filename;
    }


    /**
     * 向文件中写入数据 BufferedWriter
     * @param fileName 文件路径（包括文件名）
     * @param content 内容
     */
    public static boolean writeByBufferedReader(String fileName,String content) {
        boolean flag=false;
        try {
            String filePath=fileName.substring(0,fileName.lastIndexOf(File.separator));
            File file = new File(filePath);
            if (!file.exists())
                file.mkdirs();
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            flag=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (!dir.exists()) {
            return true;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
