package com.tecsun.sisp.fakamanagement.modules.service.impl.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.StatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.StatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;


@Service("statisticsService")
public class StatisticsServiceImpl extends BaseService {
	
	/**
	 * mapper文件namespace属性值
	 */
	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.statistics.StatisticsServiceImpl.";

	public List<StatisticsVO> statisticCardStore(StatisticsBean bean) throws Exception{
        if(StringUtils.isNotEmpty(bean.getStartdate())){
            bean.setStartdate(bean.getStartdate()+" 00:00:00");
        }if(StringUtils.isNotEmpty(bean.getEnddate())){
            bean.setEnddate(bean.getEnddate()+" 23:59:59");
        }
        return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardStore",bean);
	}

	public List<StatisticsVO> statisticCardReceive(StatisticsBean bean) throws Exception{
        if(StringUtils.isNotEmpty(bean.getStartdate())){
            bean.setStartdate(bean.getStartdate()+" 00:00:00");
        }if(StringUtils.isNotEmpty(bean.getEnddate())){
            bean.setEnddate(bean.getEnddate()+" 23:59:59");
        }
        return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardReceive",bean);
	}

	public List<StatisticsVO> statisticCardReceiveTwo(StatisticsBean bean){
		return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardReceiveTwo",bean);
	}

	public List<StatisticsVO> statisticCardRetention(StatisticsBean bean) throws Exception{
        if(StringUtils.isNotEmpty(bean.getStartdate())){
            bean.setStartdate(bean.getStartdate()+" 00:00:00");
        }if(StringUtils.isNotEmpty(bean.getEnddate())){
            bean.setEnddate(bean.getEnddate()+" 23:59:59");
        }
        return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardRetention",bean);
	}

	public List<StatisticsVO> statisticCardProblem(StatisticsBean bean) throws Exception{
        if(StringUtils.isNotEmpty(bean.getStartdate())){
            bean.setStartdate(bean.getStartdate()+" 00:00:00");
        }if(StringUtils.isNotEmpty(bean.getEnddate())){
            bean.setEnddate(bean.getEnddate()+" 23:59:59");
        }
        return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardProblem",bean);
	}

	public List<StatisticsVO> statisticCardActivation(StatisticsBean bean) throws Exception{
        if(StringUtils.isNotEmpty(bean.getStartdate())){
            bean.setStartdate(bean.getStartdate()+" 00:00:00");
        }if(StringUtils.isNotEmpty(bean.getEnddate())){
            bean.setEnddate(bean.getEnddate()+" 23:59:59");
        }
        return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardActivation",bean);
	}

	public List<StatisticsVO> statisticCardCabinet(StatisticsBean bean) throws Exception{
        if(StringUtils.isNotEmpty(bean.getStartdate())){
            bean.setStartdate(bean.getStartdate()+" 00:00:00");
        }if(StringUtils.isNotEmpty(bean.getEnddate())){
            bean.setEnddate(bean.getEnddate()+" 23:59:59");
        }
        return this.getFakaSqlSessionTemplate().selectList(packageName+"statisticCardCabinet",bean);
	}

}
