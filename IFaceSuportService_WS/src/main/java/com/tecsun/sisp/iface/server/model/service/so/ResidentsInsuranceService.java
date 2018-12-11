package com.tecsun.sisp.iface.server.model.service.so;

import com.tecsun.sisp.iface.common.Page;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrffPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrjfPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrzhPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.JfbzPO;

import java.util.List;

/**
 * Created by DG on 2015/10/9.
 */
public interface ResidentsInsuranceService {

    public Boolean simisCheckoutIdCard(SecQueryBean query);

    public GrxxPO simisGetEndowInfoPerson(SecQueryBean query);

    public Page<GrjfPO> simisGetEndowPayPerson(SecQueryBean query, Page<GrjfPO> page);

    public Integer simisCountEndowPayPerson(SecQueryBean query);

    public Page<GrzhPO> simisGetEndowAccountPerson(SecQueryBean query , Page<GrzhPO> page);

    public Integer simisCountEndowAccountPerson(SecQueryBean query);

    public Page<GrffPO> simisGetEndowAnnuityPerson(SecQueryBean query , Page<GrffPO> page);

    public Integer simisCountEndowAnnuityPerson(SecQueryBean query);

	public Integer simisCountEndowPayStandardPerson(SecQueryBean query);
	
	public Page<JfbzPO> simisEndowPayStandardPerson(SecQueryBean query , Page<JfbzPO> page);


}
