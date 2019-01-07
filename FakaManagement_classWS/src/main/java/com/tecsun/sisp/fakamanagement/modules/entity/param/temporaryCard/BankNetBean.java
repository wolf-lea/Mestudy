package com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard;

/**
 * @Description:
 * @author: liang
 * @date 2018/3/21 10:57
 **/
public class BankNetBean {
    private String userid;
    private Integer rkwd;//入库网点id
    private String boxNO;
    private String[] boxNOs;
    private String cardNOStart;
    private String cardNOEnd;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getRkwd() {
        return rkwd;
    }

    public void setRkwd(Integer rkwd) {
        this.rkwd = rkwd;
    }

    public String getBoxNO() {
        return boxNO;
    }

    public void setBoxNO(String boxNO) {
        this.boxNO = boxNO;
    }

    public String[] getBoxNOs() {
        return boxNOs;
    }

    public void setBoxNOs(String[] boxNOs) {
        this.boxNOs = boxNOs;
    }

    public String getCardNOStart() {
        return cardNOStart;
    }

    public void setCardNOStart(String cardNOStart) {
        this.cardNOStart = cardNOStart;
    }

    public String getCardNOEnd() {
        return cardNOEnd;
    }

    public void setCardNOEnd(String cardNOEnd) {
        this.cardNOEnd = cardNOEnd;
    }
}
