package com.tecsun.sisp.adapter.modules.sbjf.service.impl;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.sbjf.entity.GradeEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.Grades;
import com.tecsun.sisp.adapter.modules.sbjf.entity.PayRecordDetailEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.ToPayEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.GetListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linzetian on 2017/6/6.
 */
@Service("PayServiceImpl")
public class PayServiceImpl extends BaseService {

    @Autowired
    GradeServiceImpl gradeService;


    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayServiceImpl.";

    /**
     * 获取缴费清单
     */
    public Page<ToPayEntity> getToPayList4other(GetListBean bean) throws Exception{
        Page<ToPayEntity> page =  new Page<>(bean.getPageno(),bean.getPagesize());
        bean.setPage(page);

        List<ToPayEntity> list = this.getSqlSessionTemplate().selectList(NAMESPACE + "toPaylist", bean);

        if(list.size() > 0 ){
            page.setData(list);
            page = addGrades(bean.getInsureCode(),page);
        }
        return page;
    }

    /**
     * 根据一个payId获取一个待缴费信息
     * @param payId
     * @return
     * @throws Exception
     */
    public ToPayEntity getToPayInfo4other(long payId) throws Exception{
        ToPayEntity res = this.getSqlSessionTemplate().selectOne(NAMESPACE + "getToPayInfo", payId);
        return res;
    }

    /**
     * 更新缴费记录状态
     * @param records
     * @throws Exception
     */
    public void updateToPayList4other(String status,List<PayRecordDetailEntity> records) throws Exception {
        for (PayRecordDetailEntity record : records) {
            record.setStatus(status);
            updateToPayInfo4other(record);
        }
    }

    /**
     * 更新待缴费记录情况
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean updateToPayInfo4other(PayRecordDetailEntity bean) throws Exception{
        int count = this.getSqlSessionTemplate().update(NAMESPACE+"updateToPayInfo",bean);
        return count > 0 ? true :false;
    }


    /**
     * 根据身份号，姓名获取最新缴费单号
     */
    public String getPayNum4other(GetListBean bean) throws Exception{
        String payNum = this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPayNum", bean);
        return payNum;
    }

    /**
     * 往每条记录添加档次信息
     * @param insureCode
     * @param pages
     * @return
     * @throws Exception
     */
    public Page<ToPayEntity> addGrades(String insureCode,Page<ToPayEntity> pages) throws Exception{
        if(pages == null || pages.getData() ==null){
            return pages;
        }

        for (ToPayEntity item : pages.getData()) {
            String _insureCode = item.getSubInsureCode() != null ? insureCode+"_"+item.getSubInsureCode() : insureCode;
            List<GradeEntity> grades = getGrades(_insureCode);
            item.setGrades(grades);
        }
        return pages;
    }

    private List<GradeEntity> getGrades(String insureCode) throws Exception{
        List<GradeEntity> grades  = Grades.getInstance().getGrade(insureCode);
        if(grades == null){
            grades = gradeService.list4other(insureCode);

            if(grades == null || grades.size() ==0 ){
                throw new Exception("获取不到险种编码为"+insureCode+"对应的档次信息");
            }

            Grades.getInstance().add(insureCode,grades);
        }
        return grades;
    }
}
