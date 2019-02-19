package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/14.
 */
public class BusinessInfoPO {
    private String id;
    private String person_id;
    private String status;
    private String statement;
    private String identify_id;
    private String times;
    private String photoPath1;
    private String photoPath2;
    private String photoPath3;
    
    private String personcheckstatus;//人检结果
    private String personchecktime;//人检时间
    private String machinecheckstatus;//机检结果
    private String machinechecktime;//机检时间
    private String authway;//认证渠道
	private String authtype;//认证方式
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getIdentify_id() {
        return identify_id;
    }

    public void setIdentify_id(String identify_id) {
        this.identify_id = identify_id;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPhotoPath1() {
        return photoPath1;
    }

    public void setPhotoPath1(String photoPath1) {
        this.photoPath1 = photoPath1;
    }

    public String getPhotoPath2() {
        return photoPath2;
    }

    public void setPhotoPath2(String photoPath2) {
        this.photoPath2 = photoPath2;
    }

    public String getPhotoPath3() {
        return photoPath3;
    }

    public void setPhotoPath3(String photoPath3) {
        this.photoPath3 = photoPath3;
    }
    
    public String getPersoncheckstatus() {
		return personcheckstatus;
	}

	public void setPersoncheckstatus(String personcheckstatus) {
		this.personcheckstatus = personcheckstatus;
	}

	public String getPersonchecktime() {
		return personchecktime;
	}

	public void setPersonchecktime(String personchecktime) {
		this.personchecktime = personchecktime;
	}

	public String getMachinecheckstatus() {
		return machinecheckstatus;
	}

	public void setMachinecheckstatus(String machinecheckstatus) {
		this.machinecheckstatus = machinecheckstatus;
	}

	public String getMachinechecktime() {
		return machinechecktime;
	}

	public void setMachinechecktime(String machinechecktime) {
		this.machinechecktime = machinechecktime;
	}

	public String getAuthway() {
		return authway;
	}

	public void setAuthway(String authway) {
		this.authway = authway;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

}
