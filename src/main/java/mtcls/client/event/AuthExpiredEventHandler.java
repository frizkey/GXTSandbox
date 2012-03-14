package mtcls.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AuthExpiredEventHandler extends EventHandler {
	void onAuthExpired(AuthExpiredEvent event);
}