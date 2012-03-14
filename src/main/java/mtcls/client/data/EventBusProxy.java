package mtcls.client.data;

import mtcls.client.event.remote.HasRemoteCall;
import mtcls.client.event.remote.RemoteCallEvent;
import mtcls.client.event.remote.RemoteCallEventHandler;
import mtcls.common.event.TopicGroup;
import mtcls.common.event.remote.RemoteCallTopicGroups;
import mtcls.common.event.remote.RemoteCallTopics;

import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.DataReader;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class EventBusProxy<D> implements DataProxy<D>,
		RemoteCallEventHandler<D> {
	protected HasRemoteCall<D> remoteCallAdapter;
	protected boolean eventBusAttached;
	protected HandlerRegistration registration;
	protected EventBus eventBus;
	protected TopicGroup topicGroup;
	protected AsyncCallback<D> callback;
	protected DataReader<D> reader;
	protected Object loadConfig;

	public EventBusProxy(HasRemoteCall<D> remoteCallAdapter) {
		setRemoteCallAdapter(remoteCallAdapter);
	}

	@Override
	public void load(DataReader<D> reader, Object loadConfig,
			AsyncCallback<D> callback) {
		if (!isEventBusAttached()) {
			attachEventBus();
		}
		setReader(reader);
		setLoadConfig(loadConfig);
		setCallback(callback);
//		int retryCount = getRemoteCallAdapter().getRetryCount();
//		getRemoteCallAdapter().getRemoteCall().retry(loadConfig, retryCount);
		getRemoteCallAdapter().invoke(loadConfig);
	}

	protected void attachEventBus() {
//		setRegistration(getEventBus().addHandler(RemoteCallEvent.TYPE, this));
		getRemoteCallAdapter().bind(this);
		setEventBusAttached(true);
	}

	/**
	 * @return the remoteCallAdapter
	 */
	public HasRemoteCall<D> getRemoteCallAdapter() {
		return remoteCallAdapter;
	}

	/**
	 * @param remoteCallAdapter
	 *            the remoteCallAdapter to set
	 */
	public void setRemoteCallAdapter(
			HasRemoteCall<D> remoteCallAdapter) {
		this.remoteCallAdapter = remoteCallAdapter;
	}

	/**
	 * @return the eventBusAttached
	 */
	public boolean isEventBusAttached() {
		return eventBusAttached;
	}

	/**
	 * @param eventBusAttached
	 *            the eventBusAttached to set
	 */
	public void setEventBusAttached(boolean eventBusAttached) {
		this.eventBusAttached = eventBusAttached;
	}

	/**
	 * @return the registration
	 */
	public HandlerRegistration getRegistration() {
		return registration;
	}

	/**
	 * @param registration
	 *            the registration to set
	 */
	public void setRegistration(HandlerRegistration registration) {
		this.registration = registration;
	}

	/**
	 * @return the eventBus
	 */
	public EventBus getEventBus() {
		if(eventBus==null){
			eventBus = getRemoteCallAdapter().getEventBus();
		}
		return eventBus;
	}

	/**
	 * @param eventBus
	 *            the eventBus to set
	 */
	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	public TopicGroup getTopicGroup() {
		if (topicGroup == null) {
			topicGroup = RemoteCallTopicGroups.RemoteCallSuccessOrFailed;
		}
		return topicGroup;
	}

	/**
	 * @param topicGroup
	 *            the topicGroup to set
	 */
	public void setTopicGroup(TopicGroup topicGroup) {
		this.topicGroup = topicGroup;
	}

	@Override
	public void onRemoteCall(RemoteCallEvent<D> event) {
		if (RemoteCallTopics.RemoteCallSuccess.equals(event.getEventTopic())) {
			D result = event.getResult();
			try {
				D d = null;
				if (getReader() != null) {
					d = getReader().read(getLoadConfig(), result);
				} else {
					getCallback().onSuccess(result);
				}
				getCallback().onSuccess(d);
			} catch (Exception e) {
				getCallback().onFailure(e);
				e.printStackTrace(System.err);
			}
		} else if (RemoteCallTopics.RemoteCallFailed.equals(event
				.getEventTopic())) {
			Throwable t = event.getError();
			getCallback().onFailure(t);
		}
	}

	/**
	 * @return the callback
	 */
	public AsyncCallback<D> getCallback() {
		return callback;
	}

	/**
	 * @param callback
	 *            the callback to set
	 */
	public void setCallback(AsyncCallback<D> callback) {
		this.callback = callback;
	}

	/**
	 * @return the reader
	 */
	public DataReader<D> getReader() {
		return reader;
	}

	/**
	 * @param reader
	 *            the reader to set
	 */
	public void setReader(DataReader<D> reader) {
		this.reader = reader;
	}

	/**
	 * @return the loadConfig
	 */
	public Object getLoadConfig() {
		return loadConfig;
	}

	/**
	 * @param loadConfig
	 *            the loadConfig to set
	 */
	public void setLoadConfig(Object loadConfig) {
		this.loadConfig = loadConfig;
	}

}
