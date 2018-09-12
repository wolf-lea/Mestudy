package com.tecsun.sisp.iface.server.model.dao.so;

import java.util.List;

import com.tecsun.sisp.iface.common.util.MyBatisDao;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrffPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrjfPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrzhPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.JfbzPO;

/**
 * Created by DG on 2015/10/14.
 */
@MyBatisDao
public interface ResidentsInsuranceDao {

    public Integer simisCheckoutIdCard(java.util.Map map);

    public GrxxPO simisGetEndowInfoPerson(java.util.Map map);

    public List<GrjfPO> simisGetEndowPayPerson(SecQueryBean query);

    public Integer simisCountEndowPayPerson(String aac001);

    public List<GrzhPO> simisGetEndowAccountPerson(SecQueryBean query);

    public Integer simisCountEndowAccountPerson(String aac001);

    public List<GrffPO> simisGetEndowAnnuityPerson(SecQueryBean query);

    public Integer simisCountEndowAnnuityPerson(String aac001);

    public Integer simisCountEndowPayStandardPerson(String aac001);
    
    public List<JfbzPO> simisEndowPayStandardPerson(SecQueryBean query);
}
