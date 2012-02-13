

/**
 * PaymentSimulator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.alicnina.paymentsimulator;

    /*
     *  PaymentSimulator java interface
     */

    public interface PaymentSimulator {
          

        /**
          * Auto generated method signature
          * 
                    * @param registerAccount
                
         */

         
                     public com.alicnina.paymentsimulator.RegisterAccountResponse registerAccount(

                        com.alicnina.paymentsimulator.RegisterAccount registerAccount)
                        throws java.rmi.RemoteException
             ;

        

        /**
          * Auto generated method signature
          * 
                    * @param initializePayment
                
         */

         
                     public com.alicnina.paymentsimulator.InitializePaymentResponse initializePayment(

                        com.alicnina.paymentsimulator.InitializePayment initializePayment)
                        throws java.rmi.RemoteException
             ;

        

        /**
          * Auto generated method signature
          * 
                    * @param removeAccount
                
         */

         
                     public com.alicnina.paymentsimulator.RemoveAccountResponse removeAccount(

                        com.alicnina.paymentsimulator.RemoveAccount removeAccount)
                        throws java.rmi.RemoteException
             ;

        

        
       //
       }
    