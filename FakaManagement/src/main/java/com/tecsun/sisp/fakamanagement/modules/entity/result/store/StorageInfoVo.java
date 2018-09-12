package com.tecsun.sisp.fakamanagement.modules.entity.result.store;

/**
 * 查询批次明细表 CARD_BATCH_DETAIL 出参
 * Created by xumaohao on 2018/1/26.
 */
public class StorageInfoVo {
    private String userid;          //用户id
    private String id;              //主键id
    private String batchId;         //批次主键
    private String batchBinBoxId;   //批次箱盒主键
    private String name;            //姓名
    private String idcard;          //身份证号码
    private String cardid;          //社会保障卡卡号
    private String batchNo;         //批次号
    private String companyCode;     //单位编码
    private String companyName;     //单位名称
    private String angent;          //联系人
    private String phone;           //联系电话
    private String address;         //联系地址
    private String kgZxwz;          //旧存放位置
    private String fkwd;            //发卡网点
    private String bank;            //所属银行
    private String city;            //城市代码
    private String xtZxwz;          //系统-装箱位置
    private String ccaid;           //柜号

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchBinBoxId() {
        return batchBinBoxId;
    }

    public void setBatchBinBoxId(String batchBinBoxId) {
        this.batchBinBoxId = batchBinBoxId;
    }

    public String getCcaid() {
        return ccaid;
    }

    public void setCcaid(String ccaid) {
        this.ccaid = ccaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAngent() {
        return angent;
    }

    public void setAngent(String angent) {
        this.angent = angent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKgZxwz() {
        return kgZxwz;
    }

    public void setKgZxwz(String kgZxwz) {
        this.kgZxwz = kgZxwz;
    }

    public String getFkwd() {
        return fkwd;
    }

    public void setFkwd(String fkwd) {
        this.fkwd = fkwd;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getXtZxwz() {
        return xtZxwz;
    }

    public void setXtZxwz(String xtZxwz) {
        this.xtZxwz = xtZxwz;
    }
}
