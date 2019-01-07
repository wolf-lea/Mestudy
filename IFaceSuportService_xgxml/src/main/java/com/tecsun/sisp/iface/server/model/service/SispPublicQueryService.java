package com.tecsun.sisp.iface.server.model.service;

import com.tecsun.sisp.iface.common.vo.cssp.UseInfoBean;

/**
 * Created by hhl on 2016/6/19.
 */
public interface SispPublicQueryService {

    public UseInfoBean getInfo(String deviceId) throws Exception;
}
