package com.tecsun.sisp.net.modules.service.impl;

import com.tecsun.sisp.net.modules.dao.common.CommQueryDao;
import com.tecsun.sisp.net.modules.entity.response.DictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lorn on 2018/7/27.
 */
@Service
public class CommonServiceImpl implements CommonService{

    @Autowired
    private CommQueryDao commQueryDao;

    //查询数据字典
    public List<DictVO> getDictionaryById4Sisp(String groupId) throws Exception {
        return commQueryDao.getDictionaryById(groupId);

    }


}
