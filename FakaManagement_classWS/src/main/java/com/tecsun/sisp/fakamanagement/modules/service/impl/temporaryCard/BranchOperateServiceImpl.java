package com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Dom4JUtil;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.FakaFailParamBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.FakaParamBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TemporaryCardInfoBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.CardStoreCountVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.CardStoreTotalVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.UserFunctionVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardPersonVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TemporaryCardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlicong on 2018/3/21.
 */
@Service("branchOperateServiceImpl")
public class BranchOperateServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.BranchOperateServiceImpl.";

    public final static Logger logger = Logger.getLogger(BranchOperateServiceImpl.class);

    //查询网点临时卡列表
    public Page<TemporaryCardInfoVO> getTemporaryCardList(Page<TemporaryCardInfoVO> page,TemporaryCardInfoBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<TemporaryCardInfoVO> list = template.selectList(NAMESPACE + "getTemporaryCardList", req);
        for (TemporaryCardInfoVO vo : list){
            if ("00".equals(vo.getStatus())){
                vo.setStatus("初始(入库)");
            }else if("01".equals(vo.getStatus())){
                vo.setStatus("已发放");
            }else if("02".equals(vo.getStatus())){
                vo.setStatus("已注销(遗失)");
            }else if("03".equals(vo.getStatus())){
                vo.setStatus("已作废(损坏)");
            }else if("04".equals(vo.getStatus())){
                vo.setStatus("已回收");
            }else {
                vo.setStatus("注销(不再使用)");
            }
        }
        page.setData(list);
        return page;
    }

    //查询网点临时卡详细
    public TemporaryCardInfoVO getTemporaryCardDetail(TemporaryCardInfoBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        TemporaryCardInfoVO vo = template.selectOne(NAMESPACE + "getTemporaryCardList", req);
        /*if ("00".equals(vo.getStatus())){
            vo.setStatus("初始(入库)");
        }else if("01".equals(vo.getStatus())){
            vo.setStatus("已发放");
        }else if("02".equals(vo.getStatus())){
            vo.setStatus("已注销(遗失)");
        }else if("03".equals(vo.getStatus())){
            vo.setStatus("已作废(损坏)");
        }else if("04".equals(vo.getStatus())){
            vo.setStatus("已回收");
        }else {
            vo.setStatus("注销(不再使用)");
        }*/
        return vo;
    }

    /**
     * 获取调用临时卡发放接口的参数
     * @return
     */
    public String grantAllDsjkParam(String operation,String user,String pwd,FakaParamBean bean){
        StringBuffer sbf=new StringBuffer();
        sbf.append("<操作*>"+operation+"</操作*><用户名*>"+user+"</用户名*><密码*>"+pwd+"</密码*><所属城市*>"+bean.getCity()
                +"</所属城市*><经办机构*>"+bean.getOrgCode()+"</经办机构*><临时卡编号*>"+bean.getTempcardNo()
                +"</临时卡编号*><卡复位码*>"+bean.getAtr()+"</卡复位码*><社会保障号码*>"+bean.getIdcard()
                +"</社会保障号码*><姓名*>"+bean.getName()+"</姓名*><代办人身份证号>"+bean.getAgentIdCard()
                +"</代办人身份证号><代办人姓名>"+bean.getAgentName()+"</代办人姓名>");
        return sbf.toString();
    }

    /**
     * 获取调用临时卡发放失败接口的参数
     * @return
     */
    public String failAllDsjkParam(String operation,String user,String pwd,TempCardPersonVO bean){
        StringBuffer sbf=new StringBuffer();
        sbf.append("<操作*>"+operation+"</操作*><用户名*>"+user+"</用户名*><密码*>"+pwd+"</密码*><社保卡号*>"+bean.getCardid()
                +"</社保卡号*><AZ01LID*>"+bean.getAz01LID()+"</AZ01LID*><AZ01ID*>"+bean.getAz01ID()
                +"</AZ01ID*><AZ02ID*>"+bean.getAz02ID()+"</AZ02ID*>");
        return sbf.toString();
    }

    /**
     * 获取调用临时卡注销接口的参数
     * @return
     */
    public String cancelAllDsjkParam(String operation,String user,String pwd,FakaParamBean bean){
        StringBuffer sbf=new StringBuffer();
        sbf.append("<操作*>"+operation+"</操作*><用户名*>"+user+"</用户名*><密码*>"+pwd+"</密码*><所属城市*>"+bean.getCity()
                +"</所属城市*><经办机构*>"+bean.getOrgCode()+"</经办机构*><临时卡编号*>"+bean.getTempcardNo()
                +"</临时卡编号*><卡复位码*>"+bean.getAtr()+"</卡复位码*><注销类别*>"+bean.getCancelType()
                +"</注销类别*><代办人身份证号>"+bean.getAgentIdCard()
                +"</代办人身份证号><代办人姓名>"+bean.getAgentName()+"</代办人姓名>");
        return sbf.toString();
    }

    //临时卡发放失败
    public String failTemporaryCard(TempCardPersonVO bean)throws Exception{
        String user= Config.getInstance().get("card_user");
        String pwd=Config.getInstance().get("card_pwd");
        String operation="临时卡发放失败";
        logger.info("开始调用省厅接口");
        String param=failAllDsjkParam(operation, user, pwd, bean);//组装参数
        String res= CardServiceUtils.allDsjk(param);//调用省厅临时卡发放接口
        logger.info("调用结果："+res);
        res="<root>"+res+"</root>";
        Map<String,String> map= Dom4JUtil.readXmlToMap(res);
        String status=map.get("ERR");
        return status;
    }

    //临时卡发放--插入人员信息
    @Transactional("faka")
    public int addTempPersonInfo(TempCardPersonVO bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        logger.info("开始修改临时卡信息===");
        template.update(NAMESPACE + "updateTemporaryInfo", bean);//修改临时卡信息
        logger.info("修改临时卡信息成功===");
        TemporaryCardInfoBean bean2 = new TemporaryCardInfoBean();
        bean2.setTempcardNo(bean.getTempcardNo());
        List<TemporaryCardInfoVO> list = template.selectList(NAMESPACE + "getTemporaryCardList", bean2);//查询临时卡ID
        bean.setTempcardDetailId(list.get(0).getId());
        logger.info("开始插入人员信息===");
        template.insert(NAMESPACE + "addTempCardPersonInfo", bean);//插入人员信息
        logger.info("开始修改库存数量===");
        int i = template.update(NAMESPACE + "updateAddTempNum", bean);//修改库存数量
        return i;
    }

    //临时卡发注销
    @Transactional("faka")
    public int cancelTempCard(FakaParamBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        logger.info("开始修改临时卡信息===");
        template.update(NAMESPACE + "cancelTemporaryInfo", bean);//修改临时卡信息
        logger.info("开始修改人员信息===");
        template.update(NAMESPACE + "updateTempCardPersonInfo", bean);//修改人员信息
        logger.info("开始修改库存数量===");
        int i = template.update(NAMESPACE + "updateCancelTempoNum", bean);//修改库存数量
        return i;
    }

    //临时卡发收回
    @Transactional("faka")
    public int recycleTempCard(FakaParamBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        logger.info("开始修改临时卡信息===");
        template.update(NAMESPACE + "recycleTemporaryInfo", bean);//修改临时卡信息
        logger.info("开始修改人员信息===");
        template.update(NAMESPACE + "updateTempCardPersonInfo", bean);//修改人员信息
        logger.info("开始修改库存数量===");
        int i = template.update(NAMESPACE + "updateRecycleTempNum", bean);//修改库存数量
        return i;
    }


}
