package com.tecsun.sisp.fakamanagement.modules.entity.param.statistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

public class StatisticsBean extends TSBVO{
	
	private String startdate;
	private String enddate;
	private String fkwd;
	private String bDate;
	private String eDate;

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatDay= new SimpleDateFormat("yyyy-MM-dd");
	
	public String getStartdate() {
		return startdate;
	}
	public Date getStartDate() throws ParseException {
		return format.parse(startdate);
	}
	public void setStartdate(String startdate) {
		/*if(null==startdate||startdate.equals("")){
			startdate="1900-01-01 00:00:00";
		}*/
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public Date getEndDate() throws ParseException {
		return format.parse(enddate);
	}
	public void setEnddate(String enddate) throws ParseException {
		/*if(null==enddate||enddate.equals("")){
			enddate=format.format(new Date());
		}
		Date end=formatDay.parse(enddate);
		if (formatDay.format(end).equals(formatDay.format(new Date()))) {
			Calendar calendar=new GregorianCalendar(); 
		    calendar.setTime(end); 
		    calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
		    end=calendar.getTime();   //这个时间就是日期往后推一天的结果 
			enddate=format.format(end);
		}*/
		this.enddate = enddate;
	}
	/**
	 * @return the fkwd
	 */
	public String getFkwd() {
		return fkwd;
	}
	/**
	 * @param fkwd the fkwd to set
	 */
	public void setFkwd(String fkwd) {
		this.fkwd = fkwd;
	}
	
	

}
