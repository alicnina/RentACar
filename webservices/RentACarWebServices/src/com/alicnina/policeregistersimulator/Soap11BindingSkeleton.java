/**
 * Soap11BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.policeregistersimulator;

public class Soap11BindingSkeleton implements com.alicnina.policeregistersimulator.PortType, org.apache.axis.wsdl.Skeleton {
    private com.alicnina.policeregistersimulator.PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "IDNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "DrivingLicenceNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "code"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("initializePoliceRegister", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://alicnina.com/policeregistersimulator", "initializePoliceRegister"));
        _oper.setSoapAction("urn:initializePoliceRegister");
        _myOperationsList.add(_oper);
        if (_myOperations.get("initializePoliceRegister") == null) {
            _myOperations.put("initializePoliceRegister", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("initializePoliceRegister")).add(_oper);
    }

    public Soap11BindingSkeleton() {
        this.impl = new com.alicnina.policeregistersimulator.Soap11BindingImpl();
    }

    public Soap11BindingSkeleton(com.alicnina.policeregistersimulator.PortType impl) {
        this.impl = impl;
    }
    public void initializePoliceRegister(java.lang.String IDNumber, java.lang.String drivingLicenceNumber, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException
    {
        impl.initializePoliceRegister(IDNumber, drivingLicenceNumber, code, message);
    }

}
