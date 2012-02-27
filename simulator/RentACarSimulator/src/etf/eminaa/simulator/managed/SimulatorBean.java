package etf.eminaa.simulator.managed;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpRequest;

@ManagedBean
@SessionScoped
public class SimulatorBean {

	private int vehicleId = 0;

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	private boolean isStarted = false;
	private String fFileName = "path.csv";
	private String fEncoding = "utf-8";

	HttpClient httpClient = new HttpClient();

	private ExecutorService executor = Executors.newCachedThreadPool();
	private Simulator sim;

	class Simulator implements Runnable {

		private boolean isRunning;

		public boolean isRunning() {
			return isRunning;
		}

		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}

		public Simulator(boolean isRunning) {
			this.isRunning = isRunning;
		}

		public void run() {
			System.out.println("Reading from file.");
			Scanner scanner = null;
			try {
				scanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream(fFileName), fEncoding);
				while (scanner.hasNextLine() && isRunning) {
					sendToRentACarRest(scanner.nextLine());
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} finally {
				if (null != scanner) {
					scanner.close();
				}
			}

		}
	}

	private void sendToRentACarRest(String nextLine) {

		String[] vals = nextLine.split(",");
		String longitude = vals[0];
		String latitude = vals[1];
		String altitude = vals[2];

		PostMethod post = new PostMethod("http://localhost:8080/RentACar/location/" + vehicleId);
		post.setParameter("latitude", latitude);
		post.setParameter("longitude", longitude);
		post.setParameter("altitude", altitude);

		try {
			httpClient.executeMethod(post);
		} catch (HttpException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		System.out.println(nextLine);
	}

	public void startSimulator(AjaxBehaviorEvent event) {
		isStarted = true;
		sim = new Simulator(isStarted);
		executor.execute(sim);
	}

	public void finishSimulator(AjaxBehaviorEvent event) {
		// FIXME: the SimulatorBean needs to be singleton, otherwise the
		// simulator can never be stopped if session expires!

		isStarted = false;
		if (null != sim) {
			sim.setRunning(isStarted);
		}
	}

	public String getCurrentStatus() {
		return isStarted ? "Running!" : "Stopped!";
	}

}
