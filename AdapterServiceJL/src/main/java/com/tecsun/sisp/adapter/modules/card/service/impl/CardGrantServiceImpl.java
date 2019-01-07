package com.tecsun.sisp.adapter.modules.card.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.card.entity.request.EvaAndGrantBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.MinorCardGrantBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.PrefabCardBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.TempCardBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.NetInfoVO;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;

/**
 * @author wunuanchan
 * @version 创建时间：2017年12月26日 下午6:47:29 说明：
 */
@Service("cardGrantService")
public class CardGrantServiceImpl extends BaseService {

	public static final Logger logger = Logger
			.getLogger(CardGrantServiceImpl.class);

	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.CardGrantServiceImpl.";

	// 卡检测保存
	public Integer evaluateAndGrant4Cssp(List<EvaAndGrantBean> list)
			throws Exception {

		return this.getSqlSessionTemplate().insert(
				NAMESPACE + "evaluateAndGrant", list);

	}

	// 网点信息查询
	public NetInfoVO queryNetInfo4Cssp(String code) {
		Map map = new HashMap();
		map.put("code", code);
		return this.getSqlSessionTemplate().selectOne(
				NAMESPACE + "queryNetInfo", map);
	}

	// 机构信息查询
	public NetInfoVO queryOrgInfo4Sisp(String code) {
		Map map = new HashMap();
		map.put("code", code);
		return this.getSqlSessionTemplate().selectOne(
				NAMESPACE + "queryOrgInfo", map);
	}

	// 通过fkwd(user_id)获取org_code
	public String selectLogName4sisp(String fkwd) {
		return this.getSqlSessionTemplate().selectOne(
				NAMESPACE + "selectLogName", fkwd);
	}

	// 插入未成年人发卡代领人信息
	public Integer insertInsteadCardInfo4faka(MinorCardGrantBean bean)
			throws Exception {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().insert(
					NAMESPACE + "insertInsteadCardInfo", bean);
		} catch (Exception e) {
			logger.error("存入未成年人发卡信息失败" + JsonHelper.javaBeanToJson(bean), e);
			status = -2;
		}
		return status;
	}

	// 插入未成年人发卡基本信息
	public Integer insertMinorCardBasicInfo4faka(MinorCardGrantBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().insert(
					NAMESPACE + "insertMinorCardBasicInfo", bean);
		} catch (Exception e) {
			logger.error("存入未成年人基本发卡信息失败" + JsonHelper.javaBeanToJson(bean), e);
			status = -2;
		}
		return status;
	}

	// 插入未成年人发卡时间、照片信息
	public Integer insertCardReceiveInfo4faka(MinorCardGrantBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().insert(
					NAMESPACE + "insertCardReceiveInfo", bean);
		} catch (Exception e) {
			logger.error("存入未成年人发卡时间、照片信息" + JsonHelper.javaBeanToJson(bean), e);
			status = -2;
		}
		return status;
	}

	// 在card_agent中获取agentid
	public Integer selectAgentidbyIdcard4faka(MinorCardGrantBean bean) {
		return this.getSqlSessionTemplate().selectOne(
				NAMESPACE + "selectAgentidbyIdcard", bean);
	}

	// 保存临时卡数据到TEMPCARD_DETAIL表中
	public Integer insertTempCardToDetail4faka(TempCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().insert(
					NAMESPACE + "insertTempCardToDetail", bean);
		} catch (Exception e) {
			logger.error(
					"存入临时卡信息到TEMPCARD_DETAIL失败"
							+ JsonHelper.javaBeanToJson(bean), e);
			status = -2;
		}
		return status;
	}

	// 保存临时卡数据到TEMPCARD_PERSON_INFO表中
	public Integer inserTempCardToPersonInfo4faka(TempCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().insert(
					NAMESPACE + "inserTempCardToPersonInfo", bean);
		} catch (Exception e) {
			logger.error(
					"存入临时卡信息到TEMPCARD_PERSON_INFO失败"
							+ JsonHelper.javaBeanToJson(bean), e);
			status = -2;
		}
		return status;
	}

	// 在TEMPCARD_DETAIL表中根据TEMPCARD_DETAIL获取id
	public Integer getDetailId4faka(String tempCardNo) {
		return this.getSqlSessionTemplate().selectOne(
				NAMESPACE + "getDetailId", tempCardNo);
	}

	// 存在TEMPCARD_NO的情况修改TEMPCARD_DETAIL表中的数据
	public Integer updateDetail4faka(TempCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().update(
					NAMESPACE + "updateDetail", bean);
		} catch (Exception e) {
			logger.error(
					"修改TEMPCARD_DETAIL信息失败！" + JsonHelper.javaBeanToJson(bean),
					e);
			status = -2;
		}
		return status;
	}

	// 根据社保卡号判断是否存在数据
	public Integer getPrefabCardId4faka(String cardid) {
		return this.getSqlSessionTemplate().selectOne(
				NAMESPACE + "getPrefabCardId", cardid);
	}
	// 存在预制卡唯一编号的情况修改REPLACECARD_DETAIL表中的数据
	public Integer updatePrefabCardId4faka(PrefabCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().update(
					NAMESPACE + "updatePrefabCardId", bean);
		} catch (Exception e) {
			logger.error(
					"修改REPLACECARD_DETAIL信息失败！" + JsonHelper.javaBeanToJson(bean),
					e);
			status = -2;
		}
		return status;
	}
	//保存预制卡数据到REPLACECARD_DETAIL表中
	public Integer insertPrefabCardId4faka(PrefabCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().update(
					NAMESPACE + "insertPrefabCardId", bean);
		} catch (Exception e) {
			logger.error(
					"修改REPLACECARD_DETAIL信息失败！" + JsonHelper.javaBeanToJson(bean),
					e);
			status = -2;
		}
		return status;
	}
	//保存预制卡数据到replacecard_person_info表中
	public Integer inserPrefabCardToPersonInfo4faka(PrefabCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().insert(
					NAMESPACE + "inserPrefabCardToPersonInfo", bean);
		} catch (Exception e) {
			logger.error(
					"保存replacecard_person_info信息失败！" + JsonHelper.javaBeanToJson(bean),
					e);
			status = -2;
		}
		return status;
	}
	//将未成年人发卡状态改为已发卡
	public Integer updateCardStatus4faka(MinorCardGrantBean bean) {
		return this.getSqlSessionTemplate().update(NAMESPACE+"updateCardStatus",bean);
	}
	//将预制卡状态改为已发卡
	public Integer updateReplaceCardStatus4faka(PrefabCardBean bean) {
		return this.getSqlSessionTemplate().update(NAMESPACE+"updateReplaceCardStatus",bean);
	}
	
	//判断card_info是否存在数据
	public Integer getCardInfoId4faka(MinorCardGrantBean bean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getCardInfoId", bean);
	}
	//修改未成年人发卡数据
	public Integer updateCardInfo4faka(MinorCardGrantBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().update(
					NAMESPACE + "updateCardInfo", bean);
		} catch (Exception e) {
			logger.error(
					"修改card_info信息失败！" + JsonHelper.javaBeanToJson(bean),
					e);
			status = -2;
		}
		return status;
	}
	
/*	//判断replacecard_person_info表中是否存在数据
	public Integer getPersonInfoId4faka(PrefabCardBean bean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getPersonInfoId", bean);
	}*/
	
/*	//修改replace_person_info表数据
	public Integer updatePersonInfo4faka(PrefabCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().update(
					NAMESPACE + "updatePersonInfo", bean);
		} catch (Exception e) {
			logger.error(
					"修改updatePersonInfo信息失败！" + JsonHelper.javaBeanToJson(bean),
					e);
			status = -2;
		}
		return status;
	}*/
	//获取org_id
	public Integer selectOrgId4sisp(String rkwd) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE+"selectOrgId",rkwd);
	}

	public Integer selectTempcardId4faka(TempCardBean bean) {
		int status = 0;
		try {
			status = this.getSqlSessionTemplate().selectOne(
					NAMESPACE + "selectTempcardId", bean);
		} catch (Exception e) {
			logger.error(
					"########查询失败########" + JsonHelper.javaBeanToJson(bean));
			status = -2;
		}
		return status;
	}
	
	// 查询卡数据是否已发卡
	public List<MinorCardGrantBean> selectIsCardReceive4faka(MinorCardGrantBean bean) {
			return this.getSqlSessionTemplate().selectList(
					NAMESPACE + "selectIsCardReceive", bean);
		}
}
