package etf.eminaa.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.alicnina.policeregistersimulator.DisablePoliceRegister;
import com.alicnina.policeregistersimulator.DisablePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.EnablePoliceRegister;
import com.alicnina.policeregistersimulator.EnablePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.RemovePoliceRegister;
import com.alicnina.policeregistersimulator.RemovePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.SavePoliceRegister;
import com.alicnina.policeregistersimulator.SavePoliceRegisterResponse;

@ManagedBean
@RequestScoped
public class PoliceRegisterBean {
	
	private String drivingLicenceNumber, idNumber;
	private boolean isEnabled;
	
	private String code, message;
	private String codeRemove, messageRemove;
	private String codeEnable, messageEnable;
	private String codeDisable, messageDisable;
	
	// implemented methods
	public void saveRegister(AjaxBehaviorEvent event) {
		if (null != idNumber && null != drivingLicenceNumber) {
			SavePoliceRegister policeReg = new SavePoliceRegister();
			policeReg.setIdNumber(idNumber);
			policeReg.setDrivingLicenceNumber(drivingLicenceNumber);
			policeReg.setEnabledRegister(true);
			SavePoliceRegisterResponse policeRegResponse = new SavePoliceRegisterResponse();
			code = policeRegResponse.getCode();
			message = policeRegResponse.getMessage();
		}
	}
	public String saveAccountMessage() {
		String msg = "Please Add new Account!";
		if (code == "102") {
			msg = message;
		} else {
			msg = "Account with ID Number " + idNumber + "  and Driving Licence Number " + drivingLicenceNumber + " is not registered!";
		}
		return msg;
	}
	
	public void deleteAccount(AjaxBehaviorEvent event) {
		if (null != idNumber && null != drivingLicenceNumber) {
			RemovePoliceRegister removePoliceReg = new RemovePoliceRegister();
			removePoliceReg.setIdNumber(idNumber);
			removePoliceReg.setDrivingLicenceNumber(drivingLicenceNumber);
			RemovePoliceRegisterResponse removePoliceRegResponse = new RemovePoliceRegisterResponse();
			codeRemove = removePoliceRegResponse.getCode();
			messageRemove = removePoliceRegResponse.getMessage();
		}
	}
	public String deleteAccountMessage() {
		String msg = "Please Add new Account!";
		if (codeRemove == "102") {
			msg = messageRemove;
		} else {
			msg = "Account with ID Number " + idNumber + "  and Driving Licence Number " + drivingLicenceNumber + " is not removed!";
		}
		return msg;
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
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public void enableRegister(AjaxBehaviorEvent event) {
		if (null != idNumber && null != drivingLicenceNumber) {
			EnablePoliceRegister policeReg = new EnablePoliceRegister();
			policeReg.setDrivingLicenceNumber(drivingLicenceNumber);
			EnablePoliceRegisterResponse policeRegResponse = new EnablePoliceRegisterResponse();
			codeEnable = policeRegResponse.getCode();
			messageEnable = policeRegResponse.getMessage();
		}
	}
	public String enableRegisterMessage() {
		String msg = "Please Add new Account!";
		if (codeEnable == "303") {
			msg = messageEnable;
		} else {
			msg = "Account with Driving Licence Number " + drivingLicenceNumber + " is not enabled!";
		}
		return msg;
	}
	
	public void disableRegister(AjaxBehaviorEvent event) {
		if (null != idNumber && null != drivingLicenceNumber) {
			DisablePoliceRegister policeRegDisable = new DisablePoliceRegister();
			policeRegDisable.setDrivingLicenceNumber(drivingLicenceNumber);
			DisablePoliceRegisterResponse policeRegResponse = new DisablePoliceRegisterResponse();
			codeDisable = policeRegResponse.getCode();
			messageDisable = policeRegResponse.getMessage();
		}
	}
	public String disableRegisterMessage() {
		String msg = "Please Add new Account!";
		if (codeEnable == "300") {
			msg = messageEnable;
		} else {
			msg = "Account with Driving Licence Number " + drivingLicenceNumber + " is not enabled!";
		}
		return msg;
	}

}
