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

import com.alicnina.policeregistersimulator.DisablePoliceRegister;
import com.alicnina.policeregistersimulator.DisablePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.EnablePoliceRegister;
import com.alicnina.policeregistersimulator.EnablePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.PoliceRegisterSimulatorStub;
import com.alicnina.policeregistersimulator.RemovePoliceRegister;
import com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.SavePoliceRegister;
import com.alicnina.policeregistersimulator.SavePoliceRegisterResponse;

import etf.eminaa.domain.Authorities;
import etf.eminaa.domain.Users;

@ManagedBean
@RequestScoped
public class PoliceRegisterBean {

	private String idNumber, drivingLicenceNumber;

	private String code = "", message = "";
	private String codeRemove = "", messageRemove = "";
	private String codeEnable = "", messageEnable = "";
	private String codeDisable = "", messageDisable = "";

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

	// implemented methods
	public void saveRegister(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin() == true) {
			if (null != idNumber && null != drivingLicenceNumber) {
				SavePoliceRegister policeReg = new SavePoliceRegister();
				policeReg.setIdNumber(idNumber);
				policeReg.setDrivingLicenceNumber(drivingLicenceNumber);
				policeReg.setEnabledRegister(true);

				try {
					PoliceRegisterSimulatorStub client = new PoliceRegisterSimulatorStub();
					SavePoliceRegisterResponse policeRegResponse = client.savePoliceRegister(policeReg);

					code = policeRegResponse.getCode();
					message = policeRegResponse.getMessage();

				} catch (AxisFault e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getSaveRegisterMessage() {
		String msg = "Please Add new Account!";
		if (code.equals("200") == true) {
			msg = message;
		} else {
			msg = "Account with ID Number " + idNumber + "  and Driving Licence Number " + drivingLicenceNumber + " is not REGISTERED!";
		}
		return msg;
	}

	public void removeRegister(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin() == true) {
			if (idNumber != null  && drivingLicenceNumber != null) {
				RemovePoliceRegister removePoliceReg = new RemovePoliceRegister();
				removePoliceReg.setIdNumber(idNumber);
				removePoliceReg.setDrivingLicenceNumber(drivingLicenceNumber);

				try {
					PoliceRegisterSimulatorStub polRegSimStub = new PoliceRegisterSimulatorStub();
					RemovePoliceRegisterResponse removePoliceRegResponse = polRegSimStub.removePoliceRegister(removePoliceReg);

					codeRemove = removePoliceRegResponse.getCode();
					messageRemove = removePoliceRegResponse.getMessage();

				} catch (AxisFault e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new Exception("User is not authorized for this call !");
		}
	}

	public String getRemoveRegisterMessage() {
		String msg = "Please Remove Desired Account!";
		if (codeRemove.equals("102") == true) {
			msg = messageRemove;
		} else {
			msg = "Account with ID Number " + idNumber + "  and Driving Licence Number " + drivingLicenceNumber + " is not REMOVED!";
		}
		return msg;
	}

	public void enableRegister(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin() == true) {
			if (drivingLicenceNumber.isEmpty() != false && drivingLicenceNumber != null) {
				EnablePoliceRegister enablePoliceReg = new EnablePoliceRegister();
				enablePoliceReg.setDrivingLicenceNumber(drivingLicenceNumber);

				try {
					PoliceRegisterSimulatorStub polRegSimStub = new PoliceRegisterSimulatorStub();
					EnablePoliceRegisterResponse policeRegResponse = polRegSimStub.enablePoliceRegister(enablePoliceReg);

					codeEnable = policeRegResponse.getCode();
					messageEnable = policeRegResponse.getMessage();
				} catch (AxisFault e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				new EnablePoliceRegisterResponse();
			}
		} else {
			throw new Exception("User is not authorized for this call !");
		}
	}

	public String getEnableRegisterMessage() {
		String msg = "Please Type Desired Driving licence NUmber You want to enable!";
		if (codeEnable.equals("303") == true) {
			msg = messageEnable;
		} else {
			msg = "Account with Driving Licence Number " + drivingLicenceNumber + " cannot be enabled!";
		}
		return msg;
	}

	public void disableRegister(AjaxBehaviorEvent event) throws Exception {
		if (getIsAuthorizedAdmin() == true) {
			if (drivingLicenceNumber.isEmpty() != false && drivingLicenceNumber != null) {
				DisablePoliceRegister policeRegDisable = new DisablePoliceRegister();
				policeRegDisable.setDrivingLicenceNumber(drivingLicenceNumber);

				try {
					PoliceRegisterSimulatorStub polRegSimStub = new PoliceRegisterSimulatorStub();
					DisablePoliceRegisterResponse policeRegResponse = polRegSimStub.disablePoliceRegister(policeRegDisable);

					codeDisable = policeRegResponse.getCode();
					messageDisable = policeRegResponse.getMessage();
				} catch (AxisFault e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new Exception("User is not authorized for this call !");
		}
	}
	
	public String getDisableRegisterMessage() {
		String msg = "Please Type Desired Driving licence NUmber You want to Disable!";
		if (codeDisable.equals("300") == true) {
			msg = messageDisable;
		} else {
			msg = "Account with Driving Licence Number " + drivingLicenceNumber + "  cannot be disabled!";
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
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}

	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
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

	public String getCodeEnable() {
		return codeEnable;
	}

	public void setCodeEnable(String codeEnable) {
		this.codeEnable = codeEnable;
	}

	public String getMessageEnable() {
		return messageEnable;
	}

	public void setMessageEnable(String messageEnable) {
		this.messageEnable = messageEnable;
	}
	
	public String getCodeDisable() {
		return codeDisable;
	}

	public void setCodeDisable(String codeDisable) {
		this.codeDisable = codeDisable;
	}

	public String getMessageDisable() {
		return messageDisable;
	}

	public void setMessageDisable(String messageDisable) {
		this.messageDisable = messageDisable;
	}
}
