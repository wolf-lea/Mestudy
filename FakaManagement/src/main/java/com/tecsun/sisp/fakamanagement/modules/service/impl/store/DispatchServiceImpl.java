package com.tecsun.sisp.fakamanagement.modules.service.impl.store;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.DispatchBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.DispatchVo;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分拨
 * Created by xumaohao on 2018/1/23.
 */
@Service("dispatchService")
public class DispatchServiceImpl extends BaseService {

    //mapper文件namespace属性值
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.store.DispatchServiceImpl.";

    private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl.";

    //日志
    public final static Logger logger = Logger.getLogger(DispatchServiceImpl.class);

    //查询社保卡分拨情况
    public Page<DispatchVo> queryDispatch(Page<DispatchVo> page,DispatchBean bean) throws Exception{
        //设置查询的页数页码
        bean.setPage(page);
        //查询数据库
        List<DispatchVo> list = this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "queryDispatch", bean);
        if(list != null && list.size() > 0) {
            //查询银行信息
            List<DistinctAndBankVO> bank = this.getUserSqlSessionTemplate().selectList(packageName + "queryBankInfo");
            //进行编码转换
            for (int i = 0; i < list.size(); i++) {
                //将fbStatusName（分拨状态）根据fbStatus（编码）转换
                String fbStatusName = list.get(i).getFbStatus().equals("01") ? "已分拨" : "未分拨";
                list.get(i).setFbStatusName(fbStatusName);
                if (bank != null && bank.size() > 0) {
                    //将查询结果的银行编码转换为中文
                    for (int j = 0; j < bank.size(); j++) {
                        if (list.get(i).getBank().equals(bank.get(j).getCode())) {
                            list.get(i).setBank(bank.get(j).getName());
                            break;
                        }
                    }
                }
            }
            page.setData(list);
        }
        return page;
    }

    //新增社保卡分拨
    @Transactional("faka")
    public Integer selectDispatch(DispatchBean bean) throws Exception{
        //修改【批次主表 CARD_BATCH】中的分拨状态
        this.getFakaSqlSessionTemplate().update(NAMESPACE + "updateCardBatch", bean);
        //记录操作日志【批次操作日志表 CARD_BATCH_LOG】
        return this.getFakaSqlSessionTemplate().insert(NAMESPACE + "insertCareBatchLog", bean);
    }
}
