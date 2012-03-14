package mtcls.client.view;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Widget;

public interface HasComponents<T extends Widget> extends Iterable<T> {
	public Iterator<T> iterator();
}
