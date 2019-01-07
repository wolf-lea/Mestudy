package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;
import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;
/**
 * 临时卡主表  入参
 * yangliu
 */
public class TempCardInfoBean extends BaseVO {
    private Integer Id;	//		"主键
    private Integer idKey;//新增后返回的id
    private Integer cardInstoreNum;//		"卡入库数量
    private Integer cardGrantNum;	//"卡发放数量
    private Integer cardSurplusNum;	//	"卡剩余数量
    private Integer recoveryNum;	//		"回收数量
    private Integer wasteCardNum;	//	"废卡数量
    private Integer cancelNum;	//		"注销数量
    private Integer rkwd;//	"入库网点(唯一)"

    public Integer getIdKey() {
        return idKey;
    }

    public void setIdKey(Integer idKey) {
        this.idKey = idKey;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCardInstoreNum() {
        return cardInstoreNum;
    }

    public void setCardInstoreNum(Integer cardInstoreNum) {
        this.cardInstoreNum = cardInstoreNum;
    }

    public Integer getCardGrantNum() {
        return cardGrantNum;
    }

    public void setCardGrantNum(Integer cardGrantNum) {
        this.cardGrantNum = cardGrantNum;
    }

    public Integer getCardSurplusNum() {
        return cardSurplusNum;
    }

    public void setCardSurplusNum(Integer cardSurplusNum) {
        this.cardSurplusNum = cardSurplusNum;
    }

    public Integer getRecoveryNum() {
        return recoveryNum;
    }

    public void setRecoveryNum(Integer recoveryNum) {
        this.recoveryNum = recoveryNum;
    }

    public Integer getWasteCardNum() {
        return wasteCardNum;
    }

    public void setWasteCardNum(Integer wasteCardNum) {
        this.wasteCardNum = wasteCardNum;
    }

    public Integer getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(Integer cancelNum) {
        this.cancelNum = cancelNum;
    }

    public Integer getRkwd() {
        return rkwd;
    }

    public void setRkwd(Integer rkwd) {
        this.rkwd = rkwd;
    }
}
