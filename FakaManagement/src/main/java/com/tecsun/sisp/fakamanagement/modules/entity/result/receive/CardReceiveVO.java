package com.tecsun.sisp.fakamanagement.modules.entity.result.receive;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;


/**
 * 照片和代领人必须要有其中之一
* @ClassName: CardReceiveVO 
* @Description: TODO(卡片领取记录表) 
* @author huangtao
* @date 2017年7月11日 下午2:20:58 
*
 */
public class CardReceiveVO extends BaseVO {

	private Integer id;//
	private Integer ciid;// 卡信息表 唯一编码
	private Integer type;// 人员类型
	private String receive_time;// 领取时间
	private String photo;// 个人照片
	private String idcard_photo_up;// 个人身份证正面
	private String idcard_photo_down;// 个人身份证反面
	private String sign_photo;// 电子签字
	private Integer agentid;// 代领人表对应编码

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCiid() {
		return ciid;
	}

	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getReceive_time() {
		return receive_time;
	}
	SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void setReceive_time(Date receive_time) {
		this.receive_time = format.format(receive_time);
	}

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

}
