package etf.eminaa.managed;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.axis2.AxisFault;

import com.alicnina.paymentsimulator.PaymentSimulatorStub;
import com.alicnina.paymentsimulator.RegisterAccount;
import com.alicnina.paymentsimulator.RegisterAccountResponse;
import com.alicnina.paymentsimulator.RemoveAccount;
import com.alicnina.paymentsimulator.RemoveAccountResponse;

import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Users;

@ManagedBean
@RequestScoped
public class AccountBean {

	private String creditCardNumber, cvv2;
	private double ammount;
	private String code="", message="";
	private String codeRemove="", messageRemove="";
	private Users user;
	
	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

	
	// implemented methods
	public void saveAccount(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin() == true) {
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
		} else {
			throw new Exception("User is not authorized for this call!");
		}
	}

	public String getSaveAccountMessage() {
		String msg = "Please Add new Account!";
		if (code.equals("102") == true) {
			msg = message;
		} else {
			msg = "Account with Credit Card Number " + creditCardNumber + "  and cvv2 Number " + cvv2 + " (" + ammount + ") is not registered!";
		}
		return msg;
	}

	public void deleteAccount(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin() == true) {
			if (null != creditCardNumber && null != cvv2) {
				RemoveAccount removeAccount = new RemoveAccount();
				removeAccount.setCreditCardNo(creditCardNumber);
				removeAccount.setCvv2(cvv2);
				RemoveAccountResponse removeAccountResponse = new RemoveAccountResponse();
				codeRemove = removeAccountResponse.getCode();
				messageRemove = removeAccountResponse.getMessage();
			}
		} else {
			throw new Exception("User is not authorized for this call!");
		}
	}

	public String getDeleteAccountMessage() {
		String msg = "Please Delete Account!";
		if (codeRemove.equals("102") == true) {
			msg = messageRemove;
		} else {
			msg = "Account with Credit Card Number " + creditCardNumber + "  and cvv2 Number " + cvv2 + " (" + ammount + ") is not removed!";
		}
		return msg;
	}

	// authorization
	public boolean getIsAuthorizedAdmin() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (sessionMap.containsKey("userLoginBean")) {
			Users user = ((UserLoginBean) sessionMap.get("userLoginBean")).getUser();
			if (null != user && null != user.getAuthorities()) {
				Set<Authorities> authorities = user.getAuthorities();
				Iterator<Authorities> it = authorities.iterator();
				while (it.hasNext()) {
					Authorities auth = it.next();
					if (auth.getAuthority().equals("ROLE_ADMIN")) {
						return true;
					}
				}
			}
		}
		return false;
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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCodeRemove() {
		return codeRemove;
	}

	public void setCodeRemove(String codeRemove) {
		this.codeRemove = codeRemove;
	}

	public String getMessageRemove() {
		return messageRemove;
	}

	public void setMessageRemove(String messageRemove) {
		this.messageRemove = messageRemove;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

}
