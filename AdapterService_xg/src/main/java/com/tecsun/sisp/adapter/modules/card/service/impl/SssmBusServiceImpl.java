package com.tecsun.sisp.adapter.modules.card.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.modules.card.entity.request.AreaManage;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardGetBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.IssueApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.ResultBusMakeDetal;
import com.tecsun.sisp.adapter.modules.card.entity.request.RetentionBinBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.RetentionBoxBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.RetentionCardBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.XgApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardLocationVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.ResultXhcVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.RetentionCardVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.SocialOrgVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.SssmInfoVO;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.so.entity.response.TSBSssmVO;

/**
 */
@Service("SssmBusService")
public class SssmBusServiceImpl extends BaseService {
	public static final Logger logger = Logger.getLogger(SssmBusServiceImpl.class);

	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.SssmBusServiceImpl.";

	// 登录滞留卡管理
	public int isLoginSssm4Sssm(String loginName, String password) throws Exception {
		Map map = new HashMap();
		map.put("loginName", loginName);
		map.put("password", password);
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "isLoginSssm", map);
	}

	// 查询卡位置
	public List<CardLocationVO> getCardLocationList4Sssm(String sfzh, String xm) throws Exception {
		Map map = new HashMap();
		map.put("sfzh", sfzh);
		map.put("xm", xm);
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCardLocation", map);
	}

	// 查询滞留卡信息
	public List<RetentionCardVO> getCardInfo4Sssm(String sfzh, String sbkh) throws Exception {
		Map map = new HashMap();
		map.put("sfzh", sfzh);
		map.put("sbkh", sbkh);
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCardInfo", map);
	}

	// 查询卡片实时数量
	public List<RetentionCardVO> getRetentionCount4Sssm(RetentionCardBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getRetentionCount", bean);
	}

	// 新增滞留卡、零散卡
	public int insertRetentionCard4Sssm(RetentionCardBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertRetentionCard", bean);
	}

	// 查询社保机构信息
	public TSBSssmVO getOrgInfo4Sssm(long orgId) throws Exception {
		Map map = new HashMap();
		map.put("orgId", orgId);
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getOrgInfo", map);
	}

	// 查询所有社保机构信息
	public List<TSBSssmVO> getAllOrgInfo4Sssm() throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getOrgInfo");
	}

	// 新增精准发卡申领
	public int insertIssueApplyCard4Sssm(IssueApplyBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertIssueApplyCard", bean);
	}

	// 更改滞留卡盒信息(增加数量)
	public int updateRetentionBox4Sssm(long boxId) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "updateRetentionBox", boxId);
	}

	// 更改滞留卡盒信息(减少数量)
	public int updateLowRetentionBox4Sssm(long boxId) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "updateLowRetentionBox", boxId);
	}

	// 根据社保卡号查询卡片位置
	public CardLocationVO getCardSN4Sssm(String sbkh) throws Exception {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCardSN", sbkh);
	}

	// 更改滞留卡片信息(精准发卡后其余卡片位置)
	public int updateIssueCard4Sssm(long boxId, int cardsn) throws Exception {
		Map map = new HashMap();
		map.put("boxId", boxId);
		map.put("cardsn", cardsn);
		return this.getSqlSessionTemplate().insert(NAMESPACE + "updateIssueCard", map);
	}

	// 根据社保卡号更改滞留卡片信息
	public int updateRetentionCard4Sssm(String sbkh, String sfzh, String status, String regisStatus)
			throws Exception {
		Map map = new HashMap();
		map.put("sbkh", sbkh);
		map.put("sfzh", sfzh);
		map.put("status", status);// 卡片状态 0在盒中1分散中2已发放
		map.put("regisStatus", regisStatus);// 滞留状态1， 待发放 0已发
		return this.getSqlSessionTemplate().insert(NAMESPACE + "updateRetentionCard", map);
	}

	// 查询当前箱、盒、卡信息(最大的箱、盒、卡号)
	public List<ResultXhcVO> getCurrentCardBoxBinInfo4Sssm(String orgName) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCurrentCardBoxBinInfo",
				orgName);
	}

	// 插入箱子
	public int insertBin4Sssm(RetentionBinBean binVO) {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertBin", binVO);
	}

	// 插入盒子
	public int insertBox4Sssm(RetentionBoxBean boxVO) {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertBox", boxVO);
	}

	// 更改滞留卡箱信息(增加数量)
	public int updateRetentionBin4Sssm(long binId) {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "updateRetentionBin", binId);
	}

	// 滞留卡领取
	public int cardGet4Sssm(CardGetBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "cardGet", bean);
	}

	// 滞留卡领取
	public List<Map<String, String>> getCardNum4Sssm(CardGetBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getCardNum", bean);
	}

	/**
	 * 
	 * @author 作者名 付伟锋
	 * @date 日期 2017- 08- 07 15:22
	 * @version 版本标识 V.1.0.0
	 * @parameter 参数及其意义
	 * @return 返回值
	 * @throws IOException
	 * @throws WriteException
	 * @throws 异常类及抛出条件
	 * @方法说明-判断是否符合补换卡申领条件 过程：1、调省厅补换卡系统中的制卡进度接口；
	 *                     2、调补换卡系统中的BASIC_PERSON_INFO表中是否有此人的信息
	 *                     结果：两者中都没有数据才能资格符合申领条件，否则提示暂无资格审领
	 */
	public List<SssmInfoVO> canApply4sssm(SecQueryBean bean) throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "canApply", bean);
	}

	public List<AreaManage> queryByParentId4sssm(String regionalcode) {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "queryByParentId", regionalcode);
	}
	
	public AreaManage queryById4sssm(String id) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "queryById", id);
	}

	public SocialOrgVO queryIssuingOrg4sssm(String regionalId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("regionalId", regionalId);
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "queryIssuingOrg", params);
	}
	public int insertBasePersonInfo4sssm(XgApplyBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertBasePersonInfo", bean);
	}

	public int insertBusPersonalApply4sssm(XgApplyBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertBusPersonalApply", bean);
	}

	public int insertBusHisInfo4sssm(XgApplyBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertBusHisInfo", bean);
	}

	public int updateBasePersonInfo4sssm(XgApplyBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "updateBasePersonInfo", bean);
	}
	
	/**
	 * 查卡位置
	 * @param certNum
	 * @return
	 */
	public  ResultBusMakeDetal getCardwz4sssm(String certNum) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getCardwz", certNum);
	}
}
