package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

/**
 * Created by Administrator on 2017/11/13 0013.
 */
public class FairPositionVo {
    private String id;
    private String name;  //岗位名称
    private String hireNum; //招聘人数
    private String salaryMin;   //最低薪资
    private String salaryMax;   //最高薪资
    private String address; //工作地点
    private String duty;    //岗位职责
    private String coId;        //公司id
    private String coName;      //公司名称
    private String coSummary;//公司简介
    private String area;   //公司地址
    private String phone;
    private String tel;
    private String contact; //联系人
    private String city;//公司所在城市
    private String workingSeniority;//工作年限编码
    private String workingSeniorityName;//工作年限
    
    
    
    
    

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWorkingSeniority() {
		return workingSeniority;
	}

	public void setWorkingSeniority(String workingSeniority) {
		this.workingSeniority = workingSeniority;
	}

	public String getWorkingSeniorityName() {
		return workingSeniorityName;
	}

	public void setWorkingSeniorityName(String workingSeniorityName) {
		this.workingSeniorityName = workingSeniorityName;
	}

	public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCoName() {
        return coName;
    }

    public void setCoName(String coName) {
        this.coName = coName;
    }

    public String getCoSummary() {
        return coSummary;
    }

    public void setCoSummary(String coSummary) {
        this.coSummary = coSummary;
    }

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHireNum() {
        return hireNum;
    }

    public void setHireNum(String hireNum) {
        this.hireNum = hireNum;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(String salaryMin) {
        this.salaryMin = salaryMin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
