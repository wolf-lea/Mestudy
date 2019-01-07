package com.tecsun.sisp.adapter.modules.card.entity.request;


/**卡检测
 * Created by danmeng on 2017/8/9.
 */
public class CardDetectBean extends CardBasicBean {
    private long detectId;//检测结果保存id
    private String detectResult;//检测结果 00--初始状态;01--成功;02--失败;
    private String detectInfo; //检测信息
    private String repairResult; //修复结果 00--初始状态;01--成功;02--失败;
    private String repairInfo; //修复信息

    public long getDetectId() {
        return detectId;
    }

    public void setDetectId(long detectId) {
        this.detectId = detectId;
    }

    public String getDetectResult() {
        return detectResult;
    }

    public void setDetectResult(String detectResult) {
        this.detectResult = detectResult;
    }

    public String getDetectInfo() {
        return detectInfo;
    }

    public void setDetectInfo(String detectInfo) {
        this.detectInfo = detectInfo;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    public String getRepairInfo() {
        return repairInfo;
    }

    public void setRepairInfo(String repairInfo) {
        this.repairInfo = repairInfo;
    }
}
