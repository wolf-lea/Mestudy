package com.tecsun.sisp.iface.server.model.dao.so;

import java.util.List;

import com.tecsun.sisp.iface.common.util.MyBatisDao;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjfxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjtxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YljfxxcxPO;

/**
 * Created by DG on 2015/10/16.
 */
@MyBatisDao
public interface BirthDao {

    public String smssyGetPersonIdByPersonNo(String personNo);

    public Integer smsCheckoutPersonNo(String personNo);

    public YlgrjbxxPO smsSygrjbxx(String personNo);
    
    public List<YlgrjbxxPO> smsSygrjbxxByPersonNO(String personNo);

    public int smsCountSyjfxxcx(String aac001);

    public List<YljfxxcxPO> smsSyjfxxcx(SecQueryBean query);

    public int smsCountSyylfyxxcx(String personNo);

    public List<SyjfxxcxPO> smsSyylfyxxcx(SecQueryBean query);

    public int smsCountSyjtxxcx(String personNo);

    public List<SyjtxxcxPO> smsSyjtxxcx(SecQueryBean query);

}
