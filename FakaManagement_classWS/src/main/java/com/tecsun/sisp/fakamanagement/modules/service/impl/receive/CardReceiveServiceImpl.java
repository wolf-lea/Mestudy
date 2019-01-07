package com.tecsun.sisp.fakamanagement.modules.service.impl.receive;

import java.util.ArrayList;
import java.util.List;

import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.QueryCardReceiveBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.CardAgentVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.CardReceiveLogVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.CardReceiveVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.PrintLogVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;

/**
 * 
* @ClassName: CardReceiveServiceImpl 
* @Description: TODO(卡领取记录信息数据接口类) 
* @author huangtao
* @date 2017年7月11日 下午2:25:43 
*
 */
@Service("cardReceiveService")
public class CardReceiveServiceImpl extends BaseService {
	
	/**
	 * mapper文件namespace属性值
	 */
	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.receive.CardReceiveServiceImpl.";
	
	/**
	 * 添加领卡记录表记录
	 * @param vo
	 * @return
	 */
	public Integer insertCardReceive(CardReceiveVO vo) {
        this.getFakaSqlSessionTemplate().insert(packageName+"insertCardReceive",vo);
		return vo.getId();
	}
	/**
	 * 添加领卡查询打印记录
	 * @param vo
	 * @return
	 */
	public Integer insertPrintLog(PrintLogVO vo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertPrintLog",vo);
	}
	/**
	 * 添加代领人信息表记录
	 * @param vo
	 * @return
	 */
	public Integer insertCardAgent(CardAgentVO vo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertCardAgent",vo);
	}
	
	/**
	 * 查询领卡记录
	 * @param page 
	 * @return
	 */
	public Page<CardReceiveLogVO> queryCardReceive(Page<CardReceiveLogVO> page, QueryCardReceiveBean bean) throws Exception{
		bean.setPage(page);
		List<CardReceiveLogVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"queryCardReceive",bean);
		page.setData(list);
		return page;
	}
    /**
     * 查询领卡记录列表
     * @param page
     * @return
     */
    public Page<CardReceiveLogVO> queryCardReceiveList(Page<CardReceiveLogVO> page, QueryCardReceiveBean bean) throws Exception{
        bean.setPage(page);
        List<CardReceiveLogVO> list=this.getFakaSqlSessionTemplate().selectList(packageName+"queryCardReceiveList",bean);
        page.setData(list);
        return page;
    }

    /**
     * 查询领卡记录详情
     * @param bean
     * @return
     */
    public CardReceiveLogVO queryCardReceiveLogInfo(QueryCardReceiveBean bean) throws Exception{
        CardReceiveLogVO vo = new CardReceiveLogVO();
        List<CardReceiveLogVO> list = null;
        if ("临时卡领卡".equals(bean.getType())) {
            list = this.getFakaSqlSessionTemplate().selectList(packageName + "queryCardReceiveInfo2", bean);//临时卡详情
        } else if ("即时卡领卡".equals(bean.getType())) {
            list = this.getFakaSqlSessionTemplate().selectList(packageName + "queryCardReceiveInfo3", bean);//即时卡详情
        }else {
            list = this.getFakaSqlSessionTemplate().selectList(packageName + "queryCardReceiveInfo", bean);//正常卡详情
        }
        if (list.size()>0){
            vo = list.get(0);
        }
        return vo;
    }

	/**
	 * 查询代领人是否存在
	 * @param cavo
	 * @return
	 */
	public CardAgentVO queryAgent(CardAgentVO cavo) {
		return this.getFakaSqlSessionTemplate().selectOne(packageName+"queryAgent",cavo);
	}

    //查询卡片是否入库
    public List<CardInfoVO> checkCardInfoByIdcard(CardInfoVO bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardInfoVO> list = new ArrayList<>();
        list = template.selectList(packageName + "checkCardInfoByIdcard", bean);
        for(CardInfoVO vo : list){
            List<CardInfoVO> list1 = template.selectList(packageName + "getUseridByOrgcode", vo);
            if (list1.size()>0){
                vo.setLoginuserid(list1.get(0).getLoginuserid());
            }
        }
        return list;
    }

}
