package com.tecsun.sisp.fakamanagement.modules.service.impl.replacement;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceCardDetailBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceCardStorageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceCardDetailVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceCardStorageVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.BankNetVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardBoxVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;

/**
 * @author tanzhiyong
 * @version 创建时间：2018年10月23日 上午09:47:29 说明：
 */
@Service("replaceCardStorageServiceImpl")
public class ReplaceCardStorageServiceImpl extends BaseService {

	public static final Logger logger = Logger
			.getLogger(ReplaceCardStorageServiceImpl.class);

	public final static String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.replacement.ReplaceCardStorageServiceImpl.";

	// 插入预制卡数据
	public Integer insertReplaceCardInfo(ReplaceCardStorageBean bean) {
		int status = 0;
		try {
			status = this.getFakaSqlSessionTemplate().update(
					NAMESPACE + "insertReplaceCardInfo", bean);
		} catch (Exception e) {
			logger.error("保存数据到表REPLACECARD_BOX失败", e);
			status = -2;
		}
		return status;
	}

	// 修改入库网点
	public Integer updateReplaceDetail(ReplaceCardStorageBean bean) {
		int status = 0;
		try {
			status = this.getFakaSqlSessionTemplate().update(
					NAMESPACE + "updateReplaceDetail", bean);
		} catch (Exception e) {
			logger.error("保存数据到表REPLACECARD_DETAIL失败", e);
			status = -2;
		}
		return status;
	}

	// 修改银行
	public Integer updateReplaceCardBoxInfo(ReplaceCardStorageBean bean) {
		int status = 0;
		try {
			status = this.getFakaSqlSessionTemplate().update(
					NAMESPACE + "updateReplaceCardInfo", bean);
		} catch (Exception e) {
			logger.error("修改数据失败————REPLACECARD_BOX", e);
			status = -2;
		}
		return status;
	}

	// 根据ID获取数据
	public ReplaceCardStorageVO getReplaceCardInfobyId(Integer id) {
		return this.getFakaSqlSessionTemplate().selectOne(
				NAMESPACE + "getReplaceCardInfobyId", id);
	}

	// 判断BoxNo是否唯一
	public String getBoxId(String boxNo) {
		return this.getFakaSqlSessionTemplate().selectOne(
				NAMESPACE + "getBoxId", boxNo);
	}

	// 修改入库网点
	public Integer updateReplaceCardBox(ReplaceCardStorageBean bean) {
		return this.getFakaSqlSessionTemplate().insert(
				NAMESPACE + "updateReplaceCardBox", bean);
	}

	// 获取银行编码
	public String getBankName(String bankCode) {
		return this.getFakaSqlSessionTemplate().selectOne(
				NAMESPACE + "getBankName",bankCode);
	}

	// 获取卡盒信息
	public Page<ReplaceCardStorageVO> getBankNetBoxList(Page<ReplaceCardStorageVO> page, ReplaceCardStorageVO bean) {
		bean.setPage(page);
		List<ReplaceCardStorageVO> list = this.getFakaSqlSessionTemplate().selectList(NAMESPACE+"getBankNetBoxList", bean);
		page.setData(list);
		return page;
	}
	//查询预制卡盒号列表
	public Page<ReplaceCardStorageVO> getReplaceCardBox(
			ReplaceCardStorageBean bean, Page<ReplaceCardStorageVO> page) {
		bean.setPage(page);
        List<ReplaceCardStorageVO> list= this.getFakaSqlSessionTemplate().selectList(NAMESPACE+"getReplaceCardBox",bean);
        page.setData(list);
        return  page;
	}

	public DistinctAndBankVO getFkwdById(String bankNo) {
	        return this.getFakaSqlSessionTemplate().selectOne(NAMESPACE+"getFkwdById", bankNo);
	    }
	// 根据userid获取银行编码
	public String getBankNamebyUserid(Integer userid) {
		return this.getFakaSqlSessionTemplate().selectOne(
				NAMESPACE + "getBankNamebyUserid",userid);
	}
	//新增ReplaceCard_Detail数据
	public Integer insertReplaceCardDetail(
			ReplaceCardDetailBean bean) {
		int status = 0;
		try {
			status = this.getFakaSqlSessionTemplate().update(
					NAMESPACE + "insertReplaceCardDetail", bean);
		} catch (Exception e) {
			logger.error("新增数据到表REPLACECARD_DETAIL失败", e);
			status = -2;
		}
		return status;
	}
	//修改REPLACECARD_BOX表状态
	public Integer updateReplaceCardDetailStatus(ReplaceCardStorageBean bean) {
		return this.getFakaSqlSessionTemplate().update(NAMESPACE+"updateReplaceCardDetailStatus",bean);
	}

	//获取银行网点信息
	public Page<ReplaceCardDetailVO> getBankOutletReplaceCard(
			Page<ReplaceCardDetailVO> page, ReplaceCardDetailBean bean) {
		bean.setPage(page);
        List<ReplaceCardDetailVO> list= this.getFakaSqlSessionTemplate().selectList(NAMESPACE+"getBankOutletReplaceCard",bean);
        page.setData(list);
        return  page;
	}

	public ORGVO getRkwd(String userid) {
		return this.getFakaSqlSessionTemplate().selectOne(NAMESPACE+"getRkwd",userid);
	}
	//判断卡编号是否存在
	public List<String> getBoxIdbyReplaceCardNo(ReplaceCardStorageBean bean) {
		return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getBoxIdbyReplaceCardNo", bean);
	}
	//判断有无已下发的预制卡
	public List<String> getReplaceCardDetailListId(ReplaceCardStorageBean bean) {
		return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getReplaceCardDetailListId", bean);
	}
}
