package com.tecsun.sisp.adapter.modules.cyt.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.DiseaseBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.DiseaseDirectoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xumaohao on 2017/7/28.
 */

@Service("DiseaseServiceImpl")
public class DiseaseServiceImpl extends BaseService {

    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.cyt.service.impl.DiseaseServiceImpl.";

    /**
     * 获取疾病目录
     * @param bean
     * @return
     * @throws Exception
     */
    public List<DiseaseDirectoryBean> DiseaseSelect4other(DiseaseBean bean) throws Exception{
//        Page<DiseaseDirectoryBean> page =  new Page<>(bean.getPageno(),bean.getPagesize());
//        bean.setPage(page);

        List<DiseaseDirectoryBean> diseaseDirectory = this.getSqlSessionTemplate().selectList(NAMESPACE + "diseaseSelect", bean);

//        if(diseaseDirectory.size() > 0 ){
//            page.setData(diseaseDirectory);
//        }
        return diseaseDirectory;
    }

}
