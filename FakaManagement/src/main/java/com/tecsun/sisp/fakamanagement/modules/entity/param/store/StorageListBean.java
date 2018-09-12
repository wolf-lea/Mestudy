package com.tecsun.sisp.fakamanagement.modules.entity.param.store;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

import java.util.List;

/**
 * 入库管理入参
 * Created by xumaohao on 2018/1/24.
 */
public class StorageListBean extends BaseVO {
    private String userid;      //网点id
    private String bank;        //银行编码
    private String qy;          //区域编码
    private String ccaid;       //柜子id
    private List<detail> data;  //入库数据
    private String method;      //入库方式 00：按盒入库 01：按箱入库 02：按批次入库

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

    public List<detail> getData() {
        return data;
    }

    public void setData(List<detail> data) {
        this.data = data;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getCcaid() {
        return ccaid;
    }

    public void setCcaid(String ccaid) {
        this.ccaid = ccaid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
