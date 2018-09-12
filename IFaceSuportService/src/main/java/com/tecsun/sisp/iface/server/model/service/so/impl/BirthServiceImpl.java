package com.tecsun.sisp.iface.server.model.service.so.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjfxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.birth.SyjtxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YljfxxcxPO;
import com.tecsun.sisp.iface.server.model.dao.so.BirthDao;
import com.tecsun.sisp.iface.server.model.service.so.BirthService;

/**
 * Created by DG on 2015/10/16.
 */
@Service("birthServiceImpl")
public class BirthServiceImpl implements BirthService{

    @Autowired
    public BirthDao birthDao;

    @Override
    public Boolean smsCheckoutPersonNo(SecQueryBean query) {
        return birthDao.smsCheckoutPersonNo(query.getAac002()) != 0;
    }

    @Override
    public YlgrjbxxPO smsSygrjbxx(SecQueryBean query) {
        return birthDao.smsSygrjbxx(query.getAac002());
    }
    
    @Override
    public List<YlgrjbxxPO> smsSygrjbxxByPersonNO(SecQueryBean query) {
        return birthDao.smsSygrjbxxByPersonNO(query.getAac002());
    }

    @Override
    public int smsCountSyjfxxcx(SecQueryBean query) {
        return birthDao.smsCountSyjfxxcx(birthDao.smssyGetPersonIdByPersonNo(query.getAac002()));
    }

    @Override
    public List<YljfxxcxPO> smsSyjfxxcx(SecQueryBean query) {
        query.setAac001(birthDao.smssyGetPersonIdByPersonNo(query.getAac002()));
        return birthDao.smsSyjfxxcx(query);
    }

    @Override
    public int smsCountSyylfyxxcx(SecQueryBean query) {
        return birthDao.smsCountSyylfyxxcx(query.getAac002());
    }

    @Override
    public List<SyjfxxcxPO> smsSyylfyxxcx(SecQueryBean query) {
        return birthDao.smsSyylfyxxcx(query);
    }

    @Override
    public int smsCountSyjtxxcx(SecQueryBean query) {
        return birthDao.smsCountSyjtxxcx(query.getAac002());
    }

    @Override
    public List<SyjtxxcxPO> smsSyjtxxcx(SecQueryBean query) {
        return birthDao.smsSyjtxxcx(query);
    }
}
