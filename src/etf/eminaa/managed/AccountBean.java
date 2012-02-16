package etf.eminaa.managed;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.axis2.AxisFault;

import com.alicnina.paymentsimulator.PaymentSimulatorStub;
import com.alicnina.paymentsimulator.RegisterAccount;
import com.alicnina.paymentsimulator.RegisterAccountResponse;
import com.alicnina.paymentsimulator.RemoveAccount;
import com.alicnina.paymentsimulator.RemoveAccountResponse;

import etf.eminaa.domain.Users;

@ManagedBean
@RequestScoped
public class AccountBean {

	private String creditCardNumber, cvv2;
	private double ammount;
	private String code, message;
	private String codeRemove, messageRemove;
	
	public AccountBean() {
		UserLoginBean userLoginBean = (UserLoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoginBean");
		Users user = userLoginBean.getUser();
	}

	// implemented methods
	public void saveAccount(AjaxBehaviorEvent event) {
		if (null != creditCardNumber && null != cvv2) {
			RegisterAccount regAccount = new RegisterAccount();
			regAccount.setCreditCardNo(creditCardNumber);
			regAccount.setCvv2(cvv2);
			regAccount.setAmmount(ammount);

			try {
				PaymentSimulatorStub client = new PaymentSimulatorStub();
				RegisterAccountResponse regAccountResponse = client.registerAccount(regAccount);

				code = regAccountResponse.getCode();
				message = regAccountResponse.getMessage();
			} catch (AxisFault e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getSaveAccountMessage() {
		String msg = "Please Add new Account!";
		if (code.equals("102")) {
			msg = message;
		} else {
			msg = "Account with Credit Card Number " + creditCardNumber + "  and cvv2 Number " + cvv2 + " (" + ammount + ") is not registered!";
		}
		return msg;
	}

	public void deleteAccount(AjaxBehaviorEvent event) {
		if (null != creditCardNumber && null != cvv2) {
			RemoveAccount removeAccount = new RemoveAccount();
			removeAccount.setCreditCardNo(creditCardNumber);
			removeAccount.setCvv2(cvv2);
			RemoveAccountResponse removeAccountResponse = new RemoveAccountResponse();
			codeRemove = removeAccountResponse.getCode();
			messageRemove = removeAccountResponse.getMessage();
		}
	}

	public String getDeleteAccountMessage() {
		String msg = "Please Add new Account!";
		if (codeRemove.equals("102")) {
			msg = messageRemove;
		} else {
			msg = "Account with Credit Card Number " + creditCardNumber + "  and cvv2 Number " + cvv2 + " (" + ammount + ") is not removed!";
		}
		return msg;
	}

	// getters and setters
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

}
