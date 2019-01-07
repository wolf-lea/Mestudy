/**
 * CardService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tecsun.sisp.iface.server.outerface.card;

public interface CardService extends java.rmi.Remote {
    public String insert(String xml) throws java.rmi.RemoteException;
    public String getData(String user, String pass, String aaz500, String aac002) throws java.rmi.RemoteException;
    public String setStart(String user, String pass, String aaz500, String aac002, String aac003, String aab301) throws java.rmi.RemoteException;
    public String getCard(String user, String pass, String aaz500, String aac002, String aac003) throws java.rmi.RemoteException;
    public String getBank(String user, String pass, String aac002, String aac003) throws java.rmi.RemoteException;
    public String getAZ03(String user, String pass, String aac002, String aac003) throws java.rmi.RemoteException;
    public String getAC01(String user, String pass, String aaz500, String aac002) throws java.rmi.RemoteException;
    public String readCard(String user, String pass, String id, String page) throws java.rmi.RemoteException;
    public String checkTAC(String user, String pass, String dodebit) throws java.rmi.RemoteException;
    public void checkTAC1(String user, String pass, String dodebit) throws java.rmi.RemoteException;
    public String setLoss(String user, String pass, String aaz500, String aac002, String aac003, String aab301) throws java.rmi.RemoteException;
    public String setCard(String user, String pass, String server, String aaz500, String aac002, String aac003, String aae008, String aae010, String aab301) throws java.rmi.RemoteException;
    public String setCOPY(String xml) throws java.rmi.RemoteException;
    public String setDSZK(String xml) throws java.rmi.RemoteException;
    public String allDsjk(String xml) throws java.rmi.RemoteException;
    public String getCardInfo() throws java.rmi.RemoteException;
    public String getSockInfo() throws java.rmi.RemoteException;
}
