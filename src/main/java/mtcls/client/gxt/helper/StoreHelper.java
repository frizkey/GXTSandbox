package mtcls.client.gxt.helper;

import mtcls.client.data.EventBusProxy;
import mtcls.client.event.remote.HasRemoteCall;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.DataProxy;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.Loader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;

public abstract class StoreHelper {
	
	public static <D> ListStore<ModelData> createStore(String serviceName, 
											Boolean isGroupingEnabled, 
											ListLoader<ListLoadResult<D>> loader){
		ListStore<ModelData> retval = null;
		if(isGroupingEnabled != null && isGroupingEnabled.booleanValue()==true){
			retval = new GroupingStore<ModelData>(loader);
		}
		return retval;
	}
	
	
	public static <D> DataProxy<ListLoadResult<D>> createEventBusProxy(String serviceName, 
															HasRemoteCall<ListLoadResult<D>> remoteCall){
		DataProxy<ListLoadResult<D>> retval = 
			new EventBusProxy<ListLoadResult<D>>(
					remoteCall);
		return retval;
	}
	
	public static <D> PagingLoader<PagingLoadResult<D>> createPagingLoader(String serviceName, 
																DataProxy<PagingLoadResult<D>> proxy, 
																DataReader<PagingLoadResult<D>> reader){
		PagingLoader<PagingLoadResult<D>> retval = null;
		if(reader != null){
			retval = new BasePagingLoader<PagingLoadResult<D>>(proxy, reader);
			return retval;
		}
		retval = new BasePagingLoader<PagingLoadResult<D>>(proxy);
		return retval;
	}
	
	public static <D> ListLoader<ListLoadResult<D>> createListLoader(String serviceName, 
													DataProxy<ListLoadResult<D>> proxy, 
													DataReader<ListLoadResult<D>> reader){
		ListLoader<ListLoadResult<D>> retval = null;
		if(reader != null){
			retval = new BaseListLoader<ListLoadResult<D>>(proxy, reader);
			return retval;
		}
		retval = new BaseListLoader<ListLoadResult<D>>(proxy);
		return retval;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Loader createLoader(String serviceName, 
				Boolean isPagingEnabled, 
				DataProxy proxy, 
				DataReader reader){
		Loader retval = null;
		if(isPagingEnabled != null && isPagingEnabled.booleanValue()){
			retval = (Loader)createPagingLoader(serviceName, proxy, reader);
			return retval;
		}
		retval = createListLoader(serviceName, proxy, reader);
		return retval;
	}
}
