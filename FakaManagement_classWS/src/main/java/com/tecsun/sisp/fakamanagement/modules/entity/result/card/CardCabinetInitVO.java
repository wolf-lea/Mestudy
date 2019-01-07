package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import java.util.List;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardCabinetInitVo 
* @Description: TODO(初始化查出卡柜全部信息-层信息) 
* @author huangtao
* @date 2017年7月13日 下午3:33:53 
*
 */
public class CardCabinetInitVO extends CardCabinetVO{
	private List<CardFloorInitVO> floors;

	public List<CardFloorInitVO> getFloors() {
		return floors;
	}

	public void setFloors(List<CardFloorInitVO> floors) {
		this.floors = floors;
	}
	
}
