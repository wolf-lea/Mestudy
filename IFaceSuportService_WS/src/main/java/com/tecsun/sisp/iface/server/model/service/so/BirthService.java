package com.tecsun.sisp.iface.server.model.service.so;

import java.util.List;

import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjfxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjtxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YljfxxcxPO;

/**
 * Created by DG on 2015/10/16.
 */
public interface BirthService {

    public Boolean smsCheckoutPersonNo(SecQueryBean query);

    public YlgrjbxxPO smsSygrjbxx(SecQueryBean query);

    public int smsCountSyjfxxcx(SecQueryBean query);

    public List<YljfxxcxPO> smsSyjfxxcx(SecQueryBean query);

    public int smsCountSyylfyxxcx(SecQueryBean query);

    public List<SyjfxxcxPO> smsSyylfyxxcx(SecQueryBean query);

    public int smsCountSyjtxxcx(SecQueryBean query);

    public List<SyjtxxcxPO> smsSyjtxxcx(SecQueryBean query);
    
    //生育个人参保信息
	List<YlgrjbxxPO> smsSygrjbxxByPersonNO(SecQueryBean query);

}
