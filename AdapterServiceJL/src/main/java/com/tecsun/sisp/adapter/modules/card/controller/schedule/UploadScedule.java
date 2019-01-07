package com.tecsun.sisp.adapter.modules.card.controller.schedule;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.common.util.PicUtil;
import com.tecsun.sisp.adapter.modules.card.entity.request.CsspApplyBean;
import com.tecsun.sisp.adapter.modules.card.service.impl.CsspServiceImpl;

import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UploadScedule
{
    private static Logger logger = LoggerFactory.getLogger(UploadScedule.class);

    @Autowired
    private CsspServiceImpl csspService;
    @Autowired
    private CommServiceImpl commService;
    //定时上传社保卡申请数据：条件isUpload（N  表示尚未上传或失败）参保人相片状态（01 成功），上传次数小于规定次数uploadnumber
    public void upload() {
        //对应xml quartz.xml
        logger.info("....定时任务.....上传本地个人申领数据至第三方.....开始执行.......");
        try {
           String message="";
            //社保卡申请上传次数限制
            long uploadNum=0;
            String uploadnumber= Config.getInstance().get("card_apply_upload_num");
            if(StringUtils.isNotBlank(uploadnumber)&& ImageChangeUtil.isIntegerByString(uploadnumber))
                 uploadNum= Long.parseLong(uploadnumber);

        	//1、查询可上传的个人申领数据  返回数据性别	01男性;02女性;03未知  民族存数据库为编码
        	// 固定条件 isUpload（N  表示尚未上传或失败）参保人相片状态（01 成功）
            //社保卡申请上传次数限制(可选)
            List<CsspApplyBean> list = csspService.queryUploadData4Cssp(uploadNum);
            //2、上传数据
            if (list != null && list.size() != 0) {
                for (CsspApplyBean bean : list) {
                    if (StringUtils.isNotBlank(bean.getSfzh())) {
                        //个人相片信息
                        String perPhotoPath = commService.photoIsExist4Cssp(bean.getPhotoBuzId(), Constants.PICTURE_TYPE_102);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(perPhotoPath)) {
                            message = "个人相片不存在";
                            logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
                        } else {
                            bean.setPhoto64String(ImageChangeUtil.getImageStr(perPhotoPath));
                        }
                        //身份证正面信息
                        String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(), Constants.PICTURE_TYPE_103);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
                            message = "身份证正面图片不存在";
                            logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
                        } else {
                            bean.setIdcard64StringUp(ImageChangeUtil.getImageStr(picUpPath));
                        }
                        //身份证反面信息
                        String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(), Constants.PICTURE_TYPE_104);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
                            message = "身份证反面图片不存在";
                            logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
                        } else {
                            bean.setIdcard64StringDown(ImageChangeUtil.getImageStr(picDownPath));
                        }
                        //电子签名信息
                        if(bean.getSignphotoId()!=0) {
                            String signPhotoPath = commService.photoIsExist4Cssp(bean.getSignphotoId(), Constants.PICTURE_TYPE_105);
                            if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
                                message = "电子签名图片不存在";
                                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getSignphotoId());
                            } else {
                                bean.setSign64String(ImageChangeUtil.getImageStr(signPhotoPath));
                            }
                        }
                        //调用提供接口上传
                        boolean upload = true;//是否成功上传
                        String faileResonal = null;//上传失败原因
                        bean.setUploadNum(bean.getUploadNum() + 1);
                        if (upload) {
                            bean.setIsUpload("Y");
                            //设置已上传标记
                            logger.info("------设置已上传标志成功-------");
                        } else {
                            bean.setIsUpload("N");
                            logger.info("-------上传数据时接口返回插入失败---" + "身份证：" + bean.getSfzh() + ",姓名：" + bean.getXm() + "，插入失败原因：" + faileResonal);
                        }
                        csspService.updateCardApplyInfo4Cssp(bean.getPersonId(), bean.getIsUpload(), bean.getUploadNum());
                    }else
                        logger.info("-------上传数据时接口返回插入失败:无身份证 applyid:"+bean.getApplyId()) ;

                }
            }
        }
        catch (Exception e) {
            logger.error("....定时任务.....上传本地个人申领数据至第三方.....执行失败.......",e);
        }
        logger.info("....定时任务.....上传本地个人申领数据至第三方.....执行结束.......");
    }

    //照片转化成base64字符串 path为照片路径
    public String getImgStr(String path){
    	logger.info("------定时任务图片path:"+path+"-----");
    	if(StringUtils.isNotBlank(path)){
    		return PicUtil.GetImageStr(path);
    	}
    	return null;
    }
}