package com.tecsun.sisp.iface.server.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import com.tecsun.sisp.iface.server.entity.card.FlexEmpVO;
import com.tecsun.sisp.iface.server.entity.card.TCardDatasVO;
import com.tecsun.sisp.iface.server.entity.card.TDatasResultVo;
import com.tecsun.sisp.iface.server.entity.card.TLogsVO;
import com.tecsun.sisp.iface.server.entity.card.TTakeDatasVO;



@Service("flexEmpQueryService")
public class FlexEmpQueryServiceImpl  {
	public static final Logger logger = Logger
			.getLogger(FlexEmpQueryServiceImpl.class);

	 private SqlSessionTemplate sqlSessionTemplate;

	    public SqlSessionTemplate getSqlSessionTemplate() {
	        return sqlSessionTemplate;
	    }

	    @Resource
	    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	        this.sqlSessionTemplate = sqlSessionTemplate;
	    }
	    
	    
	    public List<FlexEmpVO> getFlexEmpRecodByIDcard(SecQueryBean bean){
	        return this.sqlSessionTemplate.selectList("getFlexEmpRecodByIDcard", bean);
	    }
	    //查1
	    public List<TCardDatasVO> getListCardDatas(TCardDatasVO vo){
	    	return this.sqlSessionTemplate.selectList("getListCardDatas",vo);
	    }
	    //新增1
	    public int insertTakeDatas(TTakeDatasVO takedataVo){
			return  sqlSessionTemplate.insert("insertTableTakeDatas",takedataVo);
			
		}
	    
	    //更新2
	    public  int updateCardDatas(TCardDatasVO cardDataVo){
	    	return sqlSessionTemplate.update("updateTCardDatas",cardDataVo);
	    }
	    
	    //插入2
	    public int insertTDatasResult(TDatasResultVo dataResultVo){
	    	return sqlSessionTemplate.insert("insertTDatasResult",dataResultVo);
	    }
	    
	    //添加到日志表
	    public int insertLogDatas(TLogsVO logDatas){
	    	return sqlSessionTemplate.insert("addLogDatas",logDatas);
	    }
	    
	    
}
