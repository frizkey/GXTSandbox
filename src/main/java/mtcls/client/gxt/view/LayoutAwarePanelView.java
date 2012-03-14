package mtcls.client.gxt.view;


import mtcls.client.view.View;

import com.extjs.gxt.ui.client.widget.layout.LayoutData;

public class LayoutAwarePanelView 
		extends ContentPanelView 
			implements LayoutAwareView{
	protected LayoutData layoutData;
	
	public LayoutAwarePanelView(View parentView) {
		super(parentView);
		initView();
	}

	protected void initView() {
		
	}
	
	/**
	 * manually invoked by subclasses in constructor 
	 * to initialize subviews
	 */
	protected void initSubviews(){
		
	}
	
	protected LayoutData createLayoutData(){
		return null;
	}
	
	public LayoutData getLayoutData(){
		if(layoutData == null){
			layoutData = createLayoutData();
		}
		return layoutData;
	}
	
	public void setLayoutData(LayoutData layoutData){
		this.layoutData = layoutData;
	}
}
