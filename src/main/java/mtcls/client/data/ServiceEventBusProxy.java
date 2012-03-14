package mtcls.client.data;

import mtcls.client.event.remote.HasRemoteCallAndService;

public class ServiceEventBusProxy<D> extends EventBusProxy<D>{

	public ServiceEventBusProxy(HasRemoteCallAndService<D> remoteCallAdapter) {
		super(remoteCallAdapter);
	}
	
	@SuppressWarnings("rawtypes")
	public String getServiceName(){
		String retval = null;
		if(getRemoteCallAdapter() == null){
			return retval;
		}
		try{
			HasRemoteCallAndService svc = (HasRemoteCallAndService)getRemoteCallAdapter();
			retval = svc.getServiceName();
		}catch(Exception ignored){
			ignored.printStackTrace(System.err);
		}
		return retval;
	}
}
