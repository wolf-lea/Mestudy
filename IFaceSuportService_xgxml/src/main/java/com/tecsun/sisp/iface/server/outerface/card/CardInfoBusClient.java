package com.tecsun.sisp.iface.server.outerface.card;


import com.tecsun.sisp.iface.server.util.Config;

import javax.xml.rpc.ServiceException;

public class CardInfoBusClient {

	//城市代码
	public static final String CITYNO = Config.getInstance().get("cityno");
	//用户名
	public static final String USER = Config.getInstance().get("user");
	//密码
	public static final String PASSWORD = Config.getInstance().get("password");
	
	public static CardServiceSoapBindingStub stub = null;
	
	public CardInfoBusClient (){
		try {
			stub = (CardServiceSoapBindingStub) new CardServiceServiceLocator().getCardService();
		} catch (ServiceException e) {
			stub = null;
		}
	}
	
	
}
