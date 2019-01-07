package com.tecsun.sisp.adapter.modules.common.entity.request;


/**
 * Created by DG on 2015/10/15.
 */
public class SecQueryBean extends BaseVO{
    
	private String 	sfzh;  			//身份证
    private String 	xm;  			//姓名
    private String infoType;		//信息类型  ine表示个人用工信息
    private int 	rowstart;
    private int 	rowend;
    
    private String 	channelcode;	//渠道编码
    private String 	deviceid;		//设备编码
    private String 	tokenid;		//权限验证码
    private int 	pageno;			//页码
    private int 	pagesize;		//页数
    
    private String dicType;//数据字典类型

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

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType ;
	}

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}
	
}
