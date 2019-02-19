package com.tecsun.sisp.iface.server.outerface.card;

public class CardServiceProxy implements CardService {
  private String _endpoint = null;
  private CardService cardService = null;

  public CardServiceProxy() {
    _initCardServiceProxy();
  }

  public CardServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCardServiceProxy();
  }

  private void _initCardServiceProxy() {
    try {
      cardService = (new CardServiceServiceLocator()).getCardService();
      if (cardService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cardService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cardService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cardService != null)
      ((javax.xml.rpc.Stub)cardService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public CardService getCardService() {
    if (cardService == null)
      _initCardServiceProxy();
    return cardService;
  }

  public String insert(String xml) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.insert(xml);
  }

  public String getData(String user, String pass, String aaz500, String aac002) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getData(user, pass, aaz500, aac002);
  }

  public String setStart(String user, String pass, String aaz500, String aac002, String aac003, String aab301) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.setStart(user, pass, aaz500, aac002, aac003, aab301);
  }

  public String getCard(String user, String pass, String aaz500, String aac002, String aac003) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getCard(user, pass, aaz500, aac002, aac003);
  }

  public String getBank(String user, String pass, String aac002, String aac003) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getBank(user, pass, aac002, aac003);
  }

  public String getAZ03(String user, String pass, String aac002, String aac003) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getAZ03(user, pass, aac002, aac003);
  }

  public String getAC01(String user, String pass, String aaz500, String aac002) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getAC01(user, pass, aaz500, aac002);
  }

  public String readCard(String user, String pass, String id, String page) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.readCard(user, pass, id, page);
  }

  public String checkTAC(String user, String pass, String dodebit) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.checkTAC(user, pass, dodebit);
  }

  public void checkTAC1(String user, String pass, String dodebit) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    cardService.checkTAC1(user, pass, dodebit);
  }

  public String setLoss(String user, String pass, String aaz500, String aac002, String aac003, String aab301) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.setLoss(user, pass, aaz500, aac002, aac003, aab301);
  }

  public String setCard(String user, String pass, String server, String aaz500, String aac002, String aac003, String aae008, String aae010, String aab301) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.setCard(user, pass, server, aaz500, aac002, aac003, aae008, aae010, aab301);
  }

  public String setCOPY(String xml) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.setCOPY(xml);
  }

  public String setDSZK(String xml) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.setDSZK(xml);
  }

  public String allDsjk(String xml) throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.allDsjk(xml);
  }

  public String getCardInfo() throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getCardInfo();
  }

  public String getSockInfo() throws java.rmi.RemoteException{
    if (cardService == null)
      _initCardServiceProxy();
    return cardService.getSockInfo();
  }
  
  
}