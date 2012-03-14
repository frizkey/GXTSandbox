package mtcls.client.gxt.helper;

import java.util.ArrayList;
import java.util.List;

import mtcls.common.model.AppModule;
import mtcls.common.model.AppProperty;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;

public class GridHelper {
	public static List<ColumnConfig> createColumnConfig(AppModule module){
		List<ColumnConfig> retval = new ArrayList<ColumnConfig>();
		List<AppProperty> properties = module.getProperties();
		for (AppProperty appProperty : properties) {
			ColumnConfig config = null;
			if(appProperty.isListable()){
				String columnId = appProperty.getName();
				String columnLabel = appProperty.getLabel();
				Integer widthObj = appProperty.getWidth();
				boolean hidden = appProperty.isHidden();
				int width = (widthObj!=null) ? widthObj.intValue() : -1;
				config = new ColumnConfig(columnId, columnLabel, width);
				config.setHidden(hidden);
				retval.add(config);
			}
		}		
		return retval;
	}
}
