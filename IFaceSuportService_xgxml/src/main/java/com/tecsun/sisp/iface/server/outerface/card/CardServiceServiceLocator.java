/**
 * CardServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tecsun.sisp.iface.server.outerface.card;

public class CardServiceServiceLocator extends org.apache.axis.client.Service implements CardServiceService {

    public CardServiceServiceLocator() {
    }


    public CardServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CardServiceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CardService
    private String CardService_address = "http://10.128.8.73:7001/hbCardService/services/CardService";

    public String getCardServiceAddress() {
        return CardService_address;
    }

    // The WSDD service name defaults to the port name.
    private String CardServiceWSDDServiceName = "CardService";

    public String getCardServiceWSDDServiceName() {
        return CardServiceWSDDServiceName;
    }

    public void setCardServiceWSDDServiceName(String name) {
        CardServiceWSDDServiceName = name;
    }

    public CardService getCardService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CardService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCardService(endpoint);
    }

    public CardService getCardService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            CardServiceSoapBindingStub _stub = new CardServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCardServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCardServiceEndpointAddress(String address) {
        CardService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (CardService.class.isAssignableFrom(serviceEndpointInterface)) {
                CardServiceSoapBindingStub _stub = new CardServiceSoapBindingStub(new java.net.URL(CardService_address), this);
                _stub.setPortName(getCardServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("CardService".equals(inputPortName)) {
            return getCardService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://server.wei.com", "CardServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://server.wei.com", "CardService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("CardService".equals(portName)) {
            setCardServiceEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
