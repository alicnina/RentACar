package etf.eminaa.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.holders.StringHolder;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alicnina.paymentsimulator.InitializePayment;
import com.alicnina.paymentsimulator.InitializePaymentResponse;
import com.alicnina.paymentsimulator.PaymentSimulatorStub;

import etf.eminaa.dao.DAOInterface;
import etf.eminaa.domain.Rental;
import etf.eminaa.domain.Users;
import etf.eminaa.domain.Vehicle;

public class RentalController extends MultiActionController {

	protected static Logger logger = Logger.getLogger("controller");

	private DAOInterface<Rental> rentalDAO;

	public void setRentalDAO(DAOInterface<Rental> rentalDAO) {
		this.rentalDAO = rentalDAO;
	}

	private DAOInterface<Vehicle> vehicleDAO;

	public void setVehicleDAO(DAOInterface<Vehicle> vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	private DAOInterface<Users> usersDAO;

	public void setUsersDAO(DAOInterface<Users> usersDAO) {
		this.usersDAO = usersDAO;
	}

	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String rentalId = request.getParameter("rentalId");
		String username = request.getParameter("username");
		String vehicleId = request.getParameter("vehicleId");
		//String startDate = request.getParameter("startDate");
		int numberDays = Integer.valueOf(request.getParameter("numberDays"));
		String creditCardNumber = request.getParameter("creditCardNumber");
		String cvv2 = request.getParameter("cvv2");

		Rental newRental = rentalDAO.findByPrimaryKey(Integer.valueOf(rentalId));
		Vehicle userChoiceVehicle = vehicleDAO.findByPrimaryKey(Integer.valueOf(vehicleId));

		double ammount = numberDays * Double.valueOf(userChoiceVehicle.getRentPricePerDay());
		if (isValidCreditCard(creditCardNumber, cvv2, ammount) && isValidUser(username)) {
			//newRental.setStartDate(startDate);
			newRental.setNumberDays(numberDays);
			rentalDAO.save(newRental);
		} else {
			rentalDAO.delete(newRental);
		}

		return new ModelAndView("redirect:list.htm");
	}

	public boolean isValidCreditCard(String creditCardNumber, String cvv2, double ammount) throws RemoteException, MalformedURLException {

		// Check financial status of user!
		InitializePayment initializePayment2 = new InitializePayment();
		initializePayment2.setCreditCardNo(creditCardNumber);
		initializePayment2.setCvv2(cvv2);
		initializePayment2.setAmmount(ammount);
		PaymentSimulatorStub paymentService = new PaymentSimulatorStub("http://localhost:7100/RentACarWebServices/services/OnlinePayment");
		InitializePaymentResponse initializePaymentResponse = paymentService.initializePayment(initializePayment2);

		// TODO: check if response code is valid
		// initializePaymentResponse.getCode() ...

		return true;
	}

	public boolean isValidUser(String username) {

		// TODO: Check drivers licence validness via Police Register Simulator
		// Service (SOAP)

		return true;
	}

	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("rentalList", rentalDAO.list());
		modelMap.addAttribute("rental", new Rental());
		return new ModelAndView("rentalForm", modelMap);
	}

	public ModelAndView getRent(HttpServletRequest request, HttpServletResponse response, Vehicle vehicle) throws Exception {
		ModelMap modelMap = new ModelMap();
		logger.debug("Received request to show edit page");

		// TODO: get user object from spring security (or session)

		Rental newRental = new Rental();

		// set fake user
		newRental.setUsers(usersDAO.list().get(0));

		newRental.setVehicle(vehicleDAO.findByPrimaryKey(vehicle.getId()));
		//newRental.setStartDate(new Date(System.currentTimeMillis()).toString());

		// temporary save (rental process not finished yet!)
		rentalDAO.save(newRental);

		modelMap.addAttribute("rentalId", newRental.getId());
		modelMap.addAttribute("username", newRental.getUsers().getUsername());
		modelMap.addAttribute("vehicleId", newRental.getVehicle().getId());
		return new ModelAndView("rentalForm", modelMap);
	}

}
