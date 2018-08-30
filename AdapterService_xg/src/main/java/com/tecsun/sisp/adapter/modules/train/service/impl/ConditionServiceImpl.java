package com.tecsun.sisp.adapter.modules.train.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainConditionBean;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xumaohao on 2017/9/6.
 */
@Service("ConditionServiceImpl")
public class ConditionServiceImpl  extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(TestTrainServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.train.service.impl.ConditionServiceImpl.";

    public Object seleceCondition4cssp() throws Exception {
        List<TrainConditionBean> condition = this.getSqlSessionTemplate().selectList(NAMESPACE + "selectCondition", null);

        JSONObject arrayList = new JSONObject();
        if(condition != null && condition.size() > 0) {
            List<String> profession = new ArrayList<>();
            List<String> date = new ArrayList<>();
            List<String> grade = new ArrayList<>();
            for (int i = 0; i < condition.size(); i++) {
                if (condition.get(i).getKey().equals("profession")) {
                    profession.add(condition.get(i).getValue());
                }else if(condition.get(i).getKey().equals("date")){
                    date.add(condition.get(i).getValue());
                }else if(condition.get(i).getKey().equals("grade")){
                    grade.add(condition.get(i).getValue());
                }
            }
            arrayList.put("profession",profession);
            arrayList.put("date",date);
            arrayList.put("grade",grade);
        }
        return arrayList;
    }
}
