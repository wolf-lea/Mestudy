package com.tecsun.sisp.adapter.modules.common.service.impl;

import com.tecsun.sisp.adapter.common.util.*;

import com.tecsun.sisp.adapter.modules.common.entity.request.*;
import com.tecsun.sisp.adapter.modules.common.entity.response.AreaInfoVo;
import com.tecsun.sisp.adapter.modules.common.entity.response.Branch;
import com.tecsun.sisp.adapter.modules.common.entity.response.BusConfigVO;
import com.tecsun.sisp.adapter.modules.common.entity.response.DictionaryVO;
import com.tecsun.sisp.adapter.modules.common.entity.response.NoticeVo;
import com.tecsun.sisp.adapter.modules.common.entity.response.ServiceBlankVO;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
/*import com.tecsun.sisp.adapter.modules.ine.entity.request.IneQueryBean;
import com.tecsun.sisp.adapter.modules.ine.entity.response.AreaVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PositionVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecordVo;*/
import com.tecsun.sisp.adapter.modules.so.entity.response.TSBSssmVO;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyBean;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**个人基本表信息相关
 * 图片信息相关
 * 德生宝信息相关
 * Created by danmeng on 2017/3/14.
 */
@Service("CommServiceImpl")
public class CommServiceImpl extends BaseService {
    public final static Logger logger = Logger.getLogger(CommServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl.";

    public final static String DeviceSource = Config.getInstance().get("device.synchronization"); //设备信息是否从设备管理系统同步

    //根据图片类型编码获取对应存放地址
    public String getPicturePath(String type) {
        String path = null;
        if (StringUtils.isNotBlank(type)) {
            String picPath = Constants.picture_path.get(type);
            path = Config.getInstance().get(picPath);
        }
        if (StringUtils.isBlank(path))
            path = Config.getInstance().get("picture_path");
        return path;
    }

    //上传图片-包含具体信息
    public long uploadPicture4Cssp(PicBean bean) throws Exception {
        long type = 0;
        try {
            if (StringUtils.isBlank(bean.getPicType())) bean.setPicType(Constants.PICTURE_TYPE_000);
            String path = getPicturePath(bean.getPicType());
            File file = new File(path);
            if (!file.exists())
                file.mkdirs();
            String filename = UID.uuid() + ".jpg";
            String filepath = path + File.separator + filename;
            ImageChangeUtil.generateImage(bean.getPicBase64(), filepath);
            boolean flag = ImageChangeUtil.isImage(filepath);
            if (!flag) {
                ImageChangeUtil.deletePic(filepath);// 删除文件
                type = -1;//  "入参不正确，传入的图像信息有误";
            } else {
                // 把图片存到公服T_YTH_PICTURE_INFO表
                bean.setPicPath(filename);
                int r = this.getSqlSessionTemplate().insert(NAMESPACE + "uploadPicture", bean);
                if (r > 0) {
                    type = bean.getPicId();
                }
            }
        } catch (Exception e) {
            logger.error("上传图片失败" + JsonHelper.javaBeanToJson(bean));
            logger.error("上传图片失败：", e);
        }
        return type;
    }

    //上传图片-暂无具体信息
    public long insertPicture4Cssp(PicBean bean) throws Exception {
        long picId = 0;
        try {
            // 把信息存到公服T_YTH_PICTURE_INFO表 暂无图片
            int r = this.getSqlSessionTemplate().insert(NAMESPACE + "uploadPicture", bean);
            if (r > 0) {
                picId = bean.getPicId();
            }
        } catch (Exception e) {
            logger.error("上传图片失败" + JsonHelper.javaBeanToJson(bean));
            logger.error("上传图片失败：", e);
        }
        return picId;
    }

    //更改图片信息
    public long updatePicture4Cssp(PicBean bean) throws Exception {
        long type = 0;
        try {
            String path = getPicturePath(bean.getPicType());
            File file = new File(path);
            if (!file.exists())
                file.mkdirs();
            String filename = CommUtil.getUUID() + ".jpg";
            String filepath = path + File.separator + filename;
            ImageChangeUtil.generateImage(bean.getPicBase64(), filepath);
            boolean flag = ImageChangeUtil.isImage(filepath);
            if (!flag) {
                ImageChangeUtil.deletePic(filepath);// 删除文件
                type = -1;//  "入参不正确，传入的图像信息有误";
            } else {
                // 把图片存到公服T_YTH_PICTURE_INFO表
                bean.setPicPath(filename);
                int r = this.getSqlSessionTemplate().update(NAMESPACE + "updatePicture", bean);
                if (r > 0) {
                    type = bean.getPicId();
                }
            }
        } catch (Exception e) {
            logger.error("更改图片失败" + JsonHelper.javaBeanToJson(bean));
            logger.error("更改图片失败：", e);
        }
        return type;
    }


    public PicBean getPicture4Cssp(PicBean bean) throws Exception {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPicture", bean);
    }

    public int updatePictureBus4Cssp(PicBusBean bean) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "updatePictureBus", bean);
    }

    public void insertPersonPic4Cssp(PicBusBean bean) throws Exception {
        this.getSqlSessionTemplate().insert(NAMESPACE + "insertPersonPic", bean);
    }

    public long insertPictureBus4Cssp(PicBusBean bean) {
        long picId = 0;
        try {
            picId = this.getSqlSessionTemplate().insert(NAMESPACE + "insertPictureBus", bean);
        } catch (Exception e) {
            logger.error("图片关联业务：", e);
        }
        return picId;
    }

    //图片操作：根据图片id判断图片是否存在并返回图片路径
    public String photoIsExist4Cssp(long picId, String type) {
        String picPath = Constants.RESULT_MESSAGE_PARAM;
        if (picId == 0) return picPath;
        try {
            PicBean picBean = new PicBean();
            picBean.setPicId(picId);
            picBean = this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPicture", picBean);
            //判断采集图片文件是否存在
            if (picBean == null || StringUtils.isBlank(picBean.getPicPath())) {
                logger.error("picId=" + picId + " 图片不存在");
            } else {
                //根据地址判断采集图片是否存在
                String path = getPicturePath(type);
                String filePath = path + File.separator + picBean.getPicPath();
                File file = new File(filePath);
                if (file == null || !file.exists()) {
                    logger.error("picId=" + picId + "  filePath=" + filePath + " 图片不存在");
                } else picPath = filePath;
            }
        } catch (Exception e) {
            picPath = Constants.RESULT_MESSAGE_PARAM;
            logger.error("参看图片是否存在错误", e);
        }
        return picPath;
    }

    //图片关联用户、并修改业务信息
    public String photoTOPersonAndBus4Cssp(long picId, long personId, String picType, String busStatus) throws Exception {
        return photoTOPersonAndBus4Cssp(picId, personId, picType, busStatus, Constants.PIXEL_BUSINESS_STATUS_01, Constants.PIXEL_BUSINESS_STATUS_02, null);
    }

    //图片关联用户、并修改业务信息 包含图片信息
    public String photoTOPersonAndBus4Cssp(long picId, long personId, String picType, String busStatus, String picMessage) throws Exception {
        return photoTOPersonAndBus4Cssp(picId, personId, picType, busStatus, Constants.PIXEL_BUSINESS_STATUS_01, Constants.PIXEL_BUSINESS_STATUS_02, picMessage);
    }

    //    图片关联用户、并修改业务信息 （全）
    public String photoTOPersonAndBus4Cssp(long picId, long personId, String picType, String busStatus, String picStatus1, String picStatus2, String picMessage) {
        String picStatus = Constants.RESULT_MESSAGE_ERROR;
        PicBusBean picBusBean = new PicBusBean();
        picBusBean.setPersonId(personId);
        picBusBean.setPicId(picId);
        picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
        picBusBean.setPicType(picType);

        picBusBean.setPicMessage(picMessage);
        if (Constants.RESULT_MESSAGE_SUCCESS.equals(busStatus)) {
            picBusBean.setPicStatus(picStatus1);
        } else {
            picBusBean.setPicStatus(picStatus2);
        }
        try {
            this.getSqlSessionTemplate().insert(NAMESPACE + "insertPersonPic", picBusBean);
            this.getSqlSessionTemplate().update(NAMESPACE + "updatePictureBus", picBusBean);
            picStatus = Constants.RESULT_MESSAGE_SUCCESS;
        } catch (Exception e) {
            logger.error("图片关联用户、业务信息错误", e);
        }
        return picStatus;
    }

    /**
     * 根据personID及图片信息获取图片id
     * 入参：picStatus-图片状态 picType-图片类型 personId-个人id
     *
     * @param bean
     * @return
     */
    public PicBusBean getPictureByPersonId4Cssp(PicBusBean bean) throws Exception {
        PicBusBean picBusBean = new PicBusBean();
        if (bean.getPersonId() == 0 || StringUtils.isNotBlank(bean.getPicType())) {
            picBusBean = this.getSqlSessionTemplate().selectOne(
                    NAMESPACE + "getPictureByPersonId", bean);
        }
        return picBusBean;
    }

    /**
     * 个人基本信息-存入个人基本信息表
     *
     * @param bean
     * @return
     */
    public int insertPersonInfo4Cssp(PersonBean bean) throws Exception {
        int status = 0;
        try {
            if (StringUtils.isNotBlank(bean.getSfzh()) && StringUtils.isNotBlank(bean.getXm())) {
                if(StringUtils.isNotBlank(bean.getBirthday())) {
                    try {
                        CommUtil.getFormatDateString(bean.getBirthday(), "yyyy-MM-dd");
                    } catch (Exception e) {
                        logger.error("出生日期不符合格式:yyyy-MM-dd:"+bean.getBirthday(),e);
                        bean.setBirthday("");
                    }
                }
                if (StringUtils.isBlank(bean.getBirthday()))
                    bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
                status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertBasicPersonInfo", bean);
            } else status = -1;
        } catch (Exception e) {
            status = -2;
            logger.error("新增个人基本信息失败" + JsonHelper.javaBeanToJson(bean), e);
        }
        return status;
    }

    /**
     * 个人基本信息-更新个人基本信息表
     *
     * @param bean
     * @return
     */
    public int updatePersonInfo4Cssp(PersonBean bean) throws Exception {
        int status = 0;
        try {
            if (StringUtils.isNotBlank(bean.getSfzh()) && StringUtils.isNotBlank(bean.getXm())) {
                status = this.getSqlSessionTemplate().insert(NAMESPACE + "updateBasicPersonInfo", bean);
            } else status = -1;
        } catch (Exception e) {
            status = -2;
            logger.error("修改个人基本信息失败" + JsonHelper.javaBeanToJson(bean), e);
        }
        return status;
    }

    /**
     * 查询个人基本信息
     *
     * @param sfzh,xm
     * @return
     */
    public PersonBean getBasicPersonInfo4Cssp(String sfzh, String xm) throws Exception {
        Map map = new HashMap();
        map.put("xm", xm);
        map.put("sfzh", sfzh);
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getBasicPersonInfo", map);
    }

    //查询区域编码
    public String getCode4Sisp(String deviceId) {
        if (Constants.CONFIG_YES.equals(DeviceSource))
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCodeOfDevice", deviceId);
        else
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCode", deviceId);
    }

    //查询德生宝地址
    public String getTsbAddress4Sisp(String deviceId) {
        if (Constants.CONFIG_YES.equals(DeviceSource))
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTsbAddressOfDevice", deviceId);
        else
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTsbAddress", deviceId);
    }

    //查询德生宝和社保网点关联信息
    public TSBSssmVO getTsbSssmInfo4Sisp(String deviceId) {
        if (Constants.CONFIG_YES.equals(DeviceSource))
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTsbSssmInfoOfDevice", deviceId);
        else
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getTsbSssmInfo", deviceId);
    }


    /**
     * 非资格认证-存入认证业务记录表
     *
     * @param bean
     * @return
     */
    public int insertVerifyRecord4Cssp(VerifyBean bean) throws Exception {
        return this.getSqlSessionTemplate().insert(NAMESPACE + "insertVerifyRecord", bean);
    }

    //查询数据字典
    public List<DictionaryVO> getDictionaryById4Sisp(String groupId) throws Exception {
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDictionaryById", groupId);

    }
    //查询业务配置信息
    public List<BusConfigVO> getBusConfigInfo4Cssp(String configCode) throws Exception {
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getBusConfigInfo", configCode);

    }

    //获取网点（地图）
    public Page<Branch> getBranchList4cssp(BranchBean bean) throws Exception{
        Page<Branch> page =  new Page<>(bean.getPageno(),bean.getPagesize());
        bean.setPage(page);
        List<Branch> list = getSqlSessionTemplate().selectList(NAMESPACE + "getBranchList", bean);
        if(list != null && list.size() > 0){
            page.setData(list);
        }
        return page;
    }

    //查询德生宝和社保网点关联信息
    public Map<String,String> getCoord4Sisp(String deviceId) {
        if (Constants.CONFIG_YES.equals(DeviceSource))
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCoordOfDevice", deviceId);
        else
            return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCoord", deviceId);
    }

	/**根据区域编码返回子区域信息  app端
	 *
	 * @param ineQueryBean
	 * @return
	 *//*
	public Page<AreaInfoVo> getAreaList4Sisp(IneQueryBean ineQueryBean) {
		Map<String,String> map = new HashMap<>();
		Page<AreaInfoVo> page = new Page<>(ineQueryBean.getPageno(),100);
		List<AreaInfoVo> list = new ArrayList<>();
		//如果是德生宝端另做处理  且 传递的areaCode为 -1 从其他地方获取地区信息
		if(Constants.TSB.equals(ineQueryBean.getChannelcode()) && "-1".equals(ineQueryBean.getAreaCode())){
			map.put("deviceid", ineQueryBean.getDeviceid());
			list = this.getSqlSessionTemplate().selectList(NAMESPACE+"getIneTsbAreaList",map);
		}else{
			if(Constants.AREA_LEVEL_CITY.equals(ineQueryBean.getAreaLevel())){
				map.put("areaCode", ineQueryBean.getAreaCode().substring(0, 4));
			}else{
				map.put("areaCode", ineQueryBean.getAreaCode());
			}
			list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getAreaList", map);
		}
		if(list.size()>0){
			page.setData(list);
			page.setCount(list.size());
		}
		return page;
	}

	*//**根据父级岗位编码获取子级岗位信息
	 * @param ineQueryBean
	 * @return
	 *//*
	public Page<PositionVo> getPositionList4Cssp(IneQueryBean ineQueryBean) {
		Map<String,String> map = new HashMap<>();
		if(ineQueryBean == null){
			ineQueryBean = new IneQueryBean();
		}
		Page<PositionVo> page = new Page<>(ineQueryBean.getPageno(),100);
		map.put("parentCode", ineQueryBean.getpCode());
		map.put("queryType", ineQueryBean.getQueryType());
		List<PositionVo> list = new ArrayList<>();
		list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getPositionList", map);
		if(list.size()>0){
			page.setData(list);
			page.setCount(list.size());
		}
		return page;
	}*/

    /**
     * 获取须知 公告
     * @param bean
     * @return
     */
    public NoticeVo getNotice4cssp(NoticeBean bean) {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getNotice", bean);
    }
    
    /**
     * 根据区域编码返回子区域信息 pc端
     * @param bean
     * @return
     */
    public List<AreaInfoVo> getPCAreaList4Sisp(AreaQueryBean bean){
    	Map<String,String> map = new HashMap<>();
    	map.put("areaCode", bean.getAreaCode());
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAreaList", map);
    	
    }
    
    /**
     * 根据父级区域编码返回子区域信息 
     * @param bean
     * @return
     */
    public List<AreaInfoVo> getDistinceByParentCode4Sisp(AreaQueryBean bean){
    	Map<String,String> map = new HashMap<>();
    	map.put("code", bean.getAreaCode());
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDistinceByParentCode", map);
    	
    }
    
    /**
     * 查询所有服务银行
     * @param bean
     * @return
     */
    public List<ServiceBlankVO> getAllServiceBlank4Sisp(){
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAllServiceBlank");
    	
    }
    /**
     * 查询是否在非限制银行列表
     * @param bean
     * @return
     */
    public List<DictionaryVO> isExitBank4Sisp(String code){
    	Map<String,String> map = new HashMap<>();
    	map.put("code", code);
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "isExitDict",map);
    }
    
    /**
     * 查询银行网点列表
     * @param bean
     * @return
     */
    public List<ServiceBlankVO> getBlankNetList4Sisp(BranchBean bean){
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "getBlankNetList",bean);
    	
    }
    
    
}
