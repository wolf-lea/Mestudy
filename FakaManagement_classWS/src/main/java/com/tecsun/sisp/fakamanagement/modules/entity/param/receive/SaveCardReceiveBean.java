package com.tecsun.sisp.fakamanagement.modules.entity.param.receive;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

/**
 * 
* @ClassName: SaveCardReceiveBean 
* @Description: TODO(领卡记录参数) 
* @author huangtao
* @date 2017年7月13日 下午2:43:48 
*
 */
public class SaveCardReceiveBean  extends TSBVO{
	private String photo;// 个人照片
	private String idcard_photo_up;// 个人身份证正面
	private String idcard_photo_down;// 个人身份证反面
	private String sign_photo;// 电子签字
	private String idcard;// 身份证号码
	private String name;//姓名
	private String phone;// 联系电话
	private Integer type; //领取类型
	private Integer[] ids;//唯一编码数组
    private String fkwd;// 发卡网点

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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

    public String getFkwd() {
        return fkwd;
    }

    public void setFkwd(String fkwd) {
        this.fkwd = fkwd;
    }
}
