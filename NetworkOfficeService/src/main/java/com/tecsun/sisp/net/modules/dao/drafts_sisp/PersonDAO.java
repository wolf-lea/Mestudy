package com.tecsun.sisp.net.modules.dao.drafts_sisp;

import com.tecsun.sisp.net.common.MyBatisDao;
import com.tecsun.sisp.net.modules.entity.request.PersonBean;

@MyBatisDao
public interface PersonDAO {
	
	public long updateDraftsMessage(PersonBean vo);

}
