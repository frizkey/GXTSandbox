package mtcls.client.view;


public interface HasParentView extends View{
	public View getParentView();
	public void setParentView(View parentView);
}
