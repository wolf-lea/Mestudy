package com.tecsun.sisp.fakamanagement.modules.service.impl.replacement;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenlicong on 2018/10/24.
 */
@Service("teplaceStatisticsServiceImpl")
public class ReplaceStatisticsServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.replacement.ReplaceStatisticsServiceImpl.";

    public final static Logger logger = Logger.getLogger(ReplaceStatisticsServiceImpl.class);

    //预制卡发卡统计
    public Page<ReplaceStatisticsVO> getReplaceCardCount(Page<ReplaceStatisticsVO> page,ReplaceStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<ReplaceStatisticsVO> list = template.selectList(NAMESPACE + "getReplaceCardCount", req);
        page.setData(list);
        return page;
    }
    public List<ReplaceStatisticsVO> getReplaceCardCountList(ReplaceStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<ReplaceStatisticsVO> list = template.selectList(NAMESPACE + "getReplaceCardCount", req);
        return list;
    }


}
