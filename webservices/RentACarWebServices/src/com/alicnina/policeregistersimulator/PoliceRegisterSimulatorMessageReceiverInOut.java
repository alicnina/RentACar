/**
 * PoliceRegisterSimulatorMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.alicnina.policeregistersimulator;

/**
 * PoliceRegisterSimulatorMessageReceiverInOut message receiver
 */

public class PoliceRegisterSimulatorMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {

	public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
			throws org.apache.axis2.AxisFault {

		try {

			// get the implementation class for the Web Service
			Object obj = getTheImplementationObject(msgContext);

			PoliceRegisterSimulatorSkeleton skel = (PoliceRegisterSimulatorSkeleton) obj;
			// Out Envelop
			org.apache.axiom.soap.SOAPEnvelope envelope = null;
			// Find the axisOperation that has been set by the Dispatch phase.
			org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
			if (op == null) {
				throw new org.apache.axis2.AxisFault(
						"Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
			}

			java.lang.String methodName;
			if ((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)) {

				if ("initializePoliceRegister".equals(methodName)) {

					com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse initializePoliceRegisterResponse1 = null;
					com.alicnina.policeregistersimulator.InitializePoliceRegister wrappedParam = (com.alicnina.policeregistersimulator.InitializePoliceRegister) fromOM(
							msgContext.getEnvelope().getBody().getFirstElement(), com.alicnina.policeregistersimulator.InitializePoliceRegister.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

//					initializePoliceRegisterResponse1 =skel.initializePoliceRegister(wrappedParam);
//					envelope = toEnvelope(getSOAPFactory(msgContext), initializePoliceRegisterResponse1, false, new javax.xml.namespace.QName(
//							"http://policeregistersimulator.alicnina.com", "initializePoliceRegister"));
				} else

				if ("savePoliceRegister".equals(methodName)) {

					com.alicnina.policeregistersimulator.SavePoliceRegisterResponse savePoliceRegisterResponse3 = null;
					com.alicnina.policeregistersimulator.SavePoliceRegister wrappedParam = (com.alicnina.policeregistersimulator.SavePoliceRegister) fromOM(
							msgContext.getEnvelope().getBody().getFirstElement(), com.alicnina.policeregistersimulator.SavePoliceRegister.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

//					savePoliceRegisterResponse3 = skel.savePoliceRegister(wrappedParam);
//
//					envelope = toEnvelope(getSOAPFactory(msgContext), savePoliceRegisterResponse3, false, new javax.xml.namespace.QName(
//							"http://policeregistersimulator.alicnina.com", "savePoliceRegister"));
				} else

				if ("removePoliceRegister".equals(methodName)) {

					com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse removePoliceRegisterResponse5 = null;
					com.alicnina.policeregistersimulator.RemovePoliceRegister wrappedParam = (com.alicnina.policeregistersimulator.RemovePoliceRegister) fromOM(
							msgContext.getEnvelope().getBody().getFirstElement(), com.alicnina.policeregistersimulator.RemovePoliceRegister.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

//					removePoliceRegisterResponse5 = skel.removePoliceRegister(wrappedParam);
//
//					envelope = toEnvelope(getSOAPFactory(msgContext), removePoliceRegisterResponse5, false, new javax.xml.namespace.QName(
//							"http://policeregistersimulator.alicnina.com", "removePoliceRegister"));

				} else {
					throw new java.lang.RuntimeException("method not found");
				}

				newMsgContext.setEnvelope(envelope);
			}
		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	//
	private org.apache.axiom.om.OMElement toOM(com.alicnina.policeregistersimulator.InitializePoliceRegister param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.policeregistersimulator.InitializePoliceRegister.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.policeregistersimulator.SavePoliceRegister param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.policeregistersimulator.SavePoliceRegister.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.policeregistersimulator.SavePoliceRegisterResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.policeregistersimulator.SavePoliceRegisterResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.policeregistersimulator.RemovePoliceRegister param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.policeregistersimulator.RemovePoliceRegister.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
			throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

			emptyEnvelope.getBody().addChild(param.getOMElement(com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse.MY_QNAME, factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse wrapinitializePoliceRegister() {
		com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse wrappedElement = new com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse();
		return wrappedElement;
	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.alicnina.policeregistersimulator.SavePoliceRegisterResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
			throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

			emptyEnvelope.getBody().addChild(param.getOMElement(com.alicnina.policeregistersimulator.SavePoliceRegisterResponse.MY_QNAME, factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.alicnina.policeregistersimulator.SavePoliceRegisterResponse wrapsavePoliceRegister() {
		com.alicnina.policeregistersimulator.SavePoliceRegisterResponse wrappedElement = new com.alicnina.policeregistersimulator.SavePoliceRegisterResponse();
		return wrappedElement;
	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
			throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

			emptyEnvelope.getBody().addChild(param.getOMElement(com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse.MY_QNAME, factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse wrapremovePoliceRegister() {
		com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse wrappedElement = new com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse();
		return wrappedElement;
	}

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type, java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {

		try {

			if (com.alicnina.policeregistersimulator.InitializePoliceRegister.class.equals(type)) {

				return com.alicnina.policeregistersimulator.InitializePoliceRegister.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse.class.equals(type)) {

				return com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.policeregistersimulator.SavePoliceRegister.class.equals(type)) {

				return com.alicnina.policeregistersimulator.SavePoliceRegister.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.policeregistersimulator.SavePoliceRegisterResponse.class.equals(type)) {

				return com.alicnina.policeregistersimulator.SavePoliceRegisterResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.policeregistersimulator.RemovePoliceRegister.class.equals(type)) {

				return com.alicnina.policeregistersimulator.RemovePoliceRegister.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse.class.equals(type)) {

				return com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
		org.apache.axis2.AxisFault f;
		Throwable cause = e.getCause();
		if (cause != null) {
			f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
		} else {
			f = new org.apache.axis2.AxisFault(e.getMessage());
		}

		return f;
	}

}// end of class
