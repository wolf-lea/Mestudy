package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/11.
 */
public class IC09PO {
    private String AAC001;//个人编号
    private String AAB001;//单位编号
    private String AAE001;//年度
    private String AAE047;//生存认证日期
    private String AAE011;//经办人
    private String AAE036;//经办日期
    private String AAE038; //认证标志
    private String AAE013;//备注
    private String AAE278;//生存认证截至日期
    private String OAE001;//唯一索引
    private String PERSONID;//参保人主键
    private String identify_id;//人工审核id
    private String AAE279;//认证方式

	public String getAAE279() {
		return AAE279;
	}

	public void setAAE279(String aAE279) {
		AAE279 = aAE279;
	}

	public String getIdentify_id() {
        return identify_id;
    }

    public void setIdentify_id(String identify_id) {
        this.identify_id = identify_id;
    }

    public String getAAC001() {
        return AAC001;
    }

    public void setAAC001(String AAC001) {
        this.AAC001 = AAC001;
    }

    public String getAAB001() {
        return AAB001;
    }

    public void setAAB001(String AAB001) {
        this.AAB001 = AAB001;
    }

    public String getAAE001() {
        return AAE001;
    }

    public void setAAE001(String AAE001) {
        this.AAE001 = AAE001;
    }

    public String getAAE047() {
        return AAE047;
    }

    public void setAAE047(String AAE047) {
        this.AAE047 = AAE047;
    }

    public String getAAE011() {
        return AAE011;
    }

    public void setAAE011(String AAE011) {
        this.AAE011 = AAE011;
    }

    public String getAAE036() {
        return AAE036;
    }

    public void setAAE036(String AAE036) {
        this.AAE036 = AAE036;
    }

    public String getAAE038() {
        return AAE038;
    }

    public void setAAE038(String AAE038) {
        this.AAE038 = AAE038;
    }

    public String getAAE013() {
        return AAE013;
    }

    public void setAAE013(String AAE013) {
        this.AAE013 = AAE013;
    }

    public String getAAE278() {
        return AAE278;
    }

    public void setAAE278(String AAE278) {
        this.AAE278 = AAE278;
    }

    public String getOAE001() {
        return OAE001;
    }

    public void setOAE001(String OAE001) {
        this.OAE001 = OAE001;
    }

    public String getPERSONID() {
        return PERSONID;
    }

    public void setPERSONID(String PERSONID) {
        this.PERSONID = PERSONID;
    }

}
