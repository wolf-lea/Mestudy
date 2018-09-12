package com.tecsun.sisp.iface.common.vo.employment.result;


import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class RegistrationInfoBean {

    private String acc0m0;    //登记证流水号
    private String acc0m1;    //就业失业登记证号
    private String acc0m2;    //条形码
    private String aac001;    //个人编号
    private String aac002;    //公民身份号码
    private String aac003;    //姓名
    private String aac004;    //性别
    private String aac005;    //民族
    private String aac006;    //出生日期
    private String aac011;    //学历
    private String aac017;    //婚姻状况
    private String aac024;    //政治面貌
    private String aac033;    //身体状况
    private String aac009;    //户口性质
    private String aac010;    //户口所在地名称
    private String aab299;    //户口所在地区划
    private String aac186;    //户口登记日期
    private String aac046;    //常住地址区划
    private String bab305;    //常住地址名称
    private String acc0m3;    //登记就失业状态
    private String aac180;    //毕业学校
    private String aac181;    //毕业日期
    private String aac183;    //所学专业
    private String acc0m8;    //所学专业名称
    private String acc0m5;    //国家职业资格名称
    private String aac015;    //国家职业资格等级
    private String aac184;    //取得职业资格日期
    private String aca111;    //取得职业（工种）资格代码
    private String aca112;    //取得职业（工种）资格名称
    private String aac014;    //专业技术职务
    private String acc0aa;    //专业技术职务名称
    private String aac185;    //取得专技资格日期
    private String acc0m6;    //是否享受社保补贴
    private String acc0m9;    //是否领过再就业优惠证
    private String acc0ma;    //再就业优惠证号
    private String acc0mb;    //参加社会保险种类
    private String acc0mc;    //是否领取过失业证
    private String acc0md;    //失业证号码
    private String aae005;    //联系电话
    private String aae007;    //手机号码
    private String acc0mh;    //发证日期
    private String acc0mj;    //发证机构名称
    private String aae110;    //当前状态
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期
    private String acc0mm;    //常住地详址
    private String acc0mi;    //发证机构代码

    public String getAcc0m0() {
        return acc0m0;
    }

    public void setAcc0m0(String acc0m0) {
        this.acc0m0 = acc0m0;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAcc0m2() {
        return acc0m2;
    }

    public void setAcc0m2(String acc0m2) {
        this.acc0m2 = acc0m2;
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

    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003;
    }

    public String getAac004() {
        if(aac004 == null){
            return "";
        }else if(aac004.equals("1")){
            return "男";
        }else if(aac004.equals("2")){
            return "女";
        }else{
            return "";
        }
    }

    public void setAac004(String aac004) {
        this.aac004 = aac004;
    }

    public String getAac005() {
        String s = Constants.NATION.get(aac005);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac005(String aac005) {
        this.aac005 = aac005;
    }

    public String getAac006() {
        return aac006;
    }

    public void setAac006(String aac006) {
        this.aac006 = aac006;
    }

    public String getAac011() {
        String s = Constants.EDUCATION.get(aac011);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac011(String aac011) {
        this.aac011 = aac011;
    }

    public String getAac017() {
        String s = Constants.MARITALSTATUS.get(aac017);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac017(String aac017) {
        this.aac017 = aac017;
    }

    public String getAac024() {
        String s = Constants.POLITICALOUTLOOK.get(aac024);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac024(String aac024) {
        this.aac024 = aac024;
    }

    public String getAac033() {
        String s = Constants.PHYSICAL.get(aac033);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac033(String aac033) {
        this.aac033 = aac033;
    }

    public String getAac009() {
        String s = Constants.RESIDENCE.get(aac009);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac009(String aac009) {
        this.aac009 = aac009;
    }

    public String getAac010() {
        return aac010;
    }

    public void setAac010(String aac010) {
        this.aac010 = aac010;
    }

    public String getAab299() {
        return aab299;
    }

    public void setAab299(String aab299) {
        this.aab299 = aab299;
    }

    public String getAac186() {
        return aac186;
    }

    public void setAac186(String aac186) {
        this.aac186 = aac186;
    }

    public String getAac046() {
        return aac046;
    }

    public void setAac046(String aac046) {
        this.aac046 = aac046;
    }

    public String getBab305() {
        return bab305;
    }

    public void setBab305(String bab305) {
        this.bab305 = bab305;
    }

    public String getAcc0m3() {
        if(acc0m3 == null){
            return "";
        }else if(acc0m3.equals("1")){
            return "就业";
        }else if(acc0m3.equals("2")){
            return "失业";
        }else{
            return "";
        }
    }

    public void setAcc0m3(String acc0m3) {
        this.acc0m3 = acc0m3;
    }

    public String getAac180() {
        return aac180;
    }

    public void setAac180(String aac180) {
        this.aac180 = aac180;
    }

    public String getAac181() {
        return aac181;
    }

    public void setAac181(String aac181) {
        this.aac181 = aac181;
    }

    public String getAac183() {
        return aac183;
    }

    public void setAac183(String aac183) {
        this.aac183 = aac183;
    }

    public String getAcc0m8() {
        return acc0m8;
    }

    public void setAcc0m8(String acc0m8) {
        this.acc0m8 = acc0m8;
    }

    public String getAcc0m5() {
        return acc0m5;
    }

    public void setAcc0m5(String acc0m5) {
        this.acc0m5 = acc0m5;
    }

    public String getAac015() {
        String s = Constants.Voca_qual_level.get(aac015);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac015(String aac015) {
        this.aac015 = aac015;
    }

    public String getAac184() {
        return aac184;
    }

    public void setAac184(String aac184) {
        this.aac184 = aac184;
    }

    public String getAca111() {
        return aca111;
    }

    public void setAca111(String aca111) {
        this.aca111 = aca111;
    }

    public String getAca112() {
        return aca112;
    }

    public void setAca112(String aca112) {
        this.aca112 = aca112;
    }

    public String getAac014() {
        return aac014;
    }

    public void setAac014(String aac014) {
        this.aac014 = aac014;
    }

    public String getAcc0aa() {
        return acc0aa;
    }

    public void setAcc0aa(String acc0aa) {
        this.acc0aa = acc0aa;
    }

    public String getAac185() {
        return aac185;
    }

    public void setAac185(String aac185) {
        this.aac185 = aac185;
    }

    public String getAcc0m6() {
        if(acc0m6 == null){
            return "";
        }else if(acc0m6.equals("0")){
            return "否";
        }else if(acc0m6.equals("1")){
            return "是";
        }else{
            return "";
        }
    }

    public void setAcc0m6(String acc0m6) {
        this.acc0m6 = acc0m6;
    }

    public String getAcc0m9() {
        if(acc0m9 == null){
            return "";
        }else if(acc0m9.equals("0")){
            return "否";
        }else if(acc0m9.equals("1")){
            return "是";
        }else{
            return "";
        }
    }

    public void setAcc0m9(String acc0m9) {
        this.acc0m9 = acc0m9;
    }

    public String getAcc0ma() {
        return acc0ma;
    }

    public void setAcc0ma(String acc0ma) {
        this.acc0ma = acc0ma;
    }

    public String getAcc0mb() {
        if(acc0mb == null || acc0mb.equals("")){
            return "";
        }

        String[] strList = acc0mb.split(",");
        String returnString = "";
        for(String text : strList){
            returnString += Constants.InsuranceTypes.get(text)+",";
        }
        return returnString.substring(0, returnString.length()-1);
    }

    public void setAcc0mb(String acc0mb) {
        this.acc0mb = acc0mb;
    }

    public String getAcc0mc() {
        if(acc0mc == null){
            return "";
        }else if(acc0mc.equals("0")){
            return "否";
        }else if(acc0mc.equals("1")){
            return "是";
        }else{
            return "";
        }
    }

    public void setAcc0mc(String acc0mc) {
        this.acc0mc = acc0mc;
    }

    public String getAcc0md() {
        return acc0md;
    }

    public void setAcc0md(String acc0md) {
        this.acc0md = acc0md;
    }

    public String getAae005() {
        return aae005;
    }

    public void setAae005(String aae005) {
        this.aae005 = aae005;
    }

    public String getAae007() {
        return aae007;
    }

    public void setAae007(String aae007) {
        this.aae007 = aae007;
    }

    public String getAcc0mh() {
        return acc0mh;
    }

    public void setAcc0mh(String acc0mh) {
        this.acc0mh = acc0mh;
    }

    public String getAcc0mj() {
        return acc0mj;
    }

    public void setAcc0mj(String acc0mj) {
        this.acc0mj = acc0mj;
    }

    public String getAae110() {
        String s = Constants.Current_State.get(aae110);
        s = s == null ? "" : s;
        return s;
    }

    public void setAae110(String aae110) {
        this.aae110 = aae110;
    }

    public String getAae013() {
        return aae013;
    }

    public void setAae013(String aae013) {
        this.aae013 = aae013;
    }

    public String getAae011() {
        return aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011;
    }

    public String getAae017() {
        return aae017;
    }

    public void setAae017(String aae017) {
        this.aae017 = aae017;
    }

    public String getAae019() {
        return aae019;
    }

    public void setAae019(String aae019) {
        this.aae019 = aae019;
    }

    public String getAae020() {
        return aae020;
    }

    public void setAae020(String aae020) {
        this.aae020 = aae020;
    }

    public String getAae022() {
        return aae022;
    }

    public void setAae022(String aae022) {
        this.aae022 = aae022;
    }

    public String getAae036() {
        return aae036;
    }

    public void setAae036(String aae036) {
        this.aae036 = aae036;
    }

    public String getAcc0mm() {
        return acc0mm;
    }

    public void setAcc0mm(String acc0mm) {
        this.acc0mm = acc0mm;
    }

    public String getAcc0mi() {
        return acc0mi;
    }

    public void setAcc0mi(String acc0mi) {
        this.acc0mi = acc0mi;
    }
}
