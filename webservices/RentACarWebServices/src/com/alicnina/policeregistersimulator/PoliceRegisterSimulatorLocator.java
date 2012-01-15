/**
 * PoliceRegisterSimulatorLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.policeregistersimulator;

public class PoliceRegisterSimulatorLocator extends org.apache.axis.client.Service implements com.alicnina.policeregistersimulator.PoliceRegisterSimulator {

    public PoliceRegisterSimulatorLocator() {
    }


    public PoliceRegisterSimulatorLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PoliceRegisterSimulatorLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HttpSoap11Endpoint2
    private java.lang.String HttpSoap11Endpoint2_address = "http://localhost:8080/Axis2HelloWorld/services/PoliceRegisterSimulator.HttpSoap11Endpoint2/";

    public java.lang.String getHttpSoap11Endpoint2Address() {
        return HttpSoap11Endpoint2_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HttpSoap11Endpoint2WSDDServiceName = "HttpSoap11Endpoint2";

    public java.lang.String getHttpSoap11Endpoint2WSDDServiceName() {
        return HttpSoap11Endpoint2WSDDServiceName;
    }

    public void setHttpSoap11Endpoint2WSDDServiceName(java.lang.String name) {
        HttpSoap11Endpoint2WSDDServiceName = name;
    }

    public com.alicnina.policeregistersimulator.PortType getHttpSoap11Endpoint2() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HttpSoap11Endpoint2_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHttpSoap11Endpoint2(endpoint);
    }

    public com.alicnina.policeregistersimulator.PortType getHttpSoap11Endpoint2(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.alicnina.policeregistersimulator.Soap11BindingStub _stub = new com.alicnina.policeregistersimulator.Soap11BindingStub(portAddress, this);
            _stub.setPortName(getHttpSoap11Endpoint2WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHttpSoap11Endpoint2EndpointAddress(java.lang.String address) {
        HttpSoap11Endpoint2_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.alicnina.policeregistersimulator.PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.alicnina.policeregistersimulator.Soap11BindingStub _stub = new com.alicnina.policeregistersimulator.Soap11BindingStub(new java.net.URL(HttpSoap11Endpoint2_address), this);
                _stub.setPortName(getHttpSoap11Endpoint2WSDDServiceName());
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
        if ("HttpSoap11Endpoint2".equals(inputPortName)) {
            return getHttpSoap11Endpoint2();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "PoliceRegisterSimulator");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "HttpSoap11Endpoint2"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HttpSoap11Endpoint2".equals(portName)) {
            setHttpSoap11Endpoint2EndpointAddress(address);
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
