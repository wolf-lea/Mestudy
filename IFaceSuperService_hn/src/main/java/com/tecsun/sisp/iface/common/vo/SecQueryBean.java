package com.tecsun.sisp.iface.common.vo;

/**
 * 社保卡请求对象
 * Created by jerry on 2015/5/31.
 */
public class SecQueryBean extends BaseVO{
    /**
     * 1 请求头 start
     */
    private String channelcode;//渠道编码
    private String deviceid;//设备编码
    private String tokenId;//权限验证码
    private String netpassword;// 登录密码
    private String userName; //用户名
    
    
    
    public String getNetpassword() {
		return netpassword;
	}
	public void setNetpassword(String netpassword) {
		this.netpassword = netpassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
     * 请求头 end
     */

    
    private String aac002;//养老、失业、工伤接口-身份证号码
    private String aae140;//险种
   
	public String getAae140() {
		return aae140;
	}
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}
	public String getAac002() {
		return aac002;
	}
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}
	/**
     * 2 请求体(包含社保所有业务入参) start
     */
     /**
     * 2 请求体(包含社保所有业务入参) start
     */
  
    private String funcid; //功能编号
    private String city;//参保系统代码，7位数；
    private String aac003;//姓名
    private String sfzhm;//身份证号；
    private String xzbz;//险种标志
    
    private String aae002;//日期;
    private String jfzt;//缴费状态
    
    
    private String yybm;//医院编码；
    private String zylsh;//门诊流水号
    
    private String oldpass;//旧密码
    private String newpass;//新密码
    private String renewpass;//确认新密码
    private String kh;//卡号；
    
    private String type;//居民类型：1-农村居民；2-城镇居民；
    private String code;//区域代码
    private String codeType;
    
    
    public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getOldpass() {
		return oldpass;
	}
	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getRenewpass() {
		return renewpass;
	}
	public void setRenewpass(String renewpass) {
		this.renewpass = renewpass;
	}
	
    
   
  
    
    public String getYybm() {
		return yybm;
	}
	public void setYybm(String yybm) {
		this.yybm = yybm;
	}
	public String getZylsh() {
		return zylsh;
	}
	public void setZylsh(String zylsh) {
		this.zylsh = zylsh;
	}
	public String getJfzt() {
		return jfzt;
	}
	public void setJfzt(String jfzt) {
		this.jfzt = jfzt;
	}
	public String getAae002() {
		return aae002;
	}
	public void setAae002(String aae002) {
		this.aae002 = aae002;
	}
	public String getXzbz() {
		return xzbz;
	}
	public void setXzbz(String xzbz) {
		this.xzbz = xzbz;
	}
	private String qsny;//起始年月， 格式YYYYMM;
    private String zzny;//终止年月， 格式YYYYMM;
    private int pageno;
    public int getPageno() {
		return pageno;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	private int pageSize;//页容量
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
	public String getFuncid() {
		return funcid;
	}
	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAac003() {
		return aac003;
	}
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}
	
	public String getSfzhm() {
		return sfzhm;
	}
	public void setSfzhm(String sfzhm) {
		this.sfzhm = sfzhm;
	}

	public String getQsny() {
		return qsny;
	}
	public void setQsny(String qsny) {
		this.qsny = qsny;
	}
	public String getZzny() {
		return zzny;
	}
	public void setZzny(String zzny) {
		this.zzny = zzny;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

    private String aae114;//缴费状态

	public String getAae114() {
		return aae114;
	}
	public void setAae114(String aae114) {
		this.aae114 = aae114;
	}
	
	private String fwmm;

	public String getFwmm() {
		return fwmm;
	}
	public void setFwmm(String fwmm) {
		this.fwmm = fwmm;
	}

    private String xzlb;//险种类别

    public String getXzlb() {
        return xzlb;
    }

    public void setXzlb(String xzlb) {
        this.xzlb = xzlb;
    }
}
