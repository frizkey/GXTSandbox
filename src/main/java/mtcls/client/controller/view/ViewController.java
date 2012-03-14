package mtcls.client.controller.view;

import java.util.List;

import mtcls.client.view.View;

public interface ViewController{
	public String getName();
	public ViewController getParent();
	public void setParent(ViewController parent);
	public View getView();
	public boolean isLeaf();
	public List<ViewController> getChildControllers();
	public void setChildControllers(List<ViewController> childControllers);
}
