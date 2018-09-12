package com.tecsun.sisp.fun.common;

import com.tecsun.sisp.fun.bean.DictionaryBean;
import com.tecsun.sisp.fun.modules.controller.common.param.ManagerParam;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取其他子系统的数据
 * Created by zhangqingjie on 15-5-15.
 */
public class DefualtUseData {

    private static Logger logger = LoggerFactory.getLogger(DefualtUseData.class);

    /**
     * 根据字典表父节点获取,前台页面调用
     *
     * @param groupId
     * @return
     */
   /* public static String getDictionaryById(String groupId) {
        try {
            String url=DictionaryUtil.getHost("CHANNLCODES_URI")+"/"+groupId;
            String result = DictionaryUtil.getClientRequest(url);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
    public static String getDictionaryById(String groupId) {
        try {
            String key= Constants.DICTIONGROUPKEY.replace("groupId", groupId);
            String result = JedisUtil.getValue(key);
            String[] groupArray=result.split(",");
            List<DictionaryBean> list=new ArrayList<DictionaryBean>();
            for(int j=0;j<groupArray.length;j++){
                DictionaryBean dictionary=new DictionaryBean();
                dictionary.setCode(groupArray[j].split(":")[0]);
                dictionary.setName(groupArray[j].split(":")[1]);
                list.add(dictionary);
            }
            String arrays = JsonMapper.toNonNullJson(list);
            return arrays;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据大类，小类编码获取name,后台方法调用
     * @param groupId
     * @param code
     * @return
     * @throws Exception
     */
    public static String getDictName(String groupId,String code)throws Exception{
        try {
            if(StringUtils.isNotEmpty(groupId)&&StringUtils.isNotEmpty(code)) {
                String key= Constants.DICTIONGROUPKEY.replace("groupId", groupId);
                String result=JedisUtil.getValue(key);
                String[] dictionaryArray=result.split(",");
                for (String bean:dictionaryArray){
                    String[] dictbean=bean.split(":");
                    if(dictbean!=null&&dictbean.length==2) {
                        if (bean.split(":")[0].equals(code)) {
                            return bean.split(":")[1];
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("字典组转换出错,groupId:" + groupId + ",code:" + code);
        }
        return "";
    }

    /**
     * 根据设备编号查询对应的负责人信息
     *
     * @param deviceId
     * @return
     */
    public static ManagerParam getMangerInfoByDevID(String deviceId) {
        ManagerParam managerParam = new ManagerParam();
        if (!StringUtils.isEmpty(deviceId)) {
           // String url=DictionaryUtil.getHost("manager_uri")+"/"+deviceId;
            String url=DictionaryUtil.getHost(Constants.manager_uri)+"/"+deviceId;
           String result=DictionaryUtil.getClientRequest(url);
            if (!StringUtils.isEmpty(result)) {
                try {
                    managerParam = JsonMapper.buildNonDefaultMapper().fromJson(result, ManagerParam.class);
                } catch (IOException e) {
                    logger.error("根据设备编号查询对应的负责人信息出错：" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return managerParam;
    }

    /**
     * 获取所有设备负责人信息
     *
     * @return
     */
    public static String getAllManger() {
        //return DictionaryUtil.getClientRequest(DictionaryUtil.getHost("managerinfo_uri"));
        return DictionaryUtil.getClientRequest(DictionaryUtil.getHost(Constants.managerinfo_uri));
    }

    public static void main(String[] args) {
        String result = DefualtUseData.getAllManger();
        System.out.println(result);
        ManagerParam vos=DefualtUseData.getMangerInfoByDevID("T201505119262510");
        System.out.println(vos.getManager_id() + "===" + vos.getName());
//        System.out.println(getDictionaryById("0"));
    }

}
