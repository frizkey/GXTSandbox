package mtcls.client.event.remote;

import mtcls.client.event.AbstractTopicEvent;
import mtcls.common.event.EventTopic;

//@SuppressWarnings("rawtypes")
public class RemoteCallEvent<T> extends AbstractTopicEvent<RemoteCallEventHandler<T>> {
//	public static final Type<RemoteCallEventHandler> TYPE = new Type<RemoteCallEventHandler>();
	protected T result;
	
	public RemoteCallEvent(Type<RemoteCallEventHandler<T>> associatedType, EventTopic eventTopic) {
		this(associatedType, eventTopic, null, null);
	}
	
	public RemoteCallEvent(Type<RemoteCallEventHandler<T>> associatedType, EventTopic eventTopic, Throwable error){
		this(associatedType, eventTopic, null, error);
	}
	
	public RemoteCallEvent(Type<RemoteCallEventHandler<T>> associatedType, EventTopic eventTopic, T result){
		this(associatedType, eventTopic, result, null);
	}
	
	public RemoteCallEvent(Type<RemoteCallEventHandler<T>> associatedType, EventTopic eventTopic, T result, Throwable error){
		super(associatedType, eventTopic, error);
		setResult(result);
	}

//	@Override
//	public Type<RemoteCallEventHandler> getAssociatedType() {
//		return TYPE;
//	}
	
	@Override
	protected void doDispatch(RemoteCallEventHandler<T> handler){
		handler.onRemoteCall(this);
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}

//	/**
//	 * @return the associatedType
//	 */
//	@Override
//	public Type<RemoteCallEventHandler<T>> getAssociatedType() {
//		return associatedType;
//	}
//
//	/**
//	 * @param associatedType the associatedType to set
//	 */
//	public void setAssociatedType(Type<RemoteCallEventHandler<T>> associatedType) {
//		this.associatedType = associatedType;
//	}
}
