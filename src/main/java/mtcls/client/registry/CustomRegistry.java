package mtcls.client.registry;

import java.util.Map;

public interface CustomRegistry<D> {
	public Map<String, D> getRegistryMap();
	
	public D getRegistered(String itemName);
}
