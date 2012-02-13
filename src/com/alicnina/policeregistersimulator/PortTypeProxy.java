package com.alicnina.policeregistersimulator;

public class PortTypeProxy implements com.alicnina.policeregistersimulator.PortType {
  private String _endpoint = null;
  private com.alicnina.policeregistersimulator.PortType portType = null;
  
  public PortTypeProxy() {
    _initPortTypeProxy();
  }
  
  public PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPortTypeProxy();
  }
  
  private void _initPortTypeProxy() {
    try {
      portType = (new com.alicnina.policeregistersimulator.PoliceRegisterSimulatorLocator()).getHttpSoap11Endpoint2();
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
  
  public com.alicnina.policeregistersimulator.PortType getPortType() {
    if (portType == null)
      _initPortTypeProxy();
    return portType;
  }
  
  public void initializePoliceRegister(java.lang.String IDNumber, java.lang.String drivingLicenceNumber, javax.xml.rpc.holders.StringHolder code, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (portType == null)
      _initPortTypeProxy();
    portType.initializePoliceRegister(IDNumber, drivingLicenceNumber, code, message);
  }
  
  
}