package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardStore 
* @Description: TODO(卡存放位置信息) 
* @author huangtao
* @date 2017年7月7日 下午5:04:49 
*
 */
public class CardStoreVO extends BaseVO{

	private Integer id;//主键
	private Integer csid;//卡存放序号
	private Integer ciid;//卡唯一编码
	private Integer cbid;//卡存放盒子
    private String batchNo;//批次号
    private String ccid;//柜子ID
    private String bin;//箱号
    private String box;//盒号

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public Integer getCiid() {
		return ciid;
	}
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}
	public Integer getCbid() {
		return cbid;
	}
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }
}
