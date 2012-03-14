package mtcls.client.controller.remote;

import mtcls.client.event.remote.HasRemoteCall;
import mtcls.client.event.remote.RemoteCallEventHandler;
import mtcls.client.rpc.RemoteCall;

public interface RemoteCallController {
	
	public void invoke(String name, Object config);
	
	public void invoke(String name, Object config, int retryCount);
	
	public <X> RemoteCall<X> getRemoteCall(String name);	
	
	public <X> void bind(String remoteCallName, 
							RemoteCallEventHandler<X> handler);
	
	public <X> void bindAndCall(String remoteCallName, 
								RemoteCallEventHandler<X> handler, 
								Object config);
	
	public <X> HasRemoteCall<X> getRemoteCallAdapter(String remoteCallName);
	
	public void load(String remoteCallName, Object config);
	
	public int getRetryCount();
	
	
}
