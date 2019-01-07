package com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean;

/**
 * Created by linzetian on 2017/6/19.
 * 生成缴费订单入参
 */
public class CreateFlowNumBean {
    private long prId;
    private String flowNum;//交易流水号
    private String channelcode;//渠道编码
    private String deviceid;//设备Id
    private String tokenid;//tokenId
    private String payNum;//缴费单号
    private String insureCode;//险种编码
    private Record[] records;//缴费明细记录
    private String sfzh;//身份证号
    private String xm;//姓名
    private float allAmount;//总额


    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public long getPrId() {
        return prId;
    }

    public void setPrId(long prId) {
        this.prId = prId;
    }

    public static class Record{
        private String status;//状态
        private long payId;//缴费记录Id
        private String gradeCode;//档次编码
        private String gradeName;//档次名称
        private float gradeAmount;//金额
        private String subInsureCode;//子险种编码

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getPayId() {
            return payId;
        }

        public void setPayId(long payId) {
            this.payId = payId;
        }

        public String getGradeCode() {
            return gradeCode;
        }

        public void setGradeCode(String gradeCode) {
            this.gradeCode = gradeCode;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public float getGradeAmount() {
            return gradeAmount;
        }

        public void setGradeAmount(float gradeAmount) {
            this.gradeAmount = gradeAmount;
        }

        public String getSubInsureCode() {
            return subInsureCode;
        }

        public void setSubInsureCode(String subInsureCode) {
            this.subInsureCode = subInsureCode;
        }


    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getInsureCode() {
        return insureCode;
    }

    public void setInsureCode(String insureCode) {
        this.insureCode = insureCode;
    }

    public Record[] getRecords() {
        return records;
    }

    public void setRecords(Record[] records) {
        this.records = records;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public float getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(float allAmount) {
        this.allAmount = allAmount;
    }

}
