package com.tecsun.sisp.adapter.modules.card.entity.response;

import java.util.Date;

/**
 * 制卡机轮询获取实体类
 * @author
 *
 */
public class PixelBusinessInfoVo {

	private Long id;
	private String filepath;//照片存放路径
	private String status;//处理是否成功；00-- 初始状态；01—成功；02—失败 ; 03--预处理成功 ； 04--预处理失败
	private String message;//失败原因
	private Date createTime;//创建时间
	private Date finishTime;//完成时间
	private Date updateTime;//修改时间
    private String sfzh;//身份证号码
    private String xm;//姓名

    private String sex;//性别
    private String nation;//民族

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
    
    public String getXm() {
        return xm;
    }
    public void setXm(String xm) {
        this.xm = xm;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
