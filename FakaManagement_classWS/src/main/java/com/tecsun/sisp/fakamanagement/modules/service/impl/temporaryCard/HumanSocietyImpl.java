package com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempCardBoxBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempCardDetailBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempCardInfoBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardBoxVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardDetailVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service("humanSocietyImpl")
public class HumanSocietyImpl extends BaseService {
    @Autowired
    private LoginUserServiceImpl loginUserService;

    public final static Logger logger = Logger.getLogger(HumanSocietyImpl.class);

    /**
     * mapper文件namespace属性值
     */
    private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.HumanSocietyImpl.";

    /**
     * 查询临时卡盒列表
     * @param bean 临时卡查询入参
     * @return
     */
    public Page<TempCardBoxVO> searchTempCardBox(TempCardBoxBean bean,Page<TempCardBoxVO> page) {
        bean.setPage(page);
        List<TempCardBoxVO> list= this.getFakaSqlSessionTemplate().selectList(packageName+"searchTempCardBox",bean);
        page.setData(list);
        return  page;
    }
    /**
     * 查询临时卡盒列表 用作判断 编号起止 以及 盒号重复
     * @param bean 临时卡查询入参
     * @return
     */
    public List<TempCardBoxVO> judgeTempCardBox(TempCardBoxBean bean) {
        return this.getFakaSqlSessionTemplate().selectList(packageName+"judgeTempCardBox",bean);
    }
    /**
     * 新增临时卡盒
     * @param bean 临时卡盒新增入参
     * @return
     */
    public int addTempCardBox(TempCardBoxBean bean) {
        return this.getFakaSqlSessionTemplate().insert(packageName+"addTempCardBox",bean);
    }
    /**
     * 修改临时卡盒列表状态和下发银行
     * @param bean 临时卡查询入参
     * @return
     */
    public  int updateTempCardBox(TempCardBoxBean bean) {
        return this.getFakaSqlSessionTemplate().update(packageName+"updateTempCardBox",bean);
    }
    /**
     * 修改临时卡盒列表的数量
     * @param bean 临时卡查询入参
     * @return
     */
    public  int updateTempCardBoxNum(TempCardBoxBean bean) {
        return this.getFakaSqlSessionTemplate().update(packageName+"updateTempCardBoxNum",bean);
    }
    /**
     * 新增临时卡入库主表
     * @param
     * @return
     */
    public int addTempCardInfo(TempCardInfoBean tempCardInfoBean) {
        return this.getFakaSqlSessionTemplate().insert(packageName+"addTempCardInfo",tempCardInfoBean);

    }
    /**
     * 查询临时卡入库主表记录
     * @param bean 临时卡查询入参
     * @return
     */
    public TempCardInfoVO searchTempCardInfo(TempCardInfoBean bean) {
        return this.getFakaSqlSessionTemplate().selectOne(packageName+"searchTempCardInfo",bean);
    }
    /**
     * 通过卡盒表的id 查询 临时卡网点入库主表
     * @param bean 临时卡查询入参
     * @return
     */
    public TempCardInfoVO searchTempCardInfoByBox(TempCardBoxBean bean) {
        return this.getFakaSqlSessionTemplate().selectOne(packageName+"searchTempCardInfoByBox",bean);
    }

    /**
     * 修改临时卡入库主表列表
     * @param bean 临时卡查询入参
     * @return
     */
    public  int updateTempCardInfo(TempCardInfoBean bean) {
        return this.getFakaSqlSessionTemplate().update(packageName+"updateTempCardInfo",bean);
    }

    /**
     * 新增临时卡明细记录
     * @param bean
     * @return
     */
    public int addTempCardDetail(TempCardDetailBean bean) {
        return this.getFakaSqlSessionTemplate().insert(packageName+"addTempCardDetail",bean);

    }
    /**
     * 查询临时卡明细记录
     * @param bean 临时卡查询入参
     * @return
     */
    public Page<TempCardDetailVO> searchTempCardDetail(TempCardDetailBean bean,Page<TempCardDetailVO> page) {
        bean.setPage(page);
        List<TempCardDetailVO> list= this.getFakaSqlSessionTemplate().selectList(packageName+"searchTempCardDetail",bean);
        page.setData(list);
        return  page;
    }
    /**
     * 删除临时卡明细记录
     * @param bean
     * @return
     */
    public int delTempCardDetail(TempCardDetailBean bean) {
        return this.getFakaSqlSessionTemplate().delete(packageName+"delTempCardDetail",bean);

    }
    /**
     * 删除临时卡明细记录
     * @param bean
     * @return
     */
    @Transactional("faka")
    public Result delTempCardDetailService(TempCardDetailBean bean,TempCardBoxVO tempCardBoxVO) {
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        try {
            String[] ids=bean.getIds().split(",");
            int num=ids.length;
            int a=delTempCardDetail(bean);
            if(a>0){
               /* TempCardInfoBean tempCardInfoBean=new TempCardInfoBean();
                tempCardInfoBean.setRkwd(Integer.valueOf(orgvo.getOrgid()));
                TempCardInfoVO tempCardInfoVO=searchTempCardInfo(tempCardInfoBean);

                tempCardInfoBean.setCardInstoreNum(tempCardInfoVO.getCardInstoreNum()-num);
                tempCardInfoBean.setCardSurplusNum(tempCardInfoVO.getCardSurplusNum()-num);
                updateTempCardInfo(tempCardInfoBean);
                logger.info("删除临时卡明细列表成功！");*/

                //修改入卡盒入库数量
                TempCardBoxBean tempCardBoxBean=new TempCardBoxBean();
                tempCardBoxBean.setBox(bean.getBox());
                tempCardBoxBean.setCardnoSum(tempCardBoxVO.getCardNum()-num);
                updateTempCardBoxNum(tempCardBoxBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            statusCode=Constants.RESULT_MESSAGE_EXCEPTION;
            message="删除临时卡明细列表异常："+message;
            logger.error(message);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new Result(statusCode,message,null);
    }
    /**
     * 删除临时卡卡盒记录
     * @param bean
     * @return
     */
    public int delTempCardBox(TempCardBoxBean bean) {
        return this.getFakaSqlSessionTemplate().delete(packageName+"delTempCardBox",bean);

    }
    /**
     * 新增卡盒表 作为 事务一
     * @param bean
     * @return
     */
    @Transactional("faka")
    public Result addTempCardInfoService(TempCardBoxBean bean){
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        try {
            //等待入库的卡盒的卡片总数
            long end =  Long.parseLong(bean.getCardEnd());
            long begin =  Long.parseLong(bean.getCardBegin());
            long cardInstoreNum = (end-begin+1);

            //新增临时卡盒记录
//            bean.setRkwd(Integer.parseInt(orgvo.getOrgid()));
            bean.setStatus(Constants.TEMPCARD_BOX_STATUS_00);
            bean.setCardnoSum((int) cardInstoreNum);
            addTempCardBox(bean);
            logger.info("批量插入 临时卡盒表结束");
        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            message= "新增临时卡盒入库主表异常："+message;
            logger.error(message);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            statusCode=Constants.RESULT_MESSAGE_EXCEPTION;

        }
        return new Result(statusCode,message,bean);
    }



    /**
     * 新增 入库主表 明细表  作为 事务二
     * @param bean
     * @return
     */
    @Transactional("faka")
    public Result addTempCardDetailService(TempCardBoxBean bean){
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        try {
            /*//等待入库的卡盒的卡片总数
            int cardInstoreNum = bean.getCardEnd() - bean.getCardBegin() + 1;
           //新增临时卡入库主表时  要判断是否已经存在该网点的记录 如果有则是进行数量修改  没有才新增
            TempCardInfoBean tempCardInfoBean = new TempCardInfoBean();
            tempCardInfoBean.setRkwd(Integer.parseInt(orgvo.getOrgid()));
            TempCardInfoVO tempCardInfoVO = searchTempCardInfo(tempCardInfoBean);
            if (tempCardInfoVO == null) {
                logger.info("该入库网点还没有入库记录，可以进行新增入库！");
                //入库主表的卡片总数
                tempCardInfoBean.setCardInstoreNum(cardInstoreNum);
                tempCardInfoBean.setCardSurplusNum(cardInstoreNum);
                addTempCardInfo(tempCardInfoBean);
            } else {
                logger.info("已经存在该入库网点的入库记录，在原来基础上进行数量修改！");
                //已经存在该网点的入库记录，在原来基础上进行数量修改
                int instoreNum = tempCardInfoVO.getCardInstoreNum();
                int surplusNum = tempCardInfoVO.getCardSurplusNum();
                instoreNum += cardInstoreNum;
                surplusNum += cardInstoreNum;
                tempCardInfoBean.setCardInstoreNum(instoreNum);
                tempCardInfoBean.setCardSurplusNum(surplusNum);
                updateTempCardInfo(tempCardInfoBean);
            }
            logger.info("批量插入 临时卡入库主表结束");*/
            //批量插入 临时卡入库明细
            //等待入库的卡盒的卡片总数
            long end =  Long.parseLong(bean.getCardEnd());
            long begin =Long.parseLong(bean.getCardBegin());
            for(long i=begin; i<=end; i++) {
                TempCardDetailBean tempCardDetailBean=new TempCardDetailBean();
                tempCardDetailBean.setTempcardNo(String.valueOf(i));
                tempCardDetailBean.setRkwd(0);
                tempCardDetailBean.setStatus(Constants.TEMPCARD_DETAIL_STATUS_00);
                addTempCardDetail(tempCardDetailBean);
            }
            logger.info("批量插入 临时卡入库明细结束");
        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            message= "新增临时卡盒入库明细异常："+message;
            logger.error(message);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            statusCode=Constants.RESULT_MESSAGE_EXCEPTION;

        }
        return new Result(statusCode,message,null);
    }

    /**
     * 新增卡盒表 以及  入库主表
     * @param bean
     * @return
     */
    @Transactional("faka")
    public Result deliveryTempCardBoxService(TempCardBoxBean bean){
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        try {
            String[] ids=bean.getIds().split(",");
            for(int i=0;i<ids.length;i++){
                //遍历要 下发的多个卡盒
                int id= Integer.parseInt(ids[i]);
                bean.setId(id);
                //查询 当前下发的卡盒所在的入库主表记录 比如（人社）  获取其剩余数量（不能以卡盒总数  要用入库主表的剩余数量）
                //还要查询当前卡盒的总数  便于 后面下发数量的计算 例如 已有数量+下发的卡盒总数
                TempCardInfoVO cardInfoVO=searchTempCardInfoByBox(bean);
                if(null==cardInfoVO){
                    message="查询不到id为"+id+"的 卡盒列表记录！";
                    logger.error(message);
                    statusCode=Constants.RESULT_MESSAGE_ERROR;
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return new Result(statusCode,message,bean);
                }else if (cardInfoVO.getStatus().equals(Constants.TEMPCARD_BOX_STATUS_01)){
                    //已接收的卡盒不能重复下发
                    message="该卡盒已接收，不能重复下发！";
                    logger.error(message);
                    statusCode=Constants.RESULT_MESSAGE_ERROR;
                    return new Result(statusCode,message,bean);
                }

                //下发 一 要修改卡盒表记录状态为 已接收  修改记录的下发银行编码
                bean.setStatus(Constants.TEMPCARD_BOX_STATUS_01);
                updateTempCardBox(bean);

               /* //下发 二 要新增一条入库主表记录 比如人社 --->  银行  的入库记录（入库网点为银行id）
                //  判断该银行网点是否存在入库记录
                TempCardInfoBean tempCardInfoBean = new TempCardInfoBean();
                tempCardInfoBean.setRkwd(bean.getBankId());
                TempCardInfoVO tempCardInfoVO = searchTempCardInfo(tempCardInfoBean);

                //银行不存在任何入库主表记录
                if (tempCardInfoVO == null) {
                    logger.info("该入库网点还没有入库记录，可以进行新增入库！");
                    //入库主表的卡片总数 使用卡盒的总数
                    tempCardInfoBean.setCardInstoreNum(cardInfoVO.getCardnoSum());//使用这个卡盒的总数
                    tempCardInfoBean.setCardSurplusNum(cardInfoVO.getCardnoSum());
                    addTempCardInfo(tempCardInfoBean);
                } else {
                    logger.info("已经存在该入库网点的入库记录，在原来基础上进行数量修改！");
                    //已经存在该网点的入库记录，在原来基础上进行数量修改
                    int instoreNum = tempCardInfoVO.getCardInstoreNum();
                    int surplusNum = tempCardInfoVO.getCardSurplusNum();
                    instoreNum += cardInfoVO.getCardnoSum();//原来基础上加上卡盒总数
                    surplusNum += cardInfoVO.getCardnoSum();
                    tempCardInfoBean.setCardInstoreNum(instoreNum);
                    tempCardInfoBean.setCardSurplusNum(surplusNum);
                    updateTempCardInfo(tempCardInfoBean);
                }
                //下发银行结束后 要修改上一层入库主表的剩余数量
                //剩余量 - 下发的卡盒数量= 入库主表的剩余数量
                TempCardInfoBean infoBean=new TempCardInfoBean();
                infoBean.setRkwd(cardInfoVO.getRkwd());
                infoBean.setCardSurplusNum(cardInfoVO.getCardSurplusNum()-cardInfoVO.getCardnoSum());
                int cardGrantNum=cardInfoVO.getCardGrantNum()+cardInfoVO.getCardnoSum();
                infoBean.setCardGrantNum(cardGrantNum);
                infoBean.setCardInstoreNum(cardInfoVO.getCardInstoreNum());
                updateTempCardInfo(infoBean);*/
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            logger.error("下发临时卡盒异常："+message);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return new Result(statusCode,message,null);
    }

}
