package com.tecsun.sisp.fakamanagement.modules.service.impl.store;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardCabinetVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service("cardCabinetManageService")
public class CardCabinetManageServiceImpl extends BaseService {

    /**
     * 新增卡柜
     * @param cvo
     * @return
     */
    public int addCabinet(CardCabinetVO cvo){
        return this.getFakaSqlSessionTemplate().insert("addCabinet",cvo);
    }

    /**
     * 查询卡柜
     * @param page
     * @param cvo
     * @return
     */
    public Page<CardCabinetVO> getCabinets(Page<CardCabinetVO> page,CardCabinetVO cvo){
        cvo.setPage(page);
        List<CardCabinetVO> list=this.getFakaSqlSessionTemplate().selectList("getCabinets",cvo);
        page.setData(list);
        return page;
    }

    /**
     * 根据卡柜名称和用户id统计卡柜数量
     * @param vo
     * @return
     */
    public int countCardBinets(CardCabinetVO vo){
        return this.getFakaSqlSessionTemplate().selectOne("countCardBinets",vo);
    }

    /**
     * 修改卡柜
     * @param cvo
     * @return
     */
    public int updateCabinet(CardCabinetVO cvo){
        return this.getFakaSqlSessionTemplate().update("updateCabinet",cvo);
    }

}
