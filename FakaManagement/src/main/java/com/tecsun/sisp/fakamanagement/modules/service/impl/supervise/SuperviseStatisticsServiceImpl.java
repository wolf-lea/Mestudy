package com.tecsun.sisp.fakamanagement.modules.service.impl.supervise;

import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.SettleStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.CardStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.DayMontCradCountBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.CardStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.ChannelAttentionVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.DayMontCradCountVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.UserFunctionVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by chenlicong on 2018/3/15.
 */
@Service("superviseStatisticsServiceImpl")
public class SuperviseStatisticsServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.supervise.SuperviseStatisticsServiceImpl.";

    public final static Logger logger = Logger.getLogger(SuperviseStatisticsServiceImpl.class);

    //获取分配给用户的统计功能列表
    public List<UserFunctionVO> getFunctionByUserId(UserFunctionVO req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<UserFunctionVO> list = template.selectList(NAMESPACE + "getFunctionByUserId", req);
        return list;
    }

    //配置统计功能排序
    public int updateFuncSortById(List<UserFunctionVO> list)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        template.update(NAMESPACE + "updateFuncByUserId", list.get(0));//清空排序
        int i = template.update(NAMESPACE + "updateFuncSortById", list);//修改排序
        return i;
    }

    //今日、本月发卡总量
    public DayMontCradCountVO getDayMontCradCount(DayMontCradCountBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        DayMontCradCountVO vo = template.selectOne(NAMESPACE + "getDayFakaCount", bean);
        DayMontCradCountVO vo1 = template.selectOne(NAMESPACE + "getMonthFakaCount", bean);
        vo.setMonthFakaCount(vo1.getMonthFakaCount());
        return vo;
    }

    //总卡量统计
    public CardStatisticsVO getCardCountInfo(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        CardStatisticsVO response = template.selectOne(NAMESPACE + "getFakaCount", req);//已发卡量统计
        CardStatisticsVO res = template.selectOne(NAMESPACE + "getCardCount", req);//总卡量统计
        response.setCardCount(res.getCardCount());
        //String notfakaCount = String.valueOf(Integer.parseInt(res.getCardCount()) - Integer.parseInt(response.getFakaCount()));//未发卡量
        double notfakaCount = res.getCardCount() - response.getFakaCount();//未发卡量
        response.setNotfakaCount(notfakaCount);
        return response;
    }

    //各银行已、未发量统计
    public List<CardStatisticsVO> getCardCountByBank(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStatisticsVO> list = template.selectList(NAMESPACE + "getBankCardCount", req);
        for (CardStatisticsVO vo : list){
            req.setBank(vo.getBank());
            CardStatisticsVO vo1 = template.selectOne(NAMESPACE + "getCardCountByBank", req);
            vo.setFakaCount(vo1.getFakaCount());
            //String notfakaCount = String.valueOf(Integer.parseInt(vo.getCardCount()) - Integer.parseInt(vo.getFakaCount()));//未发卡量
            double notfakaCount = vo.getCardCount() - vo.getFakaCount();//未发卡量
            vo.setNotfakaCount(notfakaCount);

            /*//转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getFakaCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setFakaCount(f11);
            //转化为万，保留两位小数
            double f2 = Double.valueOf(vo.getNotfakaCount())/10000;
            BigDecimal b2 = new BigDecimal(f2);
            double f22 = b2.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setNotfakaCount(f22);*/
        }
        return list;
    }

    //全市每年医保结算刷卡量统计
    public List<SettleStatisticsBean> getTempSettleCountByYear(SettleStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<SettleStatisticsBean> list = template.selectList(NAMESPACE + "getTempSettleCountByYear", req);
        /*for (SettleStatisticsBean vo : list){
            //转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setCount(String.valueOf(f11));
        }*/
        return list;
    }

    //滞留卡统计-按年
    public List<CardStatisticsVO> getRetentionCountByYear(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStatisticsVO> list = template.selectList(NAMESPACE + "getRetentionCountByYear", req);
        return list;
    }

    //滞留卡发放比例
    public CardStatisticsVO getRetentionFakaCount(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        CardStatisticsVO response = template.selectOne(NAMESPACE + "getRetentionCount", req);//滞留卡总量
        CardStatisticsVO res = template.selectOne(NAMESPACE + "getRetentionFakaCount", req);//滞留卡发卡总量
        response.setFakaCount(res.getFakaCount());
        response.setNotfakaCount(response.getCardCount()-response.getFakaCount());
        return response;
    }

    //每年参保总量统计
    public List<CardStatisticsVO> getApplyCountByYear(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStatisticsVO> list = template.selectList(NAMESPACE + "getApplyCountByYear", req);//滞留卡总量
        return list;
    }

    //查询渠道关注量
    public List<ChannelAttentionVO> getChannelAttentionCount()throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<ChannelAttentionVO> list = template.selectList(NAMESPACE + "getChannelAttentionCount");
        return list;
    }


}
