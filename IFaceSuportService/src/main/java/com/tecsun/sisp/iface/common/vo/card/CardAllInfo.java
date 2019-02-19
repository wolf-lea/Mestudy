package com.tecsun.sisp.iface.common.vo.card;

/**
 * 卡基础数据（包含个人照片）
 * Created by jerry on 2015/5/30.
 */
public class CardAllInfo extends CardBaseInfo {
    private String photo;//照片
    
    private String collType;//类型1：退休或遗嘱人员；2：非退休或遗嘱人员

    public String getCollType() {
		return collType;
	}

	public void setCollType(String collType) {
		this.collType = collType;
	}


    public CardAllInfo() {
    }
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
