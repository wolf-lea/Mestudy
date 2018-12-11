package com.tecsun.sisp.iface.common.vo.common.query;

import com.tecsun.sisp.iface.common.vo.common.BaseVO;

/**
 * Created by DG on 2015/10/15.
 */
public class SecQueryBean extends BaseVO{
	private String aae080;//实付年月
	private String business;//业务编码
	private String aae140;//险种编码
	private String aac003;//姓名
    private String aac001;  //个人编号
    private String aac002; //身份证
    private String aaz001;  //区域
    private int rowStart;
    private int rowEnd;
    
    private String channelcode;//渠道编码
    private String deviceid;//设备编码
    private String tokenId;//权限验证码
    private int pageNo;
    private int pageSize;
    
    
    public String getAae080() {
		return aae080;
	}

	public void setAae080(String aae080) {
		this.aae080 = aae080;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getAae140() {
		return aae140;
	}

	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getAac001() {
        return aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }
    
    public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public int getRowStart() {
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public void setRowEnd(int rowEnd) {
        this.rowEnd = rowEnd;
    }

	public String getAaz001() {
		return aaz001;
	}

	public void setAaz001(String aaz001) {
		this.aaz001 = aaz001;
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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
}
