package com.tecsun.sisp.iface.server.entity.card;


import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2015/8/17.
 */
public class ReturnMessageListBean {
	
	private  int page;
	private String end;
	private String city;
	private Double personPayCountnt; //个人缴费合计
	private Double subPayCount;//补贴缴费合计；

	public Double getPersonPayCountnt() {
		return personPayCountnt;
	}
	public void setPersonPayCountnt(Double personPayCountnt) {
		this.personPayCountnt = personPayCountnt;
	}
	public Double getSubPayCount() {
		return subPayCount;
	}
	public void setSubPayCount(Double subPayCount) {
		this.subPayCount = subPayCount;
	}
	//private Map<String,String> data;
	private List<ReturnMessageBean> list;
	public int getPage() {
		return page;
	}
	public List<ReturnMessageBean> getList() {
		return list;
	}
	public void setList(List<ReturnMessageBean> list) {
		this.list = list;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/*public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}*/
	
	/*
    private String token;
    private String pagesize;
    private String pageno;
    private String total;
    private List<ReturnMessageBean> content;
    private ac01DTO ac01DTO;
    private ac02DTO ac02DTO;
    private ab01DTO ab01DTO;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getPageno() {
        return pageno;
    }

    public void setPageno(String pageno) {
        this.pageno = pageno;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ReturnMessageBean> getContent() {
        return content;
    }

    public void setContent(List<ReturnMessageBean> content) {
        this.content = content;
    }

    public ac01DTO getAc01DTO() {
        return ac01DTO;
    }

    public void setAc01DTO(ac01DTO ac01DTO) {
        this.ac01DTO = ac01DTO;
    }

    public ac02DTO getAc02DTO() {
        return ac02DTO;
    }

    public void setAc02DTO(ac02DTO ac02DTO) {
        this.ac02DTO = ac02DTO;
    }

    public ab01DTO getAb01DTO() {
        return ab01DTO;
    }

    public void setAb01DTO(ab01DTO ab01DTO) {
        this.ab01DTO = ab01DTO;
    }
}

    class ac01DTO{
        private String aac003;      //姓名
        private String aae135;      //身份证
        private String aac001;      //个人参保编号
        private String aac004;      //性别
        private String aac005;      //民族
        private String bac136;      //灵活就业标志
        private String aac006;      //出生日期
        private String aac084;      //人员类别(0--未退休,1--离退休,2--退休审核期,3--离休)
        private String aac007;      //参加工作日期
        private String aac013;      //用工形式

        public String getAac003() {
            return aac003;
        }

        public void setAac003(String aac003) {
            this.aac003 = aac003;
        }

        public String getAae135() {
            return aae135;
        }

        public void setAae135(String aae135) {
            this.aae135 = aae135;
        }

        public String getAac001() {
            return aac001;
        }

        public void setAac001(String aac001) {
            this.aac001 = aac001;
        }

        public String getAac004() {
            return aac004;
        }

        public void setAac004(String aac004) {
            this.aac004 = aac004;
        }

        public String getAac005() {
            return aac005;
        }

        public void setAac005(String aac005) {
            this.aac005 = aac005;
        }

        public String getBac136() {
            return bac136;
        }

        public void setBac136(String bac136) {
            this.bac136 = bac136;
        }

        public String getAac006() {
            return aac006;
        }

        public void setAac006(String aac006) {
            this.aac006 = aac006;
        }

        public String getAac084() {
            return aac084;
        }

        public void setAac084(String aac084) {
            this.aac084 = aac084;
        }

        public String getAac007() {
            return aac007;
        }

        public void setAac007(String aac007) {
            this.aac007 = aac007;
        }

        public String getAac013() {
            return aac013;
        }

        public void setAac013(String aac013) {
            this.aac013 = aac013;
        }
    }

class ac02DTO{
    private String	aac001;		//个人编号
    private String	aae140;		//险种类型
    private String	aac030;		//本次参保日期
    private String	aac008;		//人员参保状态
    private String	aac049;		//首次参保日期
    private String	aab034;		//经办机构

    public String getAac001() {
        return aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }

    public String getAae140() {
        return aae140;
    }

    public void setAae140(String aae140) {
        this.aae140 = aae140;
    }

    public String getAac030() {
        return aac030;
    }

    public void setAac030(String aac030) {
        this.aac030 = aac030;
    }

    public String getAac008() {
        return aac008;
    }

    public void setAac008(String aac008) {
        this.aac008 = aac008;
    }

    public String getAac049() {
        return aac049;
    }

    public void setAac049(String aac049) {
        this.aac049 = aac049;
    }

    public String getAab034() {
        return aab034;
    }

    public void setAab034(String aab034) {
        this.aab034 = aab034;
    }
}

class ab01DTO{
    private String	aab001;		//单位编号
    private String	aae007;		//邮政编码
    private String	aab004;		//单位名称

    public String getAab001() {
        return aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }

    public String getAae007() {
        return aae007;
    }

    public void setAae007(String aae007) {
        this.aae007 = aae007;
    }

    public String getAab004() {
        return aab004;
    }

    public void setAab004(String aab004) {
        this.aab004 = aab004;
    }
    */
	
}


