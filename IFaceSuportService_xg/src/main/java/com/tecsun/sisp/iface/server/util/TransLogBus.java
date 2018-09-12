package com.tecsun.sisp.iface.server.util;

import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.util.JsonMapper;
import com.tecsun.sisp.iface.common.vo.*;
import com.tecsun.sisp.iface.common.vo.his.HisRequestBusInfoBean;
import org.apache.commons.lang.StringUtils;

/**
 * Created by jerry on 2015/5/31.
 */
public class TransLogBus {
//
//    /**
//     * 添加 五险业务 ,医院 操作日志业务分析子系统，校验通过添加日志
//     * @param secQueryBean  操作日志对象，被调用的方法（根据方法名获取业务类型编码）
//     * @param funcName
//     * @throws Exception
//     */
//    public void insertTransLog(SecQueryBean secQueryBean,String funcName)throws Exception{
//        if(StringUtils.isEmpty(DictionaryUtil.getDictName(Constants.CHANNELCODE,secQueryBean.getChannelcode()))){
//            throw new Exception("渠道类型不存在："+secQueryBean.getChannelcode());
//        }
////        if(Constants.TSB.equals(secQueryBean.getChannelcode())||Constants.SELFSERVICE.equals(secQueryBean.getChannelcode())) {
////            if (!checkDev(secQueryBean.getDeviceid())) {
////                throw new Exception("设备ID不存在：" + secQueryBean.getDeviceid());
////            }
////        }
//        TransBean transBean=new TransBean();
//        transBean.setTransid(CommUtil.getUUID());
//        transBean.setUserid(secQueryBean.getId());
//        transBean.setChannelcode(secQueryBean.getChannelcode());
//        transBean.setBusinesscode(funcName);
//        transBean.setDeviceid(secQueryBean.getDeviceid());
//        transBean.setStarttime(CommUtil.getNowDateLongStr());
//        transBean.setEndtime(CommUtil.getNowDateLongStr());
//        transBean.setTransmsg("1");
//        transBean.setStatus("1");
//        transBean.setResult("1");
//        String jsonData= JsonMapper.buildNormalMapper().toJson(transBean);
//        DictionaryUtil.postClientRequest(jsonData, DictionaryUtil.getHost(Constants.TRANSANALYSIS_URI));
//    }
//
//    public void insertTransLog(CardInfoBean bean,String funcName)throws Exception{
//        if(StringUtils.isEmpty(DictionaryUtil.getDictName(Constants.CHANNELCODE,bean.getChannelcode()))){
//            throw new Exception("渠道类型不存在："+bean.getChannelcode());
//        }
////        if(Constants.TSB.equals(bean.getChannelcode())||Constants.SELFSERVICE.equals(bean.getChannelcode())) {
////            if (!checkDev(bean.getDeviceid())) {
////                throw new Exception("设备ID不存在：" + bean.getDeviceid());
////            }
////        }
//        TransBean transBean=new TransBean();
//        transBean.setTransid(CommUtil.getUUID());
//        transBean.setUserid(bean.getId());
//        transBean.setChannelcode(bean.getChannelcode());
//        transBean.setBusinesscode(funcName);
//        transBean.setDeviceid(bean.getDeviceid());
//        transBean.setStarttime(CommUtil.getNowDateLongStr());
//        transBean.setEndtime(CommUtil.getNowDateLongStr());
//        transBean.setTransmsg("1");
//        transBean.setStatus("1");
//        transBean.setResult("1");
//        String jsonData= JsonMapper.buildNormalMapper().toJson(transBean);
//        DictionaryUtil.postClientRequest(jsonData, DictionaryUtil.getHost(Constants.TRANSANALYSIS_URI));
//    }
//    //医院 通知平台、预约缴费接口的业务分析子系统操作
//    public void insertTransLog(HisRequestBusInfoBean bean,String funcName)throws Exception{
//        if(StringUtils.isEmpty(DictionaryUtil.getDictName(Constants.CHANNELCODE,bean.getCardType()))){
//            throw new Exception("渠道类型不存在："+bean.getCardType());
//        }
//
//        TransBean transBean=new TransBean();
//        transBean.setTransid(CommUtil.getUUID());
//        transBean.setUserid(bean.getFLOWID());
//        //  transBean.setUserid(bean.getId());
//        transBean.setChannelcode(bean.getCardType());
//        transBean.setBusinesscode(funcName);
//        transBean.setDeviceid(transBean.getDeviceid());
//        //transBean.setDeviceid(bean.getDeviceid());
//        transBean.setStarttime(CommUtil.getNowDateLongStr());
//        transBean.setEndtime(CommUtil.getNowDateLongStr());
//        transBean.setTransmsg("1");
//        transBean.setStatus("1");
//        transBean.setResult("1");
//        String jsonData= JsonMapper.buildNormalMapper().toJson(transBean);
//        DictionaryUtil.postClientRequest(jsonData, DictionaryUtil.getHost(Constants.TRANSANALYSIS_URI));
//    }
//    //医院 终端取号、取消挂号接口的业务分析子系统操作
//    public void insertTransLog(HisRequstBean bean,String funcName)throws Exception{
//        if(StringUtils.isEmpty(DictionaryUtil.getDictName(Constants.CHANNELCODE,bean.getChannelcode()))){
//            throw new Exception("渠道类型不存在："+bean.getChannelcode());
//        }
//
//        TransBean transBean=new TransBean();
//        transBean.setTransid(CommUtil.getUUID());
//        transBean.setUserid(bean.getId());
//        transBean.setChannelcode(bean.getChannelcode());
//        transBean.setBusinesscode(funcName);
//        transBean.setDeviceid(transBean.getDeviceid());
//        // transBean.setDeviceid(bean.getDeviceid());
//        transBean.setStarttime(CommUtil.getNowDateLongStr());
//        transBean.setEndtime(CommUtil.getNowDateLongStr());
//        transBean.setTransmsg("1");
//        transBean.setStatus("1");
//        transBean.setResult("1");
//        String jsonData= JsonMapper.buildNormalMapper().toJson(transBean);
//        DictionaryUtil.postClientRequest(jsonData, DictionaryUtil.getHost(Constants.TRANSANALYSIS_URI));
//    }

}
