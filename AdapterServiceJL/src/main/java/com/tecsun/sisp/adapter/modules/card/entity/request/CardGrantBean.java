package com.tecsun.sisp.adapter.modules.card.entity.request;

import java.util.List;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
* @author  wunuanchan
* @version 
* 创建时间：2017年12月25日 下午3:11:17
* 说明：精准发放
*/

public class CardGrantBean extends SecQueryBean{
	
	private String fkwd;//登录用户userid
	private Integer  id;//唯一编码 主键	
	private String  ccid;//柜名
	private Integer  cfid;//层号
	private Integer  cbid;//盒号
	private String  name;//姓名
	private String  idcard;//身份证号码
	private String  cardid;//社会保障卡卡号
	private String  companycode;//单位编码
	private String  companyname;//单位名称
	private String  batchno;//批次号
	private Integer  receivenum;//领卡流水号
	private Integer  status;//状态
	
	
	private String base64code;//照片base64code
	private Integer type;//照片类型；1头像2身份证正面3身份证反面4签名
	private String photoname;//照片名称 （要与addCardReceive接口的文件名相同且都不需要后缀）
	
	private String photo;//头像照片(上传的文件的名字)
	private String idcard_photo_down;//身份证反面(上传的文件的名字)
	private String idcard_photo_up;//身份证正面(上传的文件的名字)
	private String sign_photo;//签名(上传的文件的名字)
	private Integer[] ids;//人员id 单位领卡时传入多个
	private String phone;//联系电话
	
	private String company_code;//单位编码
	
	private String serviceNumber;//评价业务唯一编码
	private Integer[] cardReceiveIds;//发卡记录id
	
	
	private String parentid;//网点父级id
	
	private Integer retentionnum;//滞留次数
	
	private String startdate;//时间 起
	private String enddate;//时间 止
	
	private String bin;//箱号
	private String box;//盒号
	private String method;//入库方式：00：按盒入库 01：按箱入库 02：按批次入库
	
	private String ccaid;//柜子id
	
	private List<detail> data;  //入库数据
	
	
	public List<detail> getData() {
		return data;
	}
	public void setData(List<detail> data) {
		this.data = data;
	}
	public static class detail{
        private String batchNo;     //批次号
        private String bin;         //箱号
        private String box;         //盒号

        public String getBatchNo() {
            return batchNo;
        }

        public void setBatchNo(String batchNo) {
            this.batchNo = batchNo;
        }

        public String getBin() {
            return bin;
        }

        public void setBin(String bin) {
            this.bin = bin;
        }

        public String getBox() {
            return box;
        }

        public void setBox(String box) {
            this.box = box;
        }
    }

	
	
	public String getFkwd() {
		return fkwd;
	}
	public void setFkwd(String fkwd) {
		this.fkwd = fkwd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	public Integer getCbid() {
		return cbid;
	}
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public Integer getReceivenum() {
		return receivenum;
	}
	public void setReceivenum(Integer receivenum) {
		this.receivenum = receivenum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBase64code() {
		return base64code;
	}
	public void setBase64code(String base64code) {
		this.base64code = base64code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIdcard_photo_down() {
		return idcard_photo_down;
	}
	public void setIdcard_photo_down(String idcard_photo_down) {
		this.idcard_photo_down = idcard_photo_down;
	}
	public String getIdcard_photo_up() {
		return idcard_photo_up;
	}
	public void setIdcard_photo_up(String idcard_photo_up) {
		this.idcard_photo_up = idcard_photo_up;
	}
	public String getSign_photo() {
		return sign_photo;
	}
	public void setSign_photo(String sign_photo) {
		this.sign_photo = sign_photo;
	}
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public Integer[] getCardReceiveIds() {
		return cardReceiveIds;
	}
	public void setCardReceiveIds(Integer[] cardReceiveIds) {
		this.cardReceiveIds = cardReceiveIds;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public Integer getRetentionnum() {
		return retentionnum;
	}
	public void setRetentionnum(Integer retentionnum) {
		this.retentionnum = retentionnum;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getBox() {
		return box;
	}
	public void setBox(String box) {
		this.box = box;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCcaid() {
		return ccaid;
	}
	public void setCcaid(String ccaid) {
		this.ccaid = ccaid;
	}
	
	
	
	

}
