package com.tecsun.sisp.fakamanagement.modules.service.impl.channel;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.ChannelStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.SatisfactionBean;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by chenlicong on 2017/12/26.
 */
@Service("channelStatisticsServiceImpl")
public class ChannelStatisticsServiceImpl extends BaseService {

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.channel.ChannelStatisticsServiceImpl.";

    //各渠道应用功能使用数量统计
    public List<ChannelStatisticsBean> getCountByChannel(ChannelStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getChannelSqlSessionTemplate();
        List<ChannelStatisticsBean> list = template.selectList(NAMESPACE + "getCountByChannel", req);
        for (ChannelStatisticsBean vo : list){
            //转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setCount(String.valueOf(f11));
        }
        return list;
    }

    //制卡进度查询统计
    public List<ChannelStatisticsBean> getMakeCardNum(ChannelStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getChannelSqlSessionTemplate();
        List<ChannelStatisticsBean> list = template.selectList(NAMESPACE + "getMakeCardNum", req);
        return list;
    }

    //精准发放发卡统计
    public List<ChannelStatisticsBean> getFakaNum(ChannelStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getChannelSqlSessionTemplate();
        List<ChannelStatisticsBean> list = template.selectList(NAMESPACE + "getFakaNum", req);
        //德生宝精准发放次数，为与满意度保持一致，数量减去差异数量
        if (list.size()>0) {
            for (ChannelStatisticsBean bean : list) {
                if (Integer.valueOf(bean.getCount())>=108){
                    int count = Integer.valueOf(bean.getCount()) - Integer.valueOf(Config.getInstance().get("tsb_grant_number"));
                    bean.setCount(String.valueOf(count));
                    System.out.println("德生宝精准发放次数，为与满意度保持一致，数量减去差异数量");
                }
            }
        }
        return list;
    }

    //查询银行满意度信息
    public List<SatisfactionBean> getSatisfactionInfo(SatisfactionBean req)throws Exception{
        SqlSessionTemplate template = this.getChannelSqlSessionTemplate();
        List<SatisfactionBean> list = template.selectList(NAMESPACE + "getSatisfactionInfo", req);
        return list;
    }

}
