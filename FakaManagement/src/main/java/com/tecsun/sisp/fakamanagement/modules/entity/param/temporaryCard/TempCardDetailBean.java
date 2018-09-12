package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;
import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

/**
 * 临时卡明细  入参
 * yangliu
 */
public class TempCardDetailBean extends BaseVO {
    private Integer id;	//		"主键
    private String ids;	//		"主键拼接
    private String tempcardNo;//临时卡编号
    private String atr;//卡复位码
    private Integer  rkwd;//入库网点
    private String status;//"状态 00-	初始（入库） 01-	已发放 02-	已注销（遗失） 03-	已作废（损坏）04-	已回收05-	注销（不在使用）
    private String wasteTime;//作废时间
    private String wastePosition;//作废位置
    private String cancelTime;//注销时间
    private String recoveryTime;//回收时间
    private String createTime;//创建时间
    private String updateTime;//修改时间
    private String cardBegin;//临时卡编号起
    private String cardEnd;//临时卡编号止
    private String box;//盒号
    private Integer userId;//当前登录用户id

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCardBegin() {
        return cardBegin;
    }

    public String getCardEnd() {
        return cardEnd;
    }

    public String getBox() {
        return box;
    }

    public void setCardBegin(String cardBegin) {
        this.cardBegin = cardBegin;
    }

    public void setCardEnd(String cardEnd) {
        this.cardEnd = cardEnd;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTempcardNo() {
        return tempcardNo;
    }

    public void setTempcardNo(String tempcardNo) {
        this.tempcardNo = tempcardNo;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }

    public Integer getRkwd() {
        return rkwd;
    }

    public void setRkwd(Integer rkwd) {
        this.rkwd = rkwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWasteTime() {
        return wasteTime;
    }

    public void setWasteTime(String wasteTime) {
        this.wasteTime = wasteTime;
    }

    public String getWastePosition() {
        return wastePosition;
    }

    public void setWastePosition(String wastePosition) {
        this.wastePosition = wastePosition;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(String recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
