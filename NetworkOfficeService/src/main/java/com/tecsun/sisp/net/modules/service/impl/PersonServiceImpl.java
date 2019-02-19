package com.tecsun.sisp.net.modules.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.net.modules.dao.drafts_sisp.PersonDAO;
import com.tecsun.sisp.net.modules.entity.request.PersonBean;



@Service("personService")
public class PersonServiceImpl implements PersonService{
	
	
	@Autowired
	private PersonDAO personDAO;
	

	public long updateDraftsMessage(PersonBean vo) {
		
		return personDAO.updateDraftsMessage(vo);
	}

}
