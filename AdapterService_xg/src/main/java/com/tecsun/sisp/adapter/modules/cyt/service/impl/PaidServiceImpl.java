package com.tecsun.sisp.adapter.modules.cyt.service.impl;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.PaidBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.PaidDirectoryBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xumaohao on 2017/7/28.
 */

@Service("PaidServiceImpl")
public class PaidServiceImpl extends BaseService {

    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.cyt.service.impl.PaidServiceImpl.";

    /**
     * 获取收费目录
     * @param bean
     * @return
     * @throws Exception
     */
    public Page<PaidDirectoryBean> PaidSelect4other(PaidBean bean) throws Exception{

        Page<PaidDirectoryBean> page =  new Page<>(bean.getPageno(),bean.getPagesize());

        //不分页
        if(bean.getPageno() == -1 || bean.getPagesize() == -1){
            List<PaidDirectoryBean> PaidDirectory = this.getSqlSessionTemplate().selectList(NAMESPACE + "paidSelect", bean);
            bean.setPage(page);

            if(PaidDirectory.size() > 0 ){
                page.setPageno(1);
                page.setPagesize(PaidDirectory.size());
                page.setData(PaidDirectory);
                page.setCount(PaidDirectory.size());
            }
        }
        //分页
        else{
            bean.setPage(page);

            List<PaidDirectoryBean> PaidDirectory = this.getSqlSessionTemplate().selectList(NAMESPACE + "paidSelect", bean);

            if(PaidDirectory.size() > 0 ){
                page.setData(PaidDirectory);
            }
        }

        return page;
    }

}
