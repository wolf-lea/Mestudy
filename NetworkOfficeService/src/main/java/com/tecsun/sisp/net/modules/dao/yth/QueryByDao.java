package com.tecsun.sisp.net.modules.dao.yth;

import com.tecsun.sisp.net.common.MyBatisDao;
import com.tecsun.sisp.net.modules.entity.response.UserVO;


@MyBatisDao
public interface QueryByDao {

	 public UserVO getUserById(int userId);
}
