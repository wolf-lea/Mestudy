package com.tecsun.sisp.fakamanagement.modules.service.impl.replacement;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Dom4JUtil;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceCardBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.CardStoreMergeBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceCardVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlicong on 2018/9/4.
 */
@Service("replacementCardServiceImpl")
public class ReplacementCardServiceImpl extends BaseService {

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.replacement.ReplacementCardServiceImpl.";

    public final static Logger logger = Logger.getLogger(ReplacementCardServiceImpl.class);

    @Autowired
    private LoginUserServiceImpl loginUserService;

    //查询发行服务卡人员信息
    public List<ReplaceCardVO> getCardInfoByIdcard(ReplaceCardBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<ReplaceCardVO> list = template.selectList(NAMESPACE + "getCardInfoByIdcard", bean);
        return list;
    }

    //查询补换卡人员信息
    public ReplaceCardVO getReplaceCardInfo(ReplaceCardBean bean)throws Exception{
        ReplaceCardVO vo = null;

        logger.info("调用卡数据查询接口开始===");
        String res = CardServiceUtils.invoke("getAC01", bean.getIdcard(), bean.getName(), null, bean.getReginal());
        logger.info("调用结果："+res);
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("OK".equals(err)){
            logger.info("卡数据查询成功！");
            vo = new ReplaceCardVO();
            vo.setName(map.get("AAC003"));
            vo.setIdcard(map.get("AAC002"));
            vo.setCardid(map.get("AAZ500"));
            vo.setCompanyName(map.get("AAB004"));
            vo.setPersonType(map.get("AAC009"));
            vo.setReginal(map.get("AAB301"));
            //查询所属城市名称
            if (!"".equals(vo.getReginal()) && !"null".equals(vo.getReginal())){
                DistinctAndBankVO req = new DistinctAndBankVO();
                req.setCode(vo.getReginal());
                List<DistinctAndBankVO> list = loginUserService.queryDistinctByCode(req);
                if (list.size()>0) {
                    vo.setReginalName(list.get(0).getName());
                }
            }

            //查询卡状态
            logger.info("调用卡状态查询接口开始===");
            String res2 = CardServiceUtils.invoke("getBank", bean.getIdcard(), bean.getName(), null, bean.getReginal());
            logger.info("调用结果："+res2);
            Map<String,String> map2 = Dom4JUtil.readXmlToMap(res2);
            String err2 = map2.get("ERR");
            if("OK".equals(err2)){
                logger.info("卡状态查询成功！");
                vo.setStatus(map2.get("AAZ502"));
                vo.setCardStatus(map2.get("AAZ502"));
                vo.setBankAccount(map2.get("AAE010"));
                vo.setBank(map2.get("AAE008"));
                DistinctAndBankVO req = new DistinctAndBankVO();
                req.setCode(vo.getBank());
                List<DistinctAndBankVO> list = loginUserService.queryFkwdByCode(req);
                if (list.size()>0) {
                    vo.setBankName(list.get(0).getName());
                }
            } /*else {
                logger.error("调用卡状态查询接口失败===" + err2);
                vo = new ReplaceCardVO();
                vo.setErrMsg("查询卡状态失败：" + err2);
            }*/

            //查询人员类别--调用卡进度查询接口
            logger.info("调用卡进度查询接口开始===");
            String res3 = CardServiceUtils.invoke("getAZ03", bean.getIdcard(), bean.getName(), null, bean.getReginal());
            logger.info("调用结果："+res3);
            Map<String,String> map3 = Dom4JUtil.readXmlToMap(res3);
            String err3 = map3.get("ERR");
            if("OK".equals(err3)){
                logger.info("人员类别查询成功！");
                vo.setPersonType(map3.get("CARDTYPE"));
                vo.setDistrict(map3.get("ORGANID").substring(0,8));
            } /*else {
                logger.error("调用卡进度查询接口失败===" + err3);
                vo = new ReplaceCardVO();
                vo.setErrMsg("查询人员类别失败：" + err3);
            }*/

            //查询入库网点
            logger.info("查询入库网点");
            List<ReplaceCardVO> list1 = getCardInfoByIdcard(bean);
            if (list1.size()>0){
                vo.setRkwd(list1.get(0).getRkwd());
            }

        } else {
            logger.error("调用卡数据接口失败===" + err);
            vo = new ReplaceCardVO();
            vo.setErrMsg("查询卡数据失败："+err);
        }
        return vo;
    }

    //临时挂失
    public String lsgs(ReplaceCardBean bean)throws Exception{
        String result = "";
        String param="<操作*>临时挂失</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<所属城市*>"+bean.getReginal()+"</所属城市*><社保卡号*>"+bean.getCardid()+"</社保卡号*>"
                + "<社会保障号码*>"+bean.getIdcard()+"</社会保障号码*><姓名*>"+bean.getName()+"</姓名*>";

        logger.info("调用临时挂失接口开始===" + param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("00".equals(err)){
            logger.info("临时挂失成功！");
        } else if ("01".equals(err)){
            logger.info("之前已经是临时挂失状态");
            result = "之前已经是临时挂失状态";
        }else {
            logger.error("调用临时挂失接口失败===" + err);
            result = err;
        }
        return result;
    }

    //正式挂失
    public String zsgs(ReplaceCardBean bean)throws Exception{
        String result = "";
        String param="<操作*>正式挂失</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<所属城市*>"+bean.getReginal()+"</所属城市*><社保卡号*>"+bean.getCardid()+"</社保卡号*>"
                + "<社会保障号码*>"+bean.getIdcard()+"</社会保障号码*><姓名*>"+bean.getName()+"</姓名*>";

        logger.info("调用正式挂失接口开始===" + param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("00".equals(err)){
            logger.info("正式挂失成功！");
        } else if ("01".equals(err)){
            logger.info("之前已经是正式挂失状态");
            result = "之前已经是正式挂失状态";
        }else {
            logger.error("调用正式挂失接口失败===" + err);
            result = err;
        }
        return result;
    }

    //解除挂失
    public String jcgs(ReplaceCardBean bean)throws Exception{
        String result = "";
        String param="<操作*>解除挂失</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<所属城市*>"+bean.getReginal()+"</所属城市*><社保卡号*>"+bean.getCardid()+"</社保卡号*>"
                + "<社会保障号码*>"+bean.getIdcard()+"</社会保障号码*><姓名*>"+bean.getName()+"</姓名*>";

        logger.info("调用解除挂失接口开始===" + param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("00".equals(err)){
            logger.info("解除挂失成功！");
        } else if ("01".equals(err)){
            logger.info("之前已经是正常状态");
            result = "之前已经是正常状态";
        }else {
            logger.error("调用解除挂失接口失败===" + err);
            result = err;
        }
        return result;
    }

    //(即时制卡标注)补换卡标注
    public String replaceMark(ReplaceCardBean bean)throws Exception{
        String result = "";
        String param="<操作*>即时制卡标注</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<所属城市*>"+bean.getReginal()+"</所属城市*><社会保障号码*>"+bean.getIdcard()+"</社会保障号码*><姓名*>"+bean.getName()+"</姓名*>";

        logger.info("调用即时制卡标注接口开始===" + param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("00".equals(err)){
            logger.info("即时制卡标注成功！");
        }else {
            logger.error("调用即时制卡标注接口失败===" + err);
            result = err;
        }
        return result;
    }

    //申请(补换)社保卡
    public String applyReplaceCard(ReplaceCardBean bean)throws Exception{
        String result = "";
        String param="";
        logger.info("调用卡数据查询接口开始(含照片)===");
        String res = CardServiceUtils.invoke("getData", bean.getIdcard(), bean.getName(), null, bean.getReginal());
        logger.info("调用结果："+res);
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("OK".equals(err)){
            logger.info("卡数据查询成功！");//22020000 22000100

            param="<操作*>申请社保卡</操作*><用户名*>"+Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<个人编号>"+map.get("GRBH")+"</个人编号><城市代码*>"+map.get("AAB301")+"</城市代码*>"
                + "<经办机构*>"+bean.getReginal()+"00"+"</经办机构*><申请类别*>"+bean.getReplaceType()+"</申请类别*><补换原因>"+bean.getReplaceReason()+"</补换原因>"
                + "<社保卡号>"+map.get("AAZ500")+"</社保卡号><卡片类别*>1</卡片类别*><国籍*>"+map.get("GJ")+"</国籍*>"
                + "<证件类型*>"+map.get("ZJLX")+"</证件类型*><证件有效期*>"+map.get("ZJYXQ")+"</证件有效期*>"
                + "<社会保障号*>"+map.get("AAC002")+"</社会保障号*><姓名*>"+map.get("AAC003")+"</姓名*>"
                + "<性别*>"+map.get("AAC004")+"</性别*><民族*>"+map.get("AAC005")+"</民族*>"
                + "<出生日期*>"+map.get("AAC006")+"</出生日期*><人员状态>"+map.get("AAC008")+"</人员状态><户口性质></户口性质><户口地址></户口地址><电话类型*>2</电话类型*>"
                + "<联系电话*></联系电话*><联系手机*>"+map.get("MOBILE")+"</联系手机*><通讯地址></通讯地址><邮政编码></邮政编码><电子邮箱></电子邮箱><单位编号>"+map.get("AAB001")+"</单位编号><单位名称>"+map.get("AAB004")+"</单位名称>"
                + "<行业*>1200</行业*><职业*>080</职业*><监护人证件类型></监护人证件类型><监护人证号></监护人证号><监护人姓名></监护人姓名>"
                + "<服务银行*>"+bean.getBankCode()+"</服务银行*><相片*>"+map.get("PHOTO")+"</相片*>";

            logger.info("调用申请社保卡接口开始===" + param);
            String res2 = CardServiceUtils.allDsjk(param);
            logger.info("调用结果："+res2);
            res2 = "<root>"+res2+"</root>";
            Map<String,String> map2 = Dom4JUtil.readXmlToMap(res2);
            String err2 = map2.get("ERR");
            if("00".equals(err2)){
                logger.info("申请社保卡成功！");
            }else {
                logger.error("调用申请社保卡接口失败===" + err2);
                result = err2;
            }
        } else {
            logger.error("调用卡数据接口失败===" + err);
            result = err;
            return result;
        }
        return result;
    }

    //自主发卡机制卡-获取制卡数据
    public ReplaceCardVO makeReplaceCard(ReplaceCardBean bean)throws Exception{
        ReplaceCardVO vo = null;
        //获取批次号
        String param="<操作*>获取批次号</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<所属区县*>"+bean.getDistrict()+"</所属区县*>";
        logger.info("调用获取批次号接口开始==="+param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("00".equals(err)){
            logger.info("获取批次号成功！");
            vo = new ReplaceCardVO();
            vo.setBatchNo(map.get("批次号"));

            //即制卡批次--获取社保卡号
            String param2="<操作*>即制卡批次</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                    +Config.getInstance().get("card_pwd")+"</密码*><所属区县*>"+bean.getDistrict()+"</所属区县*>"
                    +"<服务银行*>"+bean.getBankCode()+"</服务银行*><卡商*>"+bean.getKs()+"</卡商*>"
                    +"<批次号*>"+vo.getBatchNo()+"</批次号*><人数*>"+bean.getPersonTotal()+"</人数*>"+"\r\n"
                    +"序号,社会保障号码,姓名"+"\r\n"
                    +"1,"+bean.getIdcard()+","+bean.getName()+"\r\n";
            logger.info("调用获取社保卡号接口开始==="+param2);
            String res2 = CardServiceUtils.allDsjk(param2);
            logger.info("调用结果："+res2);

            /*res2="<ERR>00</ERR><批次号>286</批次号><人数>1</人数>\n" +
                    "序号,社保卡号,社会保障号码,姓名\n" +
                    "1,B00000019,220181199407205813,邬雪松\n";*/
            String[] split = res2.split("\n");
            res2 = "<root>"+split[0]+"</root>";
            Map<String,String> map2 = Dom4JUtil.readXmlToMap(res2);
            String err2 = map2.get("ERR");
            if("00".equals(err2)){
                logger.info("获取社保卡号成功！");
                String split2 = split[2];//取社保卡号信息串
                String[] cardInfo = split2.split(",");
                vo.setCardid(cardInfo[1]);//取社保卡号

                //获取即制卡数据
                String param3="<操作*>即制卡数据</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                        +Config.getInstance().get("card_pwd")+"</密码*><批次号*>"+vo.getBatchNo()+"</批次号*>"
                        +"<社保卡号*>"+vo.getCardid()+"</社保卡号*><社会保障号码*>"+bean.getIdcard()+"</社会保障号码*>";
                logger.info("调用获取即制卡数据接口开始==="+param3);
                String res3 = CardServiceUtils.allDsjk(param3);
                logger.info("调用结果："+res3);
                res3 = "<root>"+res3+"</root>";
                Map<String,String> map3 = Dom4JUtil.readXmlToMap(res3);
                String err3 = map3.get("ERR");
                if("00".equals(err3)) {
                    logger.info("获取即制卡数据成功！");
                    vo.setMakeCardData(res3);
                }else {
                    logger.error("调用即制卡数据接口失败===" + err3);
                    vo.setErrMsg("获取即制卡数据失败："+err3);
                    return vo;
                }

            }else if (err2.contains("已经有批次号")) {
                //查询批次号和社保卡号--调用卡进度查询接口
                logger.info("调用卡进度查询接口开始===");
                String res4 = CardServiceUtils.invoke("getAZ03", bean.getIdcard(), bean.getName(), null, bean.getReginal());
                logger.info("调用结果："+res4);
                Map<String,String> map4 = Dom4JUtil.readXmlToMap(res4);
                String err4 = map4.get("ERR");
                if("OK".equals(err4)){
                    logger.info("批次号和社保卡号查询成功！");
                    vo.setBatchNo(map4.get("BATCHNO"));
                    vo.setCardid(map4.get("AAZ500"));

                    //获取即制卡数据
                    String param5="<操作*>即制卡数据</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                            +Config.getInstance().get("card_pwd")+"</密码*><批次号*>"+vo.getBatchNo()+"</批次号*>"
                            +"<社保卡号*>"+vo.getCardid()+"</社保卡号*><社会保障号码*>"+bean.getIdcard()+"</社会保障号码*>";
                    logger.info("调用获取即制卡数据接口开始==="+param5);
                    String res5 = CardServiceUtils.allDsjk(param5);
                    logger.info("调用结果："+res5);
                    res5 = "<root>"+res5+"</root>";
                    Map<String,String> map5 = Dom4JUtil.readXmlToMap(res5);
                    String err5 = map5.get("ERR");
                    if("00".equals(err5)) {
                        logger.info("获取即制卡数据成功！");
                        vo.setMakeCardData(res5);
                    }else {
                        logger.error("调用即制卡数据接口失败===" + err5);
                        vo.setErrMsg("获取即制卡数据失败："+err5);
                        return vo;
                    }

                } else {
                    logger.error("调用卡进度查询接口失败===" + err4);
                    vo = new ReplaceCardVO();
                    vo.setErrMsg("查询批次号和社保卡号失败：" + err4);
                }

            }else {
                logger.error("调用获取社保卡号接口失败===" + err2);
                vo = new ReplaceCardVO();
                vo.setErrMsg("获取社保卡号失败："+err2);
                return vo;
            }
        }else {
            logger.error("调用获取批次号接口失败===" + err);
            vo = new ReplaceCardVO();
            vo.setErrMsg("获取批次号失败："+err);
            return vo;
        }
        return vo;
    }

    //PC端制卡-获取制卡数据
    public ReplaceCardVO makeReplaceCardByPC(ReplaceCardBean bean)throws Exception{
        ReplaceCardVO vo = null;
        //获取批次号
        String param="<操作*>获取批次号</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                + "<所属区县*>"+bean.getDistrict()+"</所属区县*>";
        logger.info("调用获取批次号接口开始==="+param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        String res3 = "";
        String err3 = "";
        Map<String,String> map3 = new HashMap<>();
        if("00".equals(err)){
            logger.info("获取批次号成功！");
            vo = new ReplaceCardVO();
            vo.setBatchNo(map.get("批次号"));

            //即制卡批次--获取社保卡号
            String param2="<操作*>即制卡批次</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                    +Config.getInstance().get("card_pwd")+"</密码*><所属区县*>"+bean.getDistrict()+"</所属区县*>"
                    +"<服务银行*>"+bean.getBankCode()+"</服务银行*><卡商*>"+bean.getKs()+"</卡商*>"
                    +"<批次号*>"+vo.getBatchNo()+"</批次号*><人数*>"+bean.getPersonTotal()+"</人数*>"+"\r\n"
                    +"序号,社会保障号码,姓名"+"\r\n"
                    +"1,"+bean.getIdcard()+","+bean.getName()+"\r\n";
            logger.info("调用获取社保卡号接口开始==="+param2);
            String res2 = CardServiceUtils.allDsjk(param2);
            logger.info("调用结果："+res2);

            /*res2="<ERR>00</ERR><批次号>286</批次号><人数>1</人数>\n" +
                    "序号,社保卡号,社会保障号码,姓名\n" +
                    "1,B00000019,220181199407205813,邬雪松\n";*/
            String[] split = res2.split("\n");
            res2 = "<root>"+split[0]+"</root>";
            Map<String,String> map2 = Dom4JUtil.readXmlToMap(res2);
            String err2 = map2.get("ERR");
            if("00".equals(err2)){
                logger.info("获取社保卡号成功！");
                String split2 = split[2];//取社保卡号信息串
                String[] cardInfo = split2.split(",");
                vo.setCardid(cardInfo[1]);//取社保卡号

                //获取即制卡数据
                String param3="<操作*>即制卡数据</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                        +Config.getInstance().get("card_pwd")+"</密码*><批次号*>"+vo.getBatchNo()+"</批次号*>"
                        +"<社保卡号*>"+vo.getCardid()+"</社保卡号*><社会保障号码*>"+bean.getIdcard()+"</社会保障号码*>";
                logger.info("调用获取即制卡数据接口开始==="+param3);
                res3 = CardServiceUtils.allDsjk(param3);
                logger.info("调用结果："+res3);
                res3 = "<root>"+res3+"</root>";
                map3 = Dom4JUtil.readXmlToMap(res3);
                err3 = map3.get("ERR");
                if("00".equals(err3)) {
                    logger.info("获取即制卡数据成功！");
                    vo.setMakeCardData(res3);
                }else {
                    logger.error("调用即制卡数据接口失败===" + err3);
                    vo.setErrMsg("获取即制卡数据失败："+err3);
                    return vo;
                }

            }else if (err2.contains("已经有批次号")) {
                //查询批次号和社保卡号--调用卡进度查询接口
                logger.info("调用卡进度查询接口开始===");
                String res4 = CardServiceUtils.invoke("getAZ03", bean.getIdcard(), bean.getName(), null, bean.getReginal());
                logger.info("调用结果："+res4);
                Map<String,String> map4 = Dom4JUtil.readXmlToMap(res4);
                String err4 = map4.get("ERR");
                if("OK".equals(err4)){
                    logger.info("批次号和社保卡号查询成功！");
                    vo.setBatchNo(map4.get("BATCHNO"));
                    vo.setCardid(map4.get("AAZ500"));

                    //获取即制卡数据
                    String param5="<操作*>即制卡数据</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                            +Config.getInstance().get("card_pwd")+"</密码*><批次号*>"+vo.getBatchNo()+"</批次号*>"
                            +"<社保卡号*>"+vo.getCardid()+"</社保卡号*><社会保障号码*>"+bean.getIdcard()+"</社会保障号码*>";
                    logger.info("调用获取即制卡数据接口开始==="+param5);
                    res3 = CardServiceUtils.allDsjk(param5);
                    logger.info("调用结果："+res3);
                    res3 = "<root>"+res3+"</root>";
                    map3 = Dom4JUtil.readXmlToMap(res3);
                    err3 = map3.get("ERR");
                    if("00".equals(err3)) {
                        logger.info("获取即制卡数据成功！");
                        vo.setMakeCardData(res3);
                    }else {
                        logger.error("调用即制卡数据接口失败===" + err3);
                        vo.setErrMsg("获取即制卡数据失败："+err3);
                        return vo;
                    }

                } else {
                    logger.error("调用卡进度查询接口失败===" + err4);
                    vo = new ReplaceCardVO();
                    vo.setErrMsg("查询批次号和社保卡号失败：" + err4);
                }

            }else {
                logger.error("调用获取社保卡号接口失败===" + err2);
                vo = new ReplaceCardVO();
                vo.setErrMsg("获取社保卡号失败："+err2);
                return vo;
            }
        }else {
            logger.error("调用获取批次号接口失败===" + err);
            vo = new ReplaceCardVO();
            vo.setErrMsg("获取批次号失败："+err);
            return vo;
        }
        //解析制卡数据
        if("00".equals(err3)) {
            logger.info("==========解析制卡数据==========");
            vo.setName(map3.get("姓名"));
            vo.setSex(map3.get("性别"));
            vo.setNation(map3.get("民族"));
            vo.setIdcard(map3.get("社会保障号码"));
            vo.setCardid(map3.get("社保卡号"));
            vo.setReplaceType(map3.get("申请类型"));
            vo.setReplaceReason(map3.get("补换原因"));
            vo.setBankCode(map3.get("服务银行"));
            vo.setFkrq(map3.get("发卡日期"));
            vo.setKyxq(map3.get("卡有效期"));
            vo.setBirth(map3.get("出生日期"));
            vo.setMobile(map3.get("联系手机"));
            vo.setPhoto(map3.get("相片"));
        }
        return vo;
    }

    //即制卡回盘
    public String replyCardInfo(ReplaceCardBean bean)throws Exception{
        String result = "";
        String param = "<操作*>即制卡回盘</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"
                +Config.getInstance().get("card_pwd")+"</密码*>"+"<批次号*>"+bean.getBatchNo()+"</批次号*><人数*>"+bean.getPersonTotal()+"</人数*>"+"\r\n"
                +"序号,社保卡号,社会保障号码,姓名,发卡日期,卡有效期,卡识别码,卡复位码,服务银行,银行卡号,个人帐户,装箱位置,失败环节(银行/制卡),失败原因(50字节内)"+"\r\n"
                +"1,"+bean.getCardid()+","+bean.getIdcard()+","+bean.getName()+","+bean.getFkrq()+","+bean.getKyxq()+","+bean.getKsbm()+","+bean.getAtr()+","
                +bean.getBankCode()+","+bean.getBankAccount()+","+bean.getAccount()+","+bean.getZxwz()+","+bean.getFailType()+","+bean.getFailReason()+"\r\n";

        logger.info("调用即制卡回盘接口开始==="+param);
        String res = CardServiceUtils.allDsjk(param);
        logger.info("调用结果："+res);
        res = "<root>"+res+"</root>";
        Map<String,String> map = Dom4JUtil.readXmlToMap(res);
        String err = map.get("ERR");
        if("00".equals(err)){
            logger.info("即制卡回盘成功！");
            bean.setStatus("01");//已发放

            //领卡启用
            String param2 = "<操作*>领卡启用</操作*><用户名*>"+ Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
                    + "<所属城市*>"+bean.getReginal()+"</所属城市*><社保卡号*>"+bean.getCardid()+"</社保卡号*>"
                    + "<社会保障号码*>"+bean.getIdcard()+"</社会保障号码*><姓名*>"+bean.getName()+"</姓名*>";
            logger.info("调用领卡启用接口开始==="+param2);
            String res2 = CardServiceUtils.allDsjk(param2);
            logger.info("调用结果："+res2);
            res2 = "<root>"+res2+"</root>";
            Map<String,String> map2 = Dom4JUtil.readXmlToMap(res2);
            String err2 = map2.get("ERR");
            if("00".equals(err2)){
                logger.info("领卡启用成功！");
            }else {
                logger.error("调用领卡启用接口失败===" + err2);
                result = "领卡启用失败："+err+"，请从其他渠道进行领卡启用！";
                bean.setStatus("03");//待激活
            }

        }else {
            logger.error("调用即制卡回盘接口失败===" + err);
            result = "即制卡回盘失败："+err;
            bean.setStatus("02");//发放失败
        }
        //记录补换卡人员和卡数据
        saveReplaceCardInfo(bean);
        return result;
    }

    //记录补换卡人员和卡数据
    public boolean saveReplaceCardInfo(ReplaceCardBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        //插入人员信息
        template.insert(NAMESPACE + "insertReplacePerson", bean);
        bean.setReplacecardPersonId(bean.getId());
        logger.info("插入人员信息");
        /*logger.info("查询人员信息是否存在");
        ReplaceCardVO vo = template.selectOne(NAMESPACE + "checkReplacePerson", bean);
        if (vo == null){
            template.insert(NAMESPACE + "insertReplacePerson", bean);
            bean.setReplacecardPersonId(bean.getId());
            logger.info("插入人员信息");
        }else {
            template.update(NAMESPACE + "updateReplacePerson", bean);
            bean.setReplacecardPersonId(vo.getId());
            logger.info("修改人员信息");
        }*/
        //插入卡信息
        logger.info("查询即制卡信息是否存在");
        boolean i = false;
        ReplaceCardVO vo1 = template.selectOne(NAMESPACE + "checkReplaceCard", bean);
        if (vo1 == null){
            i = template.insert(NAMESPACE + "insertReplaceCard", bean)>0;
            logger.info("插入即制卡信息");
        }else {
            i = template.update(NAMESPACE + "updateReplaceCard", bean)>0;
            logger.info("修改即制卡信息");
        }
        return i;
    }

    //制卡失败修改卡信息
    public boolean updateReplaceCardInfo(ReplaceCardBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        bean.setReplacecardPersonId(0);//制卡失败不插入人员信息
        logger.info("查询即制卡信息是否存在");
        boolean i = false;
        ReplaceCardVO vo1 = template.selectOne(NAMESPACE + "checkReplaceCard", bean);
        if (vo1 == null){
            //插入卡信息
            i = template.insert(NAMESPACE + "insertReplaceCard", bean)>0;
            logger.info("插入即制卡信息");
        }else {
            i = template.update(NAMESPACE + "updateReplaceCard", bean)>0;
            logger.info("修改即制卡信息");
        }
        return i;
    }

    //验证预制卡是否入库
    public List<ReplaceCardVO> checkReplaceCard(ReplaceCardBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        bean.setReplacecardPersonId(0);//制卡失败不插入人员信息
        logger.info("查询即制卡信息是否存在");
        List<ReplaceCardVO> list = template.selectList(NAMESPACE + "checkReplaceCard", bean);
        return list;
    }



}
