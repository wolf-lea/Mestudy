package com.tecsun.sisp.adapter.modules.ine.entity.request;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;


/**个人用工查询参数vo
 * @author sipeng
 * @email zsp_banyue@163.com
 * @date 2017年10月25日
 */
public class IneQueryBean extends SecQueryBean{
	
	private String areaCode;//所选区域 所选区域编码
	private String areaLevel;//地区级别
	private String pCode;//岗位编码
	private String accountMethod;//结算方式  0 表示日结  1表示月结 2表示小时结  3表示完工结  4表示周结  null表示不限
	private String queryType;//查询种类  是求职信息查询还是招工信息查询  0-求职  1-招工   必填
	private String opType; //操作方式  是编辑还是新增(发布)   编辑-0   新增-1
	private String workDate; // 工作日期
	private long infoId;//信息主键
	private String tel;//电话号码
	private long recordId;
	
	public String getAccountMethod() {
		return accountMethod;
	}
	public void setAccountMethod(String accountMethod) {
		this.accountMethod = accountMethod;
	}
	public String getQueryType() {
		//if(Constants.Ine_QueryType_A.equals(queryType) || Constants.Ine_QueryType_R.equals(queryType) || Constants.T_INFO_TYPE.equals(queryType)){
			return queryType;
		//}else{
			//return "";
		//}
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getWorkDate() {
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd"); 
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		if("0".equals(workDate)) return "";
		if("1".equals(workDate)){
			rightNow.add(Calendar.MONTH,-1);
		}else if("2".equals(workDate)) {
			rightNow.add(Calendar.MONTH,-2);
		}else if("3".equals(workDate)) {
			rightNow.add(Calendar.MONTH,-3);
		}else if("4".equals(workDate)) {
			rightNow.add(Calendar.MONTH,-6);
		}else if("5".equals(workDate)) {
			rightNow.add(Calendar.YEAR,-1);
		}else{
			return workDate;
		}
		return df.format(rightNow.getTime());
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public long getInfoId() {
		return infoId;
	}
	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}
	public long getRecordId() {
		return recordId;
	}
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}
	
	
}
