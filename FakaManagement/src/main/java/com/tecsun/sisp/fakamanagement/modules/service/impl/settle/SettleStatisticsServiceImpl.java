package com.tecsun.sisp.fakamanagement.modules.service.impl.settle;

import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.ChannelStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.SettleStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenlicong on 2017/12/26.
 */
@Service("settleStatisticsServiceImpl")
public class SettleStatisticsServiceImpl extends BaseService {

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.settle.SettleStatisticsServiceImpl.";

    //全市医保结算刷卡量统计
    public List<SettleStatisticsBean> getSettleCountByMonth()throws Exception{
        SqlSessionTemplate template = this.getSettleSqlSessionTemplate();
        List<SettleStatisticsBean> list = template.selectList(NAMESPACE + "getSettleCountByMonth");
        return list;
    }

    //各区县医保结算刷卡量统计
    public List<SettleStatisticsBean> getSettleCountByDistrict()throws Exception{
        SqlSessionTemplate template = this.getSettleSqlSessionTemplate();
        List<SettleStatisticsBean> list = template.selectList(NAMESPACE + "getSettleCountByDistrict");
        return list;
    }

}
