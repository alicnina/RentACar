/**
 * PaymentSimulatorLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.paymentsimulator;

public class PaymentSimulatorLocator extends org.apache.axis.client.Service implements com.alicnina.paymentsimulator.PaymentSimulator {

    public PaymentSimulatorLocator() {
    }


    public PaymentSimulatorLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PaymentSimulatorLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HttpSoap11Endpoint
    private java.lang.String HttpSoap11Endpoint_address = "http://localhost:8080/Axis2HelloWorld/services/PaymentSimulator.HttpSoap11Endpoint/";

    public java.lang.String getHttpSoap11EndpointAddress() {
        return HttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HttpSoap11EndpointWSDDServiceName = "HttpSoap11Endpoint";

    public java.lang.String getHttpSoap11EndpointWSDDServiceName() {
        return HttpSoap11EndpointWSDDServiceName;
    }

    public void setHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        HttpSoap11EndpointWSDDServiceName = name;
    }

    public com.alicnina.paymentsimulator.PortType getHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHttpSoap11Endpoint(endpoint);
    }

    public com.alicnina.paymentsimulator.PortType getHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alicnina.paymentsimulator.Soap11BindingStub _stub = new com.alicnina.paymentsimulator.Soap11BindingStub(portAddress, this);
            _stub.setPortName(getHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        HttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alicnina.paymentsimulator.PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alicnina.paymentsimulator.Soap11BindingStub _stub = new com.alicnina.paymentsimulator.Soap11BindingStub(new java.net.URL(HttpSoap11Endpoint_address), this);
                _stub.setPortName(getHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("HttpSoap11Endpoint".equals(inputPortName)) {
            return getHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "PaymentSimulator");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "HttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HttpSoap11Endpoint".equals(portName)) {
            setHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
