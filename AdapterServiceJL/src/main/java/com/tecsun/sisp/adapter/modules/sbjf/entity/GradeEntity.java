package com.tecsun.sisp.adapter.modules.sbjf.entity;

/**
 * Created by linzetian on 2017/6/7.
 * 缴费档次
 */
public class GradeEntity {
    private String gradeCode;//档次编码
    private String gradeName;//档次名称
    private float gradeAmount;//金额

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public float getGradeAmount() {
        return gradeAmount;
    }

    public void setGradeAmount(float gradeAmount) {
        this.gradeAmount = gradeAmount;
    }
}
