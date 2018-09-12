package com.tecsun.sisp.fakamanagement.modules.service.impl.card;

import java.util.List;

import javax.smartcardio.Card;

import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.CardStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.*;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.CardStatisticsVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardChangeBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardStoreBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.FKWDBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.PrintLogVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
* @ClassName: CardInfoServiceImpl 
* @Description: TODO(卡信息数据处理实现类) 
* @author huangtao
* @date 2017年7月10日 上午11:39:18 
*
 */
@Service("cardInfoService")
public class CardInfoServiceImpl extends BaseService{

	/**
	 * mapper文件namespace属性值
	 */
	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl.";
	
	
	/**
	 * 添加卡信息到数据库
	 * @param list 卡信息的集合
	 * @return
	 */
	public int saveCardInfo(List<CardInfoVO> list) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertCards", list);
	}
	/**
	 * 查询卡信息及存放位置
	 * @param vo 查询条件对象
	 * @return
	 */
	public Page<CardInfoVO> queryCards(Page<CardInfoVO> page,CardInfoVO vo) {
		vo.setPage(page);
		List<CardInfoVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"queryCards", vo);
		page.setData(list);
		return page;
	}
    /**
     * 查询卡信息及存放位置by company
     * @param vo 查询条件对象
     * @return
     */
    public Page<CardInfoVO> queryCardsByCompay(Page<CardInfoVO> page,CardInfoVO vo) {
        vo.setPage(page);
        List<CardInfoVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"queryCardsByCompay", vo);
        page.setData(list);
        return page;
    }
	/**
	 * 获取批次号、箱数、盒数、卡数
	 * @param vo
	 * @return
	 */
	public List<CardBatchVO> getBactchs(CardBatchVO vo) {
		return this.getFakaSqlSessionTemplate().selectList(packageName+"getBactchs", vo);
	}
	
	/**
	 * 根据盒id查询盒最大卡数和现有卡数
	 * @param boxid
	 * @return
	 */
	public CardBoxVO queryBoxMaxAndCardNum(Integer boxid) {
		CardBoxVO vo=this.getFakaSqlSessionTemplate().selectOne(packageName+"queryBoxMaxAndCardNum",boxid);
		if (null==vo) {
			return new CardBoxVO();
		}
		if (null==vo.getCardnum()) {
			vo.setCardnum(0);
		}
		return vo;
	}
	
	/**
	 * 单张卡入库
	 * @param csvo
	 * @return
	 */
	public Integer cardStore(CardStoreVO csvo) {
		CardBoxVO bvo=queryBoxMaxAndCardNum(csvo.getCbid());
		if(bvo.getCardnum()>=bvo.getMaxnum()){//现有卡数量大于等于最大卡数 返回错误提示
			return -11;
		}
		return this.getFakaSqlSessionTemplate().insert(packageName+"cardStore", csvo);
	}
	
	/**
	 * 批量入库
	 * @param csvo
	 * @return
	 */
	public Integer cardStores(CardStoreBean csvo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"cardStores", csvo);
	}
	
	/**
	 * 卡转移
	 * @param ccb
	 * @return
	 */
	public Integer changeCardStore(CardChangeBean ccb) {
		return this.getFakaSqlSessionTemplate().update(packageName+"changeCardStore", ccb);
	}
	/**
	 * 删除卡存放信息
	 * @param ciid
	 * @return
	 */
	public Integer deleteCardStore(Integer ciid) {
		return this.getFakaSqlSessionTemplate().delete(packageName+"deleteCardStore", ciid);
	}
	/**
	 * 删除卡存放信息
	 * @param cbid
	 * @return
	 */
	public Integer deleteCardStoreByChangeFKWD(Integer cbid) {
		return this.getFakaSqlSessionTemplate().delete(packageName+"deleteCardStoreByChangeFKWD", cbid);
	}
	/**
	 * 查询存放位置
	 * @param ciid
	 * @return
	 */
	public CardStoreVO queryCardStore(Integer ciid) {
		return this.getFakaSqlSessionTemplate().selectOne(packageName+"queryCardStore", ciid);
	}
    /**
     * 去除当前发卡系统位置
     * @param ciid
     * @return
     */
    public Integer updateThisCardInfoXtzxwz(Integer ciid) {
        return this.getFakaSqlSessionTemplate().update(packageName+"updateThisCardInfoXtzxwz", ciid);
    }
    /**
     * 修改当前发卡卡序号
     * @param ciid
     * @return
     */
    public Integer updateThisCardStore(Integer ciid) {
        return this.getFakaSqlSessionTemplate().update(packageName+"updateThisCardStore", ciid);
    }
	/**
	 * 修改剩余发卡卡序号
	 * @param csvo
	 * @return
	 */
	public Integer updateCardStore(CardStoreVO csvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateCardStore", csvo);
	}
	/**
	 * 更改领卡号
	 * @param list
	 * @return
	 */
	public Integer updateReceiveNum(List<CardInfoVO> list) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateReceiveNum", list);
	}
	
	/**
	 * 修改状态
	 * @param csvo
	 * @return
	 */
	public Integer updateUserStatus(CardInfoVO csvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateUserStatus", csvo);
	}
	/**
	 * 批量入库修改状态
	 * @param vo
	 * @return
	 */
	public Integer updateStatusByCardStores(CardStoreBean vo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateStatusByCardStores", vo);
	}
	/**
	 * 获取领卡号
	 * @return
	 */
	public Integer getReceivenum() {
		return this.getFakaSqlSessionTemplate().selectOne(packageName+"getReceivenum");
	}
	/**
	 * 根据身份证姓名查询最近领取流水号
	 * @param vo
	 * @return
	 */
	public Integer queryMaxReceivenum(PrintLogVO vo) {
		return  this.getFakaSqlSessionTemplate().selectOne(packageName+"queryMaxReceivenum",vo);
	}
	/**
	 * 添加问题卡记录
	 * @param vo
	 * @return
	 */
	public Integer insertProblemCard(CardRetentionVO vo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertProblemCard", vo);
	}
	/**
	 * 问题卡处理
	 * @param vo
	 * @return
	 */
	public Integer handleProblemCard(CardRetentionVO vo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"handleProblemCard", vo);
	}
	/**
	 * 
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<CardRetentionVO> queryProblemCard(Page<CardRetentionVO> page, CardRetentionVO vo) {
		vo.setPage(page);
		List<CardRetentionVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"queryProblemCard", vo);
		page.setData(list);
		return page;
	}
	
	public Integer updateRetentiontime(CardInfoVO csvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateRetentiontime",csvo);
	}
	public Integer changeStore(CardStoreVO csvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"changeStore",csvo);
	}
	public Integer changeFKWD(CardInfoVO cvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"changeFKWD",cvo);
	}
	public Integer updateCardInfo(CardInfoVO vo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateCardInfo",vo);
	}
	public List<CardInfoVO> queryCompanys(CardInfoVO vo) {
		return this.getFakaSqlSessionTemplate().selectList(packageName+"queryCompanys",vo);
	}
	public Integer updateRetentionnum(CardInfoVO cvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateRetentionnum",cvo);
	}
	public Integer insertBankDatas(String s) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertBankDatas",s);
	}
	public Page<CardInfoVO> getBankInterface(Page<CardInfoVO> page,CardInfoVO vo) {
		vo.setPage(page);
		List<CardInfoVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"getBankInterface",vo);
		page.setData(list);
		return page;
	}
	public Integer getBankInterfaceNum() {
		return this.getFakaSqlSessionTemplate().selectOne(packageName+"getBankInterfaceNum");
	}
	public Integer updateBankStatus(String cardid) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateBankStatus",cardid);
	}

    //获取需要提醒经办人待找卡的数据
    public int getFindCardInfoByTime(FindCardInfoVO req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<FindCardInfoVO> list = template.selectList(packageName + "getFindCardInfoByTime", req);
        int count = list.size();
        return count;
    }

    public List<CardInfoVO> getNoFakaCardInfo() throws Exception{
        return this.getFakaSqlSessionTemplate().selectList(packageName + "getNoFakaCardInfo");
    }

    //同步卡状态信息
    @Transactional("faka")
    public int updataFakaStatus(CardInfoVO vo) throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        CardStoreVO storeVO = template.selectOne(packageName + "getFakaCardStoreInfo",vo);//查询需同步的卡数据位置信息
        int ciid = Integer.valueOf(storeVO.getCiid());
        template.update(packageName + "updateCardStore",storeVO);//大于该卡序号的减一
        template.update(packageName + "updateThisCardStore",ciid);//修改卡序号为0
        return template.update(packageName + "updataFakaStatus", vo);//修改该卡状态
    }
    /*public int updataFakaStatus(CardInfoVO vo) throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        return template.update(packageName + "updataFakaStatus", vo);//修改该卡状态
    }
    public int updataFakaStatus2(CardInfoVO vo) throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        return template.update(packageName + "updataFakaStatus2", vo);//标记同步状态状态
    }*/

}
