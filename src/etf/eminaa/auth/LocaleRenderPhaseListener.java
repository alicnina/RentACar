package etf.eminaa.auth;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LocaleRenderPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1950488044542498106L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// do nothing
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		PhaseEvent phaseEvent;
//		FacesContext context = phaseEvent.getFacesContext();
//		Application application = context.getApplication();
//		ExpressionFactory factory = application.getExpressionFactory();
//		ValueExpression valueExpression = factory.createValueExpression(context.getELContext(), "#{userLoginBean}", UserLoginBean.class);
//		UserLoginBean userLoginBean = (UserLoginBean) valueExpression.getValue(context.getELContext());
//		context.getViewRoot().setLocale(userLoginBean.getLocale());

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
