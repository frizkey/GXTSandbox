package mtcls.client.controller.remote;

import java.util.Map;

import mtcls.client.event.remote.HasRemoteCall;
import mtcls.client.event.remote.RemoteCallEventHandler;
import mtcls.client.rpc.RemoteCall;
import mtcls.client.rpc.RemoteCallConstants;

@SuppressWarnings("rawtypes")
public class AbstractRemoteCallController implements RemoteCallController {
	protected int retryCount = RemoteCallConstants.RETRY_DEFAULT;
	protected Map<String, RemoteCall> remoteCallMap;
	protected Map<String, HasRemoteCall> remoteCallAdapterMap;
	
//	protected Map<String, RemoteCallbackPair<?>> createRemoteCallPairMap(){
//		Map<String, RemoteCallbackPair<?>> retval = new HashMap<String, RemoteCallbackPair<?>>();
//		return retval;
//	}
	/**
	 * Override this method for application remote call mapping
	 */
	protected Map<String, RemoteCall> createRemoteCallMap(){
		return null;
	}
	
	protected Map<String, HasRemoteCall> createRemoteCallAdapterMap(){
		return null;
	}
	
	@Override
	public <X> void bind(String remoteCallName,
			RemoteCallEventHandler<X> handler) {
		RemoteCall<X> remoteCall = getRemoteCall(remoteCallName);
		remoteCall.addHandler(handler);
	}
	
	@Override
	public <X> void bindAndCall(String remoteCallName, RemoteCallEventHandler<X> handler, Object config){
		bind(remoteCallName, handler);
		invoke(remoteCallName, config);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <X> HasRemoteCall<X> getRemoteCallAdapter(String remoteCallName){
		return (HasRemoteCall<X>)getRemoteCallAdapterMap().get(remoteCallName);
	}
	
	@Override
	public void load(String remoteCallName, Object config){
		invoke(remoteCallName, config);
	}
	
	@Override
	public void invoke(String name, Object config) {
		invoke(name, config, getRetryCount());
	}
	@Override
	public void invoke(String name, Object config, int retryCount) {
		RemoteCall<?> remoteCall = getRemoteCall(name);
		remoteCall.retry(config, retryCount);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> RemoteCall<X> getRemoteCall(String name) {
		RemoteCall<X> retval = null;
		if(name == null){
			return retval;
		}
		retval = getRemoteCallMap().get(name);
		return retval;
	}

	/**
	 * @return the remoteCallPairMap
	 */
	public Map<String, RemoteCall> getRemoteCallMap() {
		if(remoteCallMap == null){
			remoteCallMap = createRemoteCallMap();
		}
		return remoteCallMap;
	}

	/**
	 * @param remoteCallPairMap the remoteCallPairMap to set
	 */
	public void setRemoteCallMap(
			Map<String, RemoteCall> remoteCallMap) {
		this.remoteCallMap = remoteCallMap;
	}

	/**
	 * @return the retryCount
	 */
	@Override
	public int getRetryCount() {
		return retryCount;
	}

	/**
	 * @param retryCount the retryCount to set
	 */
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Map<String, HasRemoteCall> getRemoteCallAdapterMap() {
		if(remoteCallAdapterMap == null){
			remoteCallAdapterMap = createRemoteCallAdapterMap();
		}
		return remoteCallAdapterMap;
	}

	public void setRemoteCallAdapterMap(
			Map<String, HasRemoteCall> remoteCallAdapterMap) {
		this.remoteCallAdapterMap = remoteCallAdapterMap;
	}

}
