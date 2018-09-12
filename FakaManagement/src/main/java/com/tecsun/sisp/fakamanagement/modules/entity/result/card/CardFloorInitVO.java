package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import java.util.List;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardCabinetInitVo 
* @Description: TODO(初始化查出卡柜全部信息-盒信息) 
* @author huangtao
* @date 2017年7月13日 下午3:33:53 
*
 */
public class CardFloorInitVO extends CardFloorVO {
	private List<CardBoxVO> boxs;

	public List<CardBoxVO> getBoxs() {
		return boxs;
	}

	public void setBoxs(List<CardBoxVO> boxs) {
		this.boxs = boxs;
	}
	
}
