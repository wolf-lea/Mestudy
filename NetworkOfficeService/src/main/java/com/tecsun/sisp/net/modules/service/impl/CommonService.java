package com.tecsun.sisp.net.modules.service.impl;

import com.tecsun.sisp.net.modules.entity.response.DictVO;

import java.util.List;

/**
 * Created by lorn on 2018/7/27.
 */
public interface CommonService {
    public List<DictVO> getDictionaryById4Sisp(String groupId) throws Exception;


}
