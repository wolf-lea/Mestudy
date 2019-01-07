package com.tecsun.sisp.adapter.modules.version.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.version.entity.request.VersionBean;
import com.tecsun.sisp.adapter.modules.version.entity.response.VersionVo;
import com.tecsun.sisp.adapter.modules.version.service.impl.VersionServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xumaohao on 2017/11/16.
 * 用于app版本更新
 */
@RestController
@RequestMapping("/adapter/version")
public class VersionController extends BaseController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    VersionServiceImpl versionService;

    /**
     * 查询软件最新版本号
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectVersion", method = RequestMethod.POST, produces = "application/json")
    public Result selectVersion(@RequestBody VersionBean bean) {
        //入参校验
        if (StringUtils.isEmpty(bean.getChannelcode()) ||
                StringUtils.isEmpty(bean.getSortwareVersion()) ||
                StringUtils.isEmpty(bean.getSortwareCode())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数缺失");
        }

        try {
            VersionVo versionVo;
            //校验当前版本是否合法 即是否错在数据库
//            versionVo = versionService.checkVersion4sisp(bean);
//            if(versionVo == null){
//                return ok("当前软件非官方软件");
//            }
            versionVo = versionService.selectVersion4sisp(bean);
            return ok("获取最新版本信息成功", versionVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("VersionController====selectVersion");
            logger.error("获取最新版本信息出错：" + e);
        }
        return error("获取最新版本信息出错");
    }

    /**
     * 更新下载量
     * @param bean
     * @return
     */
    @RequestMapping(value = "/updateDownloadNumber", method = RequestMethod.POST, produces = "application/json")
    public Result updateDownloadNumber(@RequestBody VersionBean bean, HttpServletResponse response) {
        //入参校验
        if (StringUtils.isEmpty(bean.getChannelcode()) || (bean.getId() == 0)) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数缺失");
        }
        try {
//            //以流的形式输出文件
//            OutputStream os = null;
//            BufferedInputStream bis = null;
//            InputStream is = null;
//            String path = "";
//            if (StringUtils.isNotBlank(bean.getUpdateMethod())) {
//                VersionVo versionVo = versionService.selectVersion4sisp(bean);
//                if (versionVo == null) {
//                    return this.result(Constants.RESULT_MESSAGE_EXCEPTION, "暂无更新");
//                }
//                path = versionVo.getDownloadLink();
////                String link = Config.getInstance().get("version.file.path");
//                String link = "";
////                path = link + path;
//                path = "E:\\WebStorm-2017.2.5.exe";
//                File file = new File(path);
//                String fileName = file.getName();
//                if (!file.exists() || !file.isFile()) {
//                    logger.error("用于更新的软件版本文件丢失，版本表id=" + versionVo.getId() +
//                            "，版本号=" + versionVo.getSortwareVersion() + "，路径=" + path);
//                    return this.result(Constants.RESULT_MESSAGE_EXCEPTION, "需要下载的版本更新文件不存在，请联系技术人员处理");
//                }
//                response.setContentType("application/octet-stream");
//                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName));
//                try {
//                    is = new FileInputStream(file);
//                    bis = new BufferedInputStream(is);
//                    os = new BufferedOutputStream(response.getOutputStream());
//                    int len;
//                    byte[] bytes = new byte[10240];
//                    while (-1 != (len = bis.read(bytes))) {
//                        os.write(bytes, 0, len);
//                    }
//                    os.flush();
//                } catch (Exception e2) {
//                    logger.error("下载版本文件出错，文件路径=" + path);
//                    return this.error("不好意思，程序出现异常");
//                } finally {
//                    try {
//                        if (os != null) {
//                            os.close();
//                        }
//                        if (bis != null) {
//                            bis.close();
//                        }
//                    } catch (Exception e4) {
//                        logger.error(e4);
//                        return this.error("不好意思，程序出现异常");
//                    }
//                }
//            }
            versionService.updateDownloadNumber4sisp(bean);
            return ok("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("VersionController====updateDownloadNumber");
            logger.error("软件版本更新-计算下载量出错：" + e);
        }
        return error("更新失败");
    }

    /**
     * 新增版本
     * @param bean
     * @return
     */
    @RequestMapping(value = "/addVersion", method = RequestMethod.POST, produces = "application/json")
    public Result addVersion(@RequestBody VersionVo bean) {
        //入参校验
        if (StringUtils.isEmpty(bean.getAppType()) || StringUtils.isEmpty(bean.getSortwareVersion())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数缺失");
        }
        try {
            int versionVo = versionService.addVersion4sisp(bean);
            return ok("新增版本成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("VersionController====addVersion");
            logger.error("新增软件版本出错：" + e);
        }
        return error("新增软件版本出错");
    }

    /**
     * 修改版本
     * @param bean
     * @return
     */
    @RequestMapping(value = "/updateVersion", method = RequestMethod.POST, produces = "application/json")
    public Result updateVersion(@RequestBody VersionVo bean) {
        //入参校验
        if (bean.getId() == 0) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数缺失");
        }
        try {
            int versionVo = versionService.updateVersion4sisp(bean);
            return ok("修改版本信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("VersionController====updateVersion");
            logger.error("修改版本信息出错：" + e);
        }
        return error("修改版本信息出错");
    }

}
