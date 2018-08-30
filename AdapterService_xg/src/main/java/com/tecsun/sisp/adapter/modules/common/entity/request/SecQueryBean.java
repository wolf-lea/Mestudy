package com.tecsun.sisp.adapter.modules.common.entity.request;

/**
 * Created by DG on 2015/10/15.
 */
public class SecQueryBean extends BaseVO{
    
	private String 	sfzh;  			//身份证
    private String 	xm;  			//姓名
    private int 	rowstart;
    private int 	rowend;
    
    private String 	channelcode;	//渠道编码
    private String 	deviceid;		//设备编码
    private String 	tokenid;		//权限验证码
    private int 	pageno;			//页码
    private int 	pagesize;		//页数

    private String grbh;//个人编号
    private String aac002;//身份证
    private String aae140;//险种
    private String aac001;//个人编号；
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public int getRowstart() {
        return rowstart;
    }

    public void setRowstart(int rowstart) {
        this.rowstart = rowstart;
    }

    public int getRowend() {
        return rowend;
    }

    public void setRowend(int rowEnd) {
        this.rowend = rowEnd;
    }

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getGrbh() {
		return grbh;
	}

	public void setGrbh(String grbh) {
		this.grbh = grbh;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAae140() {
		return aae140;
	}

	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}
}
