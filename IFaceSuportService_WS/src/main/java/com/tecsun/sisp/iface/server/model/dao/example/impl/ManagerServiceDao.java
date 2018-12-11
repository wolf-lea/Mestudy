package com.tecsun.sisp.iface.server.model.dao.example.impl;

import java.util.List;

import com.tecsun.sisp.iface.common.util.MyBatisDao;
import com.tecsun.sisp.iface.server.model.dao.example.BaseDao;
import com.tecsun.sisp.iface.common.vo.card.result.BankServicePO;


@MyBatisDao
public interface ManagerServiceDao extends BaseDao<BankServicePO>{

	public List<BankServicePO> findAll(BankServicePO t);

	public List<BankServicePO> findListByPage(BankServicePO t);
}
