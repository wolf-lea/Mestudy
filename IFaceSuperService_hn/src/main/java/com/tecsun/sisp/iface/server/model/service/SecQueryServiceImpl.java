package com.tecsun.sisp.iface.server.model.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.server.model.dao.SecQueryDAO;

/**
 * Created by jerry on 2015/5/31.
 */
@Service("secQueryService")
public class SecQueryServiceImpl implements SecQueryService {

	public static final Logger logger = Logger
			.getLogger(SecQueryServiceImpl.class);

	@Autowired
	private SecQueryDAO secQueryDAO;

}
