package mtcls.client.event.remote;

public interface HasRemoteCallAndService<T> extends HasRemoteCall<T> {
	
	public String getServiceName();
	
	public void setServiceName(String serviceName);
}
