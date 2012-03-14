package mtcls.client.data;

import mtcls.client.event.remote.HasRemoteCallAndService;
import mtcls.client.event.remote.RemoteCallEvent;
import mtcls.common.event.remote.RemoteCallTopics;
import mtcls.common.model.service.ServiceListLoadResult;

public class BaseServiceEventBusProxy<D> extends ServiceEventBusProxy<D> {
	public BaseServiceEventBusProxy(HasRemoteCallAndService<D> remoteCallAdapter) {
		super(remoteCallAdapter);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void onRemoteCall(RemoteCallEvent<D> event) {
		if (RemoteCallTopics.RemoteCallSuccess.equals(event.getEventTopic())) {
			D result = event.getResult();
			try {
				if(result != null && result instanceof ServiceListLoadResult){
					ServiceListLoadResult svcResult = (ServiceListLoadResult)result;
					String targetService = svcResult.getServiceName();
					if(getServiceName()!=null && getServiceName().equalsIgnoreCase(targetService)){
						D data = null;
						if (reader != null) {
							data = getReader().read(loadConfig, result);
						} else {
							data = (D) result;
						}
						getCallback().onSuccess(data);						
					}
				}else{
					return;
				}
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
}

