package com.tecsun.sisp.adapter.modules.train.entity.request;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by xumaohao on 2017/8/28.
 */
public class MessageSelectBean extends SecQueryBean {
    private List<String> profession;
    private List<String> grade;
    private List<String> date;

    public List<String> getProfession() {
        return profession;
    }

    public void setProfession(List<String> profession) {
        this.profession = profession;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        if(!(date.size()>0)){
            this.date = date;
        }
        for (int i = 0; i < date.size(); i++) {
            String month = Constants.TRAIN_MONTH_STATUS.get(date.get(i));
            date.set(i, StringUtils.isNotEmpty(month) ? month :date.get(i));
        }
        this.date = date;
    }
}
