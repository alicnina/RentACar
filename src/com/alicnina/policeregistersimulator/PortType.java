/**
 * PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.policeregistersimulator;

public interface PortType extends java.rmi.Remote {
    public void initializePoliceRegister(java.lang.String IDNumber, java.lang.String drivingLicenceNumber, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;
}
