/**
 * Soap11BindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.alicnina.paymentsimulator;

public class Soap11BindingSkeleton implements com.alicnina.paymentsimulator.PortType, org.apache.axis.wsdl.Skeleton {
    private com.alicnina.paymentsimulator.PortType impl;
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
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "creditCardNo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "cvv2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "ammount"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "code"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "message"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("initializePayment", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://alicnina.com/paymentsimulator", "initializePayment"));
        _oper.setSoapAction("urn:initializePayment");
        _myOperationsList.add(_oper);
        if (_myOperations.get("initializePayment") == null) {
            _myOperations.put("initializePayment", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("initializePayment")).add(_oper);
    }

    public Soap11BindingSkeleton() {
        this.impl = new com.alicnina.paymentsimulator.Soap11BindingImpl();
    }

    public Soap11BindingSkeleton(com.alicnina.paymentsimulator.PortType impl) {
        this.impl = impl;
    }
    public void initializePayment(java.lang.String creditCardNo, java.lang.String cvv2, double ammount, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException
    {
        impl.initializePayment(creditCardNo, cvv2, ammount, code, message);
    }

}
