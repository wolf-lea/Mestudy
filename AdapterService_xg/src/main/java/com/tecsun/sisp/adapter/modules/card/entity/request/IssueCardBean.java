package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**精准发卡
 * Created by danmeng on 2017/5/15.
 */
public class IssueCardBean extends SecQueryBean{
    private String sbkh;//社保卡号
    private String agentSfzh;//代办人身份证
    private String isPerson;//是否本人  0：本人，1：代他人办理
    private String agentXm;//代办姓名
    private long photoId;//本人相片ID
    private long signphotoId;//本人签名照ID
    private long agentphotoId;//代办人相片ID
    private long agentSignphotoId;//代办人签名照ID

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getAgentSfzh() {
        return agentSfzh;
    }

    public void setAgentSfzh(String agentSfzh) {
        this.agentSfzh = agentSfzh;
    }

    public String getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(String isPerson) {
        this.isPerson = isPerson;
    }

    public String getAgentXm() {
        return agentXm;
    }

    public void setAgentXm(String agentXm) {
        this.agentXm = agentXm;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public long getSignphotoId() {
        return signphotoId;
    }

    public void setSignphotoId(long signphotoId) {
        this.signphotoId = signphotoId;
    }

    public long getAgentphotoId() {
        return agentphotoId;
    }

    public void setAgentphotoId(long agentphotoId) {
        this.agentphotoId = agentphotoId;
    }

    public long getAgentSignphotoId() {
        return agentSignphotoId;
    }

    public void setAgentSignphotoId(long agentSignphotoId) {
        this.agentSignphotoId = agentSignphotoId;
    }
}
