package com.tecsun.sisp.fakamanagement.modules.service.impl.statistics;

import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.CardStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.SettleStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.CardStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.UserFunctionVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by chenlicong on 2017/12/22.
 */
@Service("cardStatisticsServiceImpl")
public class CardStatisticsServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */

    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.supervise.SuperviseStatisticsServiceImpl.";

    public final static Logger logger = Logger.getLogger(CardStatisticsServiceImpl.class);


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


    //全市卡量统计
    public CardStatisticsVO getCardCountByCity(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        CardStatisticsVO response = template.selectOne(NAMESPACE + "getCardCountByCity", req);
        CardStatisticsVO res = template.selectOne(NAMESPACE + "getCardCount", req);//总卡量统计
        response.setCardCount(res.getCardCount());
        String notfakaCount = String.valueOf(Integer.parseInt(res.getCardCount()) - Integer.parseInt(response.getFakaCount()));//未发卡量
        response.setNotfakaCount(notfakaCount);
        return response;
    }

    //全市每年发卡量统计
    public List<CardStatisticsVO> getCardCountByYear(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStatisticsVO> list = template.selectList(NAMESPACE + "getCardCountByYear", req);
        return list;
    }

    //各区县已、未发量统计
    public List<CardStatisticsVO> getCardCountByDistrict(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStatisticsVO> list = template.selectList(NAMESPACE + "getDistrictCardCount", req);
        for (CardStatisticsVO vo : list){
            req.setReginalcode(vo.getReginalcode());
            CardStatisticsVO  vo1 = template.selectOne(NAMESPACE + "getCardCountByDistrict", req);
            vo.setFakaCount(vo1.getFakaCount());
            String notfakaCount = String.valueOf(Integer.parseInt(vo.getCardCount()) - Integer.parseInt(vo.getFakaCount()));//未发卡量
            vo.setNotfakaCount(notfakaCount);

            //转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getFakaCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setFakaCount(String.valueOf(f11));
            //转化为万，保留两位小数
            double f2 = Double.valueOf(vo.getNotfakaCount())/10000;
            BigDecimal b2 = new BigDecimal(f2);
            double f22 = b2.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setNotfakaCount(String.valueOf(f22));
        }
        return list;
    }

    //各银行已、未发量统计
    public List<CardStatisticsVO> getCardCountByBank(CardStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStatisticsVO> list = template.selectList(NAMESPACE + "getBankCardCount", req);
        for (CardStatisticsVO vo : list){
            req.setBank(vo.getBank());
            CardStatisticsVO  vo1 = template.selectOne(NAMESPACE + "getCardCountByBank", req);
            vo.setFakaCount(vo1.getFakaCount());
            String notfakaCount = String.valueOf(Integer.parseInt(vo.getCardCount()) - Integer.parseInt(vo.getFakaCount()));//未发卡量
            vo.setNotfakaCount(notfakaCount);

            //转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getFakaCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setFakaCount(String.valueOf(f11));
            //转化为万，保留两位小数
            double f2 = Double.valueOf(vo.getNotfakaCount())/10000;
            BigDecimal b2 = new BigDecimal(f2);
            double f22 = b2.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setNotfakaCount(String.valueOf(f22));
        }
        return list;
    }

    /**
     * 插入医保结算统计信息by时间
     * @param list 集合
     * @return
     */
    //@Transactional(value="sisp")
    public int insertYbjsInfoByTime(List<SettleStatisticsBean> list) throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        //先删除数据，再插入数据
        logger.info("开始清除数据===");
        int i = template.delete(NAMESPACE+"deleteYbjsTimeTemp");
        if (i>0) {
            logger.info("数据清除成功，开始插入数据===");
            template.insert(NAMESPACE + "insertYbjsInfoByTime", list);
        }
        return i;
    }

    /**
     * 插入医保结算统计信息by区域
     * @param list 集合
     * @return
     */
    //@Transactional(value="sisp")
    public int insertYbjsInfoByDistrict(List<SettleStatisticsBean> list) throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        //先删除数据，再插入数据
        logger.info("开始清除数据===");
        int i = template.delete(NAMESPACE+"deleteYbjsDistrictTemp");
        if (i>0) {
            logger.info("数据清除成功，开始插入数据===");
            template.insert(NAMESPACE + "insertYbjsInfoByDistrict", list);
        }
        return i;
    }

    //全市医保结算刷卡量统计
    public List<SettleStatisticsBean> getTempSettleCountByMonth(SettleStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<SettleStatisticsBean> list = template.selectList(NAMESPACE + "getTempSettleCountByMonth", req);
        for (SettleStatisticsBean vo : list){
            //转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setCount(String.valueOf(f11));
        }
        return list;
    }

    //各区县医保结算刷卡量统计
    public List<SettleStatisticsBean> getTempSettleCountByDistrict(SettleStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<SettleStatisticsBean> list = template.selectList(NAMESPACE + "getTempSettleCountByDistrict", req);
        for (SettleStatisticsBean vo : list){
            //转化为万，保留两位小数
            double f1 = Double.valueOf(vo.getCount())/10000;
            BigDecimal b1 = new BigDecimal(f1);
            double f11 = b1.setScale(2, RoundingMode.HALF_UP).doubleValue();
            vo.setCount(String.valueOf(f11));
        }
        return list;
    }


}
