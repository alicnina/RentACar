package etf.eminaa.managed;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

			try {
				PoliceRegisterSimulatorStub polRegSimStub = new PoliceRegisterSimulatorStub();
				SavePoliceRegisterResponse policeRegResponse = polRegSimStub.savePoliceRegister(policeReg);

				code = policeRegResponse.getCode();
				message = policeRegResponse.getMessage();

			} catch (AxisFault e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public String getSaveAccountMessage() {
		String msg = "Please Add new Account!";
		if (code.equals("102")) {
			msg = message;
		} else {
			msg = "Account with ID Number " + idNumber + "  and Driving Licence Number " + drivingLicenceNumber + " is not registered!";
		}
		return msg;
	}

	public void removeRegister(AjaxBehaviorEvent event) {
		if (null != idNumber && null != drivingLicenceNumber) {
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
	}

	public String getRemoveRegisterMessage() {
		String msg = "Please Add new Account!";
		if (codeRemove.equals("102")) {
			msg = messageRemove;
		} else {
			msg = "Account with ID Number " + idNumber + "  and Driving Licence Number " + drivingLicenceNumber + " is not removed!";
		}
		return msg;
	}

	public void enableRegister(AjaxBehaviorEvent event) {
		if (null != idNumber && null != drivingLicenceNumber) {
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
	}

	public String getEnableRegisterMessage() {
		String msg = "Please Add new Account!";
		if (codeEnable.equals("303")) {
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
			
			try {
				PoliceRegisterSimulatorStub polRegSimStub = new PoliceRegisterSimulatorStub();
				DisablePoliceRegisterResponse policeRegResponse = polRegSimStub.disablePoliceRegister(policeRegDisable);
						
				codeDisable = policeRegResponse.getCode();
				messageDisable = policeRegResponse.getMessage();
			} catch (AxisFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public String getDisableRegisterMessage() {
		String msg = "Please Add new Account!";
		if (codeDisable.equals("300")) {
			msg = messageDisable;
		} else {
			msg = "Account with Driving Licence Number " + drivingLicenceNumber + " is disabled!";
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

}
