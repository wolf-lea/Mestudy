package com.tecsun.sisp.fakamanagement.modules.entity.result.receive;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardAgentVO 
* @Description: TODO(代领人信息表) 
* @author huangtao
* @date 2017年7月11日 下午2:23:36 
*
 */
public class CardAgentVO extends BaseVO {
	
	private String photo;// 个人照片
	private String idcard_photo_up;// 个人身份证正面
	private String idcard_photo_down;// 个人身份证反面
	private String sign_photo;// 电子签字
	private String idcard;// 身份证号码
	private String name;//姓名
	private String phone;// 联系电话
	private Integer agentid;// 代领人唯一编码
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIdcard_photo_up() {
		return idcard_photo_up;
	}
	public void setIdcard_photo_up(String idcard_photo_up) {
		this.idcard_photo_up = idcard_photo_up;
	}
	public String getIdcard_photo_down() {
		return idcard_photo_down;
	}
	public void setIdcard_photo_down(String idcard_photo_down) {
		this.idcard_photo_down = idcard_photo_down;
	}
	public String getSign_photo() {
		return sign_photo;
	}
	public void setSign_photo(String sign_photo) {
		this.sign_photo = sign_photo;
	}
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
