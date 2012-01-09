package com.alicnina.paymentsimulator;

public class PortTypeProxy implements com.alicnina.paymentsimulator.PortType {
  private String _endpoint = null;
  private com.alicnina.paymentsimulator.PortType portType = null;
  
  public PortTypeProxy() {
    _initPortTypeProxy();
  }
  
  public PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPortTypeProxy();
  }
  
  private void _initPortTypeProxy() {
    try {
      portType = (new com.alicnina.paymentsimulator.PaymentSimulatorLocator()).getHttpSoap11Endpoint();
      if (portType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)portType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)portType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (portType != null)
      ((javax.xml.rpc.Stub)portType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.alicnina.paymentsimulator.PortType getPortType() {
    if (portType == null)
      _initPortTypeProxy();
    return portType;
  }
  
  public void initializePayment(java.lang.String creditCardNo, java.lang.String cvv2, double ammount, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (portType == null)
      _initPortTypeProxy();
    portType.initializePayment(creditCardNo, cvv2, ammount, code, message);
  }
  
  
}