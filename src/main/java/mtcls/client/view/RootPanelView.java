package mtcls.client.view;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

public class RootPanelView implements RootView{
	private static RootPanel _rootPanel;
	private static RootPanelView instance;
	protected RootPanel rootPanel;
	protected String parentElemId;
	
	public RootPanelView(){
		this(null, null);
	}
	
	public RootPanelView(String parentElemId){
		this(null, parentElemId);
	}
	
	public RootPanelView(RootPanel rootPanel, String parentElemId){
		setRootPanel(rootPanel);
		setParentElemId(parentElemId);
	}
	
	public static RootPanelView get(){
		return get(null);
	}
	
	public static RootPanelView get(String id){
		if(instance==null){
			instance = new RootPanelView(RootPanelView.getRootPanel(id), id); 
		}
		return instance;
	}
	
	private static RootPanel getRootPanel(String parentElemId){
		if(_rootPanel==null){
			if(parentElemId == null || "".equals(parentElemId)){
				_rootPanel = RootPanel.get();
			}else{
				_rootPanel = RootPanel.get(parentElemId);
			}
		}
		return _rootPanel;
	}

	public String getParentElemId() {
		return parentElemId;
	}

	public void setParentElemId(String parentElemId) {
		this.parentElemId = parentElemId;
	}

	public RootPanel getRootPanel() {
		if(rootPanel == null){
			rootPanel = RootPanelView.getRootPanel(getParentElemId());
		}
		return rootPanel;
	}

	public void setRootPanel(RootPanel rootPanel) {
		this.rootPanel = rootPanel;
	}	
	
	public AbsolutePanel getPanel(){
		return getRootPanel();
	}
}
