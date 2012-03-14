package mtcls.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AuthExpiredEvent extends GwtEvent<AuthExpiredEventHandler> {
	public static Type<AuthExpiredEventHandler> TYPE = new Type<AuthExpiredEventHandler>();

	public AuthExpiredEvent() {
	}

	@Override
	public Type<AuthExpiredEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AuthExpiredEventHandler handler) {
		handler.onAuthExpired(this);
	}
}
