package etf.eminaa.managed;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.axis.AxisFault;
import org.apache.axis2.context.ConfigurationContext;

import com.alicnina.paymentsimulator.InitializePayment;
import com.alicnina.paymentsimulator.InitializePaymentResponse;
import com.alicnina.paymentsimulator.PaymentSimulatorStub;
import com.alicnina.policeregistersimulator.InitializePoliceRegister;
import com.alicnina.policeregistersimulator.InitializePoliceRegisterResponse;
import com.alicnina.policeregistersimulator.PoliceRegisterSimulatorStub;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

@ManagedBean
@RequestScoped
public class VehicleRentalBean implements Serializable {

	private static final long serialVersionUID = -2732925993048187311L;


	private Vehicle vehicle = new Vehicle();
	private Rental rental;
	private Users user;

	private Date startDate;
	private String numberDays;
	private String status;

	private String creditCardNumber, cvv2;
	
	InitializePoliceRegister initPoliceRegister = new InitializePoliceRegister();
	InitializePoliceRegisterResponse initPoliceRegResponse;
	InitializePayment initPayment = new InitializePayment();
	InitializePaymentResponse initPaymentResponse;
	String codePR, messagePR;
	String codePS, messagePS;


	public VehicleRentalBean() {
		UserLoginBean userLoginBean = (UserLoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLoginBean");
		user = userLoginBean.getUser();
	}

	@ManagedProperty(value = "#{vehicleDAO}")
	private DAOInterface<Vehicle> vehicleDao;

	@ManagedProperty(value = "#{rentalDAO}")
	private DAOInterface<Rental> rentalDao;

	@ManagedProperty(value = "#{usersDAO}")
	private DAOInterface<Users> usersDao;

	@ManagedProperty(value = "#{userLoginBean}")
	private UserLoginBean userLoginBean;

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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public DAOInterface<Vehicle> getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(DAOInterface<Vehicle> vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public UserLoginBean getUserLoginBean() {
		return userLoginBean;
	}

	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getNumberDays() {
		return numberDays;
	}

	public void setNumberDays(String numberDays) {
		this.numberDays = numberDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DAOInterface<Rental> getRentalDao() {
		return rentalDao;
	}

	public void setRentalDao(DAOInterface<Rental> rentalDao) {
		this.rentalDao = rentalDao;
	}

	public DAOInterface<Users> getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(DAOInterface<Users> usersDao) {
		this.usersDao = usersDao;
	}

	// implemented methods
	public void vehicleRentalSave(AjaxBehaviorEvent event) {
		try {
			vehicleRentalService(true);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

	public void vehicleReservationSave(AjaxBehaviorEvent event) throws AxisFault {
		vehicleRentalService(false);
	}

	private void vehicleRentalService(boolean isPayed) throws AxisFault {
		Rental rental = new Rental();
		rental.setUsers(user);
		vehicle = vehicleDao.findByPrimaryKey(vehicle.getId());
		rental.setVehicle(vehicle);
		rental.setStartDate(new java.sql.Date(startDate.getTime()));
		int numDays = Integer.parseInt(numberDays.trim());
		rental.setNumberDays(numDays);

		initPoliceRegister.setDrivingLicenceNumber(user.getDrivingLicenceNumber());
		initPoliceRegister.setIdNumber(user.getIdNumber());
		
		try {
			PoliceRegisterSimulatorStub polRegSim = new PoliceRegisterSimulatorStub();
			initPoliceRegResponse = polRegSim.initializePoliceRegister(initPoliceRegister);
		} catch (org.apache.axis2.AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		
		codePR = initPoliceRegResponse.getCode();
		messagePR = initPoliceRegResponse.getMessage();

		if (isPayed && codePR.equals("101")== true) {
			
			initPayment.setCreditCardNo(creditCardNumber);
			initPayment.setCvv2(cvv2);
			int totalAmount = Integer.parseInt(numberDays) * Integer.parseInt(vehicle.getRentPricePerDay());
			initPayment.setAmmount(totalAmount);
			
			try {
				//PaymentSimulatorStub paySim = new PaymentSimulatorStub("http://localhost:7001/RentACarWebServices/services/OnlinePayment");
				PaymentSimulatorStub paySim = new PaymentSimulatorStub();
				initPaymentResponse = paySim.initializePayment(initPayment);
			} catch (org.apache.axis2.AxisFault e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (RemoteException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			
			codePS = initPaymentResponse.getCode();
			messagePS = initPaymentResponse.getMessage();
			if (codePS.equals("101")== true) {
				
				rentalDao.save(rental);
				vehicle.setStatus("RENTED");
				vehicleDao.edit(vehicle);
			}
		} else if (!isPayed && codePR.equals("101")== true) {
			rentalDao.save(rental);
			vehicle.setStatus("RESERVATION");
			vehicleDao.edit(vehicle);
		}
	}

	public String getRentalWelcome() {
		String msg = "welcome";
		if (null != codePR ){
			msg = messagePR;
		}
		else if (null != codePS){
			msg = messagePS;
		}
		return msg;
	}

}
