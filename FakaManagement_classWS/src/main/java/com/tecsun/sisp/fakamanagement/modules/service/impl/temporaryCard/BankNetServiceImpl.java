package com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.BankNetVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @Description:
 * @author: liang
 * @date 2018/3/21 11:12
 **/
@Service
public class BankNetServiceImpl extends BaseService {

    public static final Logger logger= Logger.getLogger(BankNetServiceImpl.class);

    /**
     * 获取卡盒信息
     * @param page
     * @param bean
     * @return
     */
    public Page<BankNetVO> getBankNetBoxList(Page<BankNetVO> page,BankNetVO bean){
        bean.setPage(page);
        List<BankNetVO> list=this.getFakaSqlSessionTemplate().selectList("getBankNetBoxList",bean);
        page.setData(list);
        return page;
    }

    /**
     * 获取一盒的卡信息
     * @param bean
     * @return
     */
    public List<BankNetVO> getBankNetCardList(BankNetVO bean){
        return this.getFakaSqlSessionTemplate().selectList("getBankNetCardList",bean);
    }

    /**
     * 修改卡盒表信息
     * @param vo
     * @return
     */
    public int updateCardboxByBoxNO(BankNetVO vo){
        return this.getFakaSqlSessionTemplate().update("updateCardboxByBoxNO",vo);
    }

    /**
     * 根据网点查询一条临时卡主表信息
     * @param rkwd
     * @return
     */
    public BankNetVO getTempCardInfo(Integer rkwd){
        return this.getFakaSqlSessionTemplate().selectOne("getTempCardInfo",rkwd);
    }

    /**
     * 修改网点入库主表
     * @param vo
     * @return
     */
    public int updateCardInfoByRkwd(BankNetVO vo){
        return this.getFakaSqlSessionTemplate().update("updateCardInfoByRkwd",vo);
    }

    /**
     * 临时卡主表增加一条记录
     * @param vo
     * @return
     */
    public int addCardInfo(BankNetVO vo){
        return this.getFakaSqlSessionTemplate().update("addCardInfo",vo);
    }

    /**
     * 修改临时卡明细表
     * @param vo
     * @return
     */
    public int updateCardDetailByCardNO(BankNetVO vo){
        return this.getFakaSqlSessionTemplate().update("updateCardDetailByCardNO",vo);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 银行下发临时卡到下级网点市
     * @param boxList
     * @param newRkwd
     * @return
     */
    @Transactional("faka")
    public Result issueCardProcess(List<BankNetVO> boxList,Integer newRkwd){
        try{
            BankNetVO bvo=new BankNetVO();

            bvo.setCardNOStart(boxList.get(0).getCardNOStart());
            bvo.setCardNOEnd(boxList.get(0).getCardNOEnd());
            bvo.setRkwd(newRkwd);
            //1、根据网点更新卡明细表 盒号，开始卡号，结束卡号
            this.updateCardDetailByCardNO(bvo);

            bvo.setBoxNO(boxList.get(0).getBoxNO());
            bvo.setStatus(Constants.TEMPCARD_BOX_02);//已下发
            //2、更新卡盒表
            this.updateCardboxByBoxNO(bvo);

            //这盒子实际的卡数量
            int count=boxList.get(0).getCardNOSum();

            /*Integer bank=Integer.valueOf(boxList.get(0).getBank());
            //市银行在临时卡主表的信息
            BankNetVO bankResult=this.getTempCardInfo(bank);
            BankNetVO bankVO = new BankNetVO();
            bankVO.setRkwd(bank);
            //发放数
            Integer cardGrantNum=bankResult.getCardGrantNum();
            //剩余数
            Integer cardSurplusNum=bankResult.getCardSurplusNum();
            bankVO.setCardGrantNum(cardGrantNum+count);
            bankVO.setCardSurplusNum(cardSurplusNum-count);
            //3.1、更新市银行在临时卡主表的信息
            this.updateCardInfoByRkwd(bankVO);*/

            BankNetVO rkwdVO = new BankNetVO();
            rkwdVO.setRkwd(newRkwd);

            //是否已有这个网点的信息
            BankNetVO countVO=this.getTempCardInfo(newRkwd);
            //3.2.1更新市银行下级网点在临时卡主表的信息
            if(countVO!=null){
                rkwdVO.setCardInstoreNum(countVO.getCardInstoreNum()+count);
                rkwdVO.setCardSurplusNum(countVO.getCardSurplusNum()+count);
                this.updateCardInfoByRkwd(rkwdVO);
            }else{
                //3.2.2添加市银行下级网点在临时卡主表的信息
                rkwdVO.setCardInstoreNum(count);
                rkwdVO.setCardSurplusNum(count);
                this.addCardInfo(rkwdVO);
            }
            logger.info("市银行下发临时卡到下级网点成功！");
            return new Result().ok("市银行下发临时卡到下级网点成功！");
        }catch (Exception e){
            logger.error("市银行下发临时卡到下级网点异常！",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new Result().error("市银行下发临时卡到下级网点异常！");
        }
    }

}
