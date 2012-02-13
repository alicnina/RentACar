/**
 * PaymentSimulatorMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.alicnina.paymentsimulator;

/**
 * PaymentSimulatorMessageReceiverInOut message receiver
 */

public class PaymentSimulatorMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {

	public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
			throws org.apache.axis2.AxisFault {

		try {

			// get the implementation class for the Web Service
			Object obj = getTheImplementationObject(msgContext);

			PaymentSimulatorSkeleton skel = (PaymentSimulatorSkeleton) obj;
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

				if ("registerAccount".equals(methodName)) {

					com.alicnina.paymentsimulator.RegisterAccountResponse registerAccountResponse1 = null;
					com.alicnina.paymentsimulator.RegisterAccount wrappedParam = (com.alicnina.paymentsimulator.RegisterAccount) fromOM(msgContext
							.getEnvelope().getBody().getFirstElement(), com.alicnina.paymentsimulator.RegisterAccount.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

//					registerAccountResponse1 = skel.registerAccount(wrappedParam);
//
//					envelope = toEnvelope(getSOAPFactory(msgContext), registerAccountResponse1, false, new javax.xml.namespace.QName(
//							"http://paymentsimulator.alicnina.com", "registerAccount"));
				} else

				if ("initializePayment".equals(methodName)) {

					com.alicnina.paymentsimulator.InitializePaymentResponse initializePaymentResponse3 = null;
					com.alicnina.paymentsimulator.InitializePayment wrappedParam = (com.alicnina.paymentsimulator.InitializePayment) fromOM(msgContext
							.getEnvelope().getBody().getFirstElement(), com.alicnina.paymentsimulator.InitializePayment.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

//					initializePaymentResponse3 = skel.initializePayment(wrappedParam);
//
//					envelope = toEnvelope(getSOAPFactory(msgContext), initializePaymentResponse3, false, new javax.xml.namespace.QName(
//							"http://paymentsimulator.alicnina.com", "initializePayment"));
				} else

				if ("removeAccount".equals(methodName)) {

					com.alicnina.paymentsimulator.RemoveAccountResponse removeAccountResponse5 = null;
					com.alicnina.paymentsimulator.RemoveAccount wrappedParam = (com.alicnina.paymentsimulator.RemoveAccount) fromOM(msgContext.getEnvelope()
							.getBody().getFirstElement(), com.alicnina.paymentsimulator.RemoveAccount.class, getEnvelopeNamespaces(msgContext.getEnvelope()));

//					removeAccountResponse5 = skel.removeAccount(wrappedParam);
//
//					envelope = toEnvelope(getSOAPFactory(msgContext), removeAccountResponse5, false, new javax.xml.namespace.QName(
//							"http://paymentsimulator.alicnina.com", "removeAccount"));

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
	private org.apache.axiom.om.OMElement toOM(com.alicnina.paymentsimulator.RegisterAccount param, boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.paymentsimulator.RegisterAccount.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.paymentsimulator.RegisterAccountResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.paymentsimulator.RegisterAccountResponse.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.paymentsimulator.InitializePayment param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.paymentsimulator.InitializePayment.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.paymentsimulator.InitializePaymentResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.paymentsimulator.InitializePaymentResponse.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.paymentsimulator.RemoveAccount param, boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.paymentsimulator.RemoveAccount.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.alicnina.paymentsimulator.RemoveAccountResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.alicnina.paymentsimulator.RemoveAccountResponse.MY_QNAME, org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.alicnina.paymentsimulator.RegisterAccountResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
			throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

			emptyEnvelope.getBody().addChild(param.getOMElement(com.alicnina.paymentsimulator.RegisterAccountResponse.MY_QNAME, factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.alicnina.paymentsimulator.RegisterAccountResponse wrapregisterAccount() {
		com.alicnina.paymentsimulator.RegisterAccountResponse wrappedElement = new com.alicnina.paymentsimulator.RegisterAccountResponse();
		return wrappedElement;
	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.alicnina.paymentsimulator.InitializePaymentResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
			throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

			emptyEnvelope.getBody().addChild(param.getOMElement(com.alicnina.paymentsimulator.InitializePaymentResponse.MY_QNAME, factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.alicnina.paymentsimulator.InitializePaymentResponse wrapinitializePayment() {
		com.alicnina.paymentsimulator.InitializePaymentResponse wrappedElement = new com.alicnina.paymentsimulator.InitializePaymentResponse();
		return wrappedElement;
	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.alicnina.paymentsimulator.RemoveAccountResponse param,
			boolean optimizeContent, javax.xml.namespace.QName methodQName) throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

			emptyEnvelope.getBody().addChild(param.getOMElement(com.alicnina.paymentsimulator.RemoveAccountResponse.MY_QNAME, factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.alicnina.paymentsimulator.RemoveAccountResponse wrapremoveAccount() {
		com.alicnina.paymentsimulator.RemoveAccountResponse wrappedElement = new com.alicnina.paymentsimulator.RemoveAccountResponse();
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

			if (com.alicnina.paymentsimulator.RegisterAccount.class.equals(type)) {

				return com.alicnina.paymentsimulator.RegisterAccount.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.paymentsimulator.RegisterAccountResponse.class.equals(type)) {

				return com.alicnina.paymentsimulator.RegisterAccountResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.paymentsimulator.InitializePayment.class.equals(type)) {

				return com.alicnina.paymentsimulator.InitializePayment.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.paymentsimulator.InitializePaymentResponse.class.equals(type)) {

				return com.alicnina.paymentsimulator.InitializePaymentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.paymentsimulator.RemoveAccount.class.equals(type)) {

				return com.alicnina.paymentsimulator.RemoveAccount.Factory.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.alicnina.paymentsimulator.RemoveAccountResponse.class.equals(type)) {

				return com.alicnina.paymentsimulator.RemoveAccountResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());

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
