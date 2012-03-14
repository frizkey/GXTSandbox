package mtcls.client.gxt.registry;

import java.util.HashMap;
import java.util.Map;

import mtcls.client.registry.CustomRegistry;

import com.extjs.gxt.ui.client.data.DataReader;

public class DataReaderRegistry implements CustomRegistry<DataReader<?>> {
	protected Map<String, DataReader<?>> registryMap;
	
	protected Map<String, DataReader<?>> createRegistryMap(){
		return new HashMap<String, DataReader<?>>();
	}
	
	public void setRegistryMap(Map<String, DataReader<?>> registryMap){
		this.registryMap = registryMap;
	}
	
	@Override
	public Map<String, DataReader<?>> getRegistryMap() {
		if(registryMap==null){
			registryMap = createRegistryMap();
		}
		return registryMap;
	}

	@Override
	public DataReader<?> getRegistered(String itemName) {
		return getRegistryMap().get(itemName);
	}
	
}
