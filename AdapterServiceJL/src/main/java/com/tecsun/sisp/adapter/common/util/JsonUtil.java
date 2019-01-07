package com.tecsun.sisp.adapter.common.util;

import java.util.Arrays;


import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardGrantBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardProblemBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardReplaceBean;

/**
* @author  wunuanchan
* @version 
* 创建时间：2017年6月5日 下午2:21:45
* 说明：
*/

public class JsonUtil {
	
	public static JSONObject getJsonCardStore(CardGrantBean bean){
		JSONObject json = new JSONObject();
		json.put("fkwd", bean.getFkwd());
		json.put("id",bean.getId()); 
		json.put("ccid",bean.getCcid()); 
		json.put("cfid",bean.getCfid()); 
		json.put("cbid",bean.getCbid()); 
		json.put("name",bean.getName()); 
		json.put("idcard",bean.getIdcard());
		json.put("cardid",bean.getCardid()); 
		json.put("companycode",bean.getCompanycode()); 
		json.put("companyname",bean.getCompanyname());
		json.put("batchno",bean.getBatchno()); 
		json.put("receivenum",bean.getReceivenum()); 
		json.put("status",bean.getStatus());
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("retentionnum",bean.getRetentionnum());
		
		
		return json;
	}

	public static JSONObject getJsonReceivePhotos(CardGrantBean bean){
		JSONObject json = new JSONObject();
		json.put("base64code",bean.getBase64code());
		json.put("type",bean.getType());
		json.put("photoname",bean.getPhotoname());
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		return json;
	}
	
	public static JSONObject getJsonCardReceive(CardGrantBean bean){
		JSONObject json = new JSONObject();
		json.put("idcard",bean.getIdcard());
		json.put("photo",bean.getPhoto());
		json.put("name",bean.getName());
		json.put("idcard_photo_down",bean.getIdcard_photo_down());
		json.put("idcard_photo_up",bean.getIdcard_photo_up());
		json.put("sign_photo",bean.getSign_photo());
		json.put("type",bean.getType());
        List<Integer> asList = Arrays.asList(bean.getIds());
		json.put("ids",asList);
		json.put("phone",bean.getPhone());
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("loginuserid",bean.getFkwd());
		return json;
	}
	public static JSONObject getJsonCardReceiveLog(CardGrantBean bean){
		JSONObject json = new JSONObject();
		json.put("idcard",bean.getIdcard());
		json.put("cardid",bean.getCardid());
		json.put("name",bean.getName());
		json.put("company_code",bean.getCompany_code());
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("userid",bean.getFkwd());	
		return json;
	}
	public static JSONObject getJsonReceiveNum(CardGrantBean bean){
		JSONObject json = new JSONObject();
		 List<Integer> asList = Arrays.asList(bean.getIds());
		json.put("ids",asList);
		json.put("idcard",bean.getIdcard());
		json.put("name",bean.getName());
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("loginuserid",bean.getFkwd());
		return json;
	}
	
	public static JSONObject getJsonCardStoreCompany(CardGrantBean bean){
		JSONObject json = new JSONObject();
		json.put("fkwd", bean.getFkwd()); 
		json.put("name",bean.getName()); 
		json.put("idcard",bean.getIdcard());
		json.put("cardid",bean.getCardid()); 
		json.put("companycode",bean.getCompanycode()); 
		json.put("companyname",bean.getCompanyname());
		json.put("batchno",bean.getBatchno()); 
		json.put("receivenum",bean.getReceivenum()); 
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("status",bean.getStatus());
		return json;
	}
	
	public static JSONObject getJsonGetFkwd(CardGrantBean bean){
		JSONObject json = new JSONObject();
		json.put("parentid",bean.getName());  
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		return json;
	}
	
	//ProblemCardController
	public static JSONObject getJsonProblemCard(CardProblemBean bean){
		JSONObject json = new JSONObject();
		json.put("name",bean.getName());	
        List<Integer> asList = Arrays.asList(bean.getIds());
		json.put("ids",asList);
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("cbid",bean.getCbid());
		json.put("loginuserid",bean.getLoginuserid());
		return json;
	}
	//ProblemCardController
	public static JSONObject getJsonRetentionCard(CardProblemBean bean){
		JSONObject json = new JSONObject();
		json.put("name",bean.getName());	
		json.put("channelcode",bean.getChannelcode());
		json.put("deviceid",bean.getDeviceid());
		json.put("tokenid",bean.getTokenid());
		json.put("loginuserid",bean.getLoginuserid());
		json.put("ciid",bean.getCiid());
		json.put("idcard",bean.getIdcard());
		json.put("cardid",bean.getCardid());
		json.put("retentiontime",bean.getRetentiontime());
		json.put("retentionuser",bean.getRetentionuser());
		json.put("handletime",bean.getHandletime());
		json.put("handle",bean.getHandle());
		json.put("status",bean.getStatus());
		json.put("userid",bean.getUserid());
		json.put("id",bean.getId());
		json.put("remark",bean.getRemark());
		return json;
	}
	
	//ProblemCardController
		public static JSONObject getJsonRetentionCardChange(CardProblemBean bean){
			JSONObject json = new JSONObject();	
			json.put("channelcode",bean.getChannelcode());
			json.put("deviceid",bean.getDeviceid());
			json.put("tokenid",bean.getTokenid());
			json.put("loginuserid",bean.getLoginuserid());
			json.put("cbid",bean.getCbid());
			json.put("orgcode",bean.getOrgcode());
			json.put("parentid",bean.getParentid());
			return json;
		}
	
		public static JSONObject getJsonCardAccount(CardGrantBean bean){
			JSONObject json = new JSONObject();
			json.put("fkwd", bean.getFkwd());
			json.put("channelcode",bean.getChannelcode());
			json.put("deviceid",bean.getDeviceid());
			json.put("tokenid",bean.getTokenid());
			json.put("startdate",bean.getStartdate());
			json.put("enddate",bean.getEnddate());
			return json;
		}
		
		
		public static JSONObject getJsonCabinets(CardGrantBean bean){
			JSONObject json = new JSONObject();
			json.put("userid", bean.getFkwd());
			json.put("channelcode",bean.getChannelcode());
			json.put("deviceid",bean.getDeviceid());
			json.put("tokenid",bean.getTokenid());
			return json;
		}
		
		public static JSONObject getJsonQueryStorage(CardGrantBean bean){
			JSONObject json = new JSONObject();
			json.put("userid", bean.getFkwd());
			json.put("channelcode",bean.getChannelcode());
			json.put("deviceid",bean.getDeviceid());
			json.put("tokenid",bean.getTokenid());
			json.put("batchNo",bean.getBatchno());
			json.put("bin",bean.getBin());
			json.put("box",bean.getBox());
			json.put("method",bean.getMethod());
			json.put("idcard", bean.getIdcard());
			return json;
		}
		
		public static JSONObject getJsonSelectStorage(CardGrantBean bean){
			JSONObject json = new JSONObject();
			json.put("userid", bean.getFkwd());
			json.put("channelcode",bean.getChannelcode());
			json.put("deviceid",bean.getDeviceid());
			json.put("tokenid",bean.getTokenid());
			json.put("method",bean.getMethod());
			json.put("ccaid",bean.getCcaid());
			json.put("data",bean.getData());
			return json;
		}
	

		public static JSONObject getJsonGetReplaceCardInfo(CardReplaceBean bean){
			JSONObject json = new JSONObject();
			json.put("name", bean.getName());
			json.put("idcard",bean.getIdcard());
			return json;
		}
		
		public static JSONObject getJsonMakeReplaceCard(CardReplaceBean bean){
			JSONObject json = new JSONObject();
			json.put("name", bean.getName());
			json.put("idcard",bean.getIdcard());
			json.put("district",bean.getDistrict());
			json.put("bankCode",bean.getBankCode());
			json.put("ks",bean.getKs());
			json.put("personTotal",bean.getPersonTotal());
			return json;
		}
		
		public static JSONObject getJsonReplyCardInfo(CardReplaceBean bean){
			JSONObject json = new JSONObject();
			json.put("name", bean.getName());
			json.put("idcard",bean.getIdcard());
			json.put("cardid",bean.getCardid());
			json.put("replaceType",bean.getReplaceType());
			json.put("replaceReason",bean.getReplaceReason());
			json.put("replacecardNo",bean.getReplacecardNo());
			json.put("atr",bean.getAtr());
			json.put("bankCode",bean.getBankCode());
			json.put("rkwd",bean.getRkwd());
			json.put("batchNo",bean.getBatchNo());
			json.put("fkrq",bean.getFkrq());
			json.put("kyxq",bean.getKyxq());
			json.put("ksbm",bean.getKsbm());
			json.put("bankAccount",bean.getBankAccount());
			json.put("account",bean.getAccount());
			json.put("zxwz",bean.getZxwz());
			json.put("failType",bean.getFailType());
			json.put("failReason",bean.getFailReason());
			json.put("photoUrl",bean.getPhotoUrl());
			json.put("personTotal",bean.getPersonTotal());	
			json.put("mobile",bean.getMobile());
			json.put("reginal",bean.getReginal());
			return json;
		}
	
	
		public static JSONObject getJsonCheckCardInfoByIdcard(CardReplaceBean bean){
			JSONObject json = new JSONObject();
			json.put("idcard",bean.getIdcard());
			return json;
		}
	
	


		public static JSONObject getJsonStorageRecover(CardGrantBean bean){
			JSONObject json = new JSONObject();
			json.put("userid", bean.getFkwd());
			json.put("channelcode",bean.getChannelcode());
			json.put("deviceid",bean.getDeviceid());
			json.put("tokenid",bean.getTokenid());
			json.put("id", bean.getId());
			json.put("ccaid",bean.getCcaid());	
			json.put("batchNo",bean.getBatchno());	
			json.put("bin",bean.getBin());	
			json.put("box",bean.getBox());	
			return json;
		}


}
