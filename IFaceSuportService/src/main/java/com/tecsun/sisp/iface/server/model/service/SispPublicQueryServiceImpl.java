package com.tecsun.sisp.iface.server.model.service;

import com.tecsun.sisp.iface.common.vo.cssp.UseInfoBean;
import com.tecsun.sisp.iface.server.model.dao_cssp.CsspQueryDAO;
import com.tecsun.sisp.iface.server.model.dao_sisp_public.SispPublicQueryDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hhl on 2016/6/19.
 */
@Service("sispPublicQueryService")
public class SispPublicQueryServiceImpl implements SispPublicQueryService {
    public static final Logger logger = Logger.getLogger(SispPublicQueryServiceImpl.class);

    @Autowired
    private SispPublicQueryDAO sispPublicQueryDAO;

   
    public UseInfoBean getInfo(String deviceId) throws Exception {
        return sispPublicQueryDAO.getInfo(deviceId);
    }
}
