package com.tecsun.sisp.net.modules.dao.common;

import com.tecsun.sisp.net.common.MyBatisDao;
import com.tecsun.sisp.net.modules.entity.response.DictVO;

import java.util.List;

@MyBatisDao
public interface CommQueryDao {
    public List<DictVO> getDictionaryById(String groupId);



}