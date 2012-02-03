package etf.eminaa.auth;

import java.util.Map;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import com.vaannila.domain.Users;
import com.vaannila.managed.UserLoginBean;

public class AuthorizationPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -8733645465524331851L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		HttpServletRequest req = (HttpServletRequest) (arg0.getFacesContext().getExternalContext().getRequest());
		Map<String, Object> sessMap = arg0.getFacesContext().getExternalContext().getSessionMap();
		String putanja = req.getRequestURI();
		if (!(putanja.contains("user-login.xhtml") || putanja.contains("user-login.jsf")) && !(putanja.contains("user-register.xhtml") || putanja.contains("user-register.jsf"))) {
			// pristup svim stranicama osim navedenim je zabranjen ukoliko user
			// nije logovan
			// pronadjemo User objekat u sessionMap, ako postoji
			UserLoginBean userLoginBean = (UserLoginBean) (sessMap.get("userLoginBean"));
			if (null == userLoginBean || null == userLoginBean.getUser()) {
				// ako ne postoji, prosljedimo korisnika na user-login.xhtml
				// metoda handleNavigation utice na navigaciono pravilo
				// argumenti metode su: FacesContext, fromAction (u ovom slucaju
				// nije bitan, pa je null) i outcome
				// nakon poziva metode, ignorise se prethodno navigaciono
				// pravilo i aplikacija trazi novo
				// efekat promjene navigacionog pravila je i jos jedan prolaz
				// kroz ovu metodu (i.e. afterPhase)
				arg0.getFacesContext().getApplication().getNavigationHandler()
						.handleNavigation(arg0.getFacesContext(), null, "user-login.jsf?faces-redirect=true");
			}

			return;
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// do nothing
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
