package mtcls.client.rpc;

import mtcls.client.controller.app.AppController;
import mtcls.client.event.AuthExpiredEvent;
import mtcls.client.event.remote.RemoteCallEvent;
import mtcls.client.event.remote.RemoteCallEventHandler;
import mtcls.common.constants.RemoteCallEnum;
import mtcls.common.event.remote.RemoteCallTopics;
import mtcls.common.exception.AuthExpiredException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.RequestTimeoutException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.SerializationException;

public abstract class RemoteCall<T> implements AsyncCallback<T> {
	protected EventBus eventBus;
	protected Type<RemoteCallEventHandler<T>> type;

	public RemoteCall(EventBus eventBus){
		setEventBus(eventBus);
	}
	
	protected Type<RemoteCallEventHandler<T>> createType(){
		return new Type<RemoteCallEventHandler<T>>();
	}
	
	/**
	 * implement this delegate to handle remote call invocation
	 * @param config
	 * @param cb
	 */
	protected abstract void invoke(Object config, AsyncCallback<T> cb);
	
	private void call(final Object config, final int retriesLeft) {
		onRemoteCallOut(config);

		invoke(config, new AsyncCallback<T>() {
			public void onFailure(Throwable caught) {
				onRemoteCallIn(config);
				GWT.log(caught.toString(), caught);
				try {
					throw caught;
				} catch (InvocationException invocationException) {
					if (caught.getMessage().equals(RemoteCallEnum.AuthExpired)) {
						AppController.get().getEventBus().fireEvent(
								new AuthExpiredEvent());
						return;
					}

					if (retriesLeft <= 0) {
						RemoteCall.this.onFailure(invocationException);
					} else {
						call(config, retriesLeft - 1); // retry call
					}
				} catch (IncompatibleRemoteServiceException remoteServiceException) {
					Window
							.alert("The app maybe out of date. Reload this page in your browser.");
				} catch (SerializationException serializationException) {
					Window.alert("A serialization error occurred. Try again.");
				} catch (AuthExpiredException e) {
					AppController.get().getEventBus().fireEvent(
							new AuthExpiredEvent());
				} catch (RequestTimeoutException e) {
					Window.alert("This is taking too long, try again");
				} catch (Throwable e) {// application exception
					RemoteCall.this.onFailure(e);
				}
			}

			public void onSuccess(T result) {
				onRemoteCallIn(config);
				RemoteCall.this.onSuccess(result);
			}
		});
	}

	private void onRemoteCallIn(Object config) {
		AppController.get().getEventBus().fireEvent(
				new RemoteCallEvent<T>(getType(), RemoteCallTopics.RemoteCallIn));
	}

	private void onRemoteCallOut(Object config) {
		AppController.get().getEventBus().fireEvent(
				new RemoteCallEvent<T>(getType(), RemoteCallTopics.RemoteCallOut));
	}

	public HandlerRegistration addHandler(RemoteCallEventHandler<T> handler){
		if(handler != null && handler.getRegistration()!=null){
			// only listen to one event at a time
			handler.getRegistration().removeHandler();
		}
		HandlerRegistration retval = getEventBus().addHandler(getType(), handler);
		handler.setRegistration(retval);
		return retval;
	}
	
	public void retry(Object config, int retryCount) {
		call(config, retryCount);
	}

	/**
	 * @return the eventBus
	 */
	public EventBus getEventBus() {
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	/**
	 * @return the type
	 */
	public Type<RemoteCallEventHandler<T>> getType() {
		if(type == null){
			type = createType();
		}
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type<RemoteCallEventHandler<T>> type) {
		this.type = type;
	}
}
