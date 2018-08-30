package com.tecsun.sisp.adapter.modules.resume.entity.response;

/**简历基本信息
 * Created by Administrator on 2017/10/31 0031.
 */
public class BasicMsgVO {
    private String id;      //简历id
    private String name;    //姓名
    private String sex;     //性别 1、男 2、女
    private String sexName;
    private String education;  //最高学历
    private String educationName;
    private String workingSeniority;       //工作年限
    private String workingSeniorityName;
    private String address;         //所在地址
    private String phone;           //联系电话
    private String email;           //联系邮箱
    private String picture;         //照片ID
    private String age;     //年龄

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getWorkingSeniorityName() {
        return workingSeniorityName;
    }

    public void setWorkingSeniorityName(String workingSeniorityName) {
        this.workingSeniorityName = workingSeniorityName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkingSeniority() {
        return workingSeniority;
    }

    public void setWorkingSeniority(String workingSeniority) {
        this.workingSeniority = workingSeniority;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
