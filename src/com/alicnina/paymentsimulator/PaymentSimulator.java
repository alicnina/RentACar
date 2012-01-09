/**
 * PaymentSimulator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.paymentsimulator;

public interface PaymentSimulator extends javax.xml.rpc.Service {
    public java.lang.String getHttpSoap11EndpointAddress();

    public com.alicnina.paymentsimulator.PortType getHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException;

    public com.alicnina.paymentsimulator.PortType getHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
