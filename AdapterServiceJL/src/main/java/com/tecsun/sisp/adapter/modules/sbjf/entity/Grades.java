package com.tecsun.sisp.adapter.modules.sbjf.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linzetian on 2017/5/17.
 * 档次信息
 */
public class Grades {
    private static volatile Grades instance;
    private static Map<String,List<GradeEntity>> grades = new HashMap<String, List<GradeEntity>>();

    private Grades(){
    }

    /**
     * 单例模式
     * @return
     */
    public static Grades getInstance(){
        if(instance == null){
            synchronized (Grades.class){
                if(instance == null)
                    instance = new Grades();
            }
        }
        return instance;
    }

    /**
     * 新增档次
     */
    public void add(String insureCode,List<GradeEntity> grade){
        if(grades == null){
            return;
        }
        grades.put(insureCode,grade);
    }

    /**
     * 根据险种编码获取档次
     * @return
     */
    public List<GradeEntity> getGrade(String insureCode){
        if(StringUtils.isEmpty(insureCode))
            return null;

        List<GradeEntity> grade = grades.get(insureCode);

        return grade;
    }
}
