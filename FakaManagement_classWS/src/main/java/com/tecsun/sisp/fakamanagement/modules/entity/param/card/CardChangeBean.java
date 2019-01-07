package com.tecsun.sisp.fakamanagement.modules.entity.param.card;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

/**
 * 
* @ClassName: CardChangeBean 
* @Description: TODO(卡转移参数) 
* @author huangtao
* @date 2017年7月13日 上午11:02:40 
*
 */
public class CardChangeBean  extends TSBVO{
	
	private Integer fromcbid; //从哪个盒子转移的id
	private Integer tocbid;//转移到那个盒子id
	public Integer getFromcbid() {
		return fromcbid;
	}
	public void setFromcbid(Integer fromcbid) {
		this.fromcbid = fromcbid;
	}
	public Integer getTocbid() {
		return tocbid;
	}
	public void setTocbid(Integer tocbid) {
		this.tocbid = tocbid;
	}
	
	

}
