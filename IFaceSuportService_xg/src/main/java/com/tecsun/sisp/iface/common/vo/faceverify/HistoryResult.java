package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/15.
 */
public class HistoryResult {
    private String person_name;
    private String business_time;
    private String type;
    private String status;
    private String id_card;

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null){
            if (type.equals("01")){
                this.type ="人脸认证";
            }else {
                this.type = "声纹认证";
            }
        } else {
            this.type = "";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null){
            if (status.equals("00")){
                this.status = "认证成功";
            }else{
                this.status = "认证失败";
            }
        }else{
            this.status = "";
        }
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
}
