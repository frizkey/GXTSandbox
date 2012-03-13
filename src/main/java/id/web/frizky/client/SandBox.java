package id.web.frizky.client;

import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.desktop.client.TaskBar;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SandBox implements EntryPoint {
    private Desktop desktop = new Desktop();
    private final DesktopBasedGXT desktopBasedGXT = new DesktopBasedGXT();

    private void itemSelected(ComponentEvent ce) {
        Window w;
        if (ce instanceof MenuEvent) {
            MenuEvent me = (MenuEvent) ce;
            w = me.getItem().getData("window");
        } else {
            w = ce.getComponent().getData("window");
        }
        if (!desktop.getWindows().contains(w)) {
            desktop.addWindow(w);
        }
        if (w != null && !w.isVisible()) {
            w.show();
        } else {
            w.toFront();
        }
    }

    public void onModuleLoad() {
        SelectionListener<MenuEvent> menuListener = new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent me) {
                itemSelected(me);
            }
        };

        SelectionListener<ComponentEvent> shortcutListener = new SelectionListener<ComponentEvent>() {
            @Override
            public void componentSelected(ComponentEvent ce) {
                itemSelected(ce);
            }
        };

        Window gridWindow = desktopBasedGXT.createGridWindow();
        Window accordionWindow = desktopBasedGXT.createAccordionWindow();

        Shortcut s1 = new Shortcut();
        s1.setText("Grid Window");
        s1.setId("grid-win-shortcut");
        s1.setData("window", gridWindow);
        s1.addSelectionListener(shortcutListener);
        desktop.addShortcut(s1);

        Shortcut s2 = new Shortcut();
        s2.setText("Accordion Window");
        s2.setId("acc-win-shortcut");
        s2.setData("window", accordionWindow);
        s2.addSelectionListener(shortcutListener);
        desktop.addShortcut(s2);

        TaskBar taskBar = desktop.getTaskBar();

        StartMenu menu = taskBar.getStartMenu();
        menu.setHeading("Darrell Meyer");
        menu.setIconStyle("user");

        MenuItem menuItem = new MenuItem("Grid Window");
        menuItem.setData("window", gridWindow);
        menuItem.setIcon(IconHelper.createStyle("icon-grid"));
        menuItem.addSelectionListener(menuListener);
        menu.add(menuItem);

        menuItem = new MenuItem("Tab Window");
        menuItem.setIcon(IconHelper.createStyle("tabs"));
        menuItem.addSelectionListener(menuListener);
        menuItem.setData("window", desktopBasedGXT.createTabWindow());
        menu.add(menuItem);

        menuItem = new MenuItem("Accordion Window");
        menuItem.setIcon(IconHelper.createStyle("accordion"));
        menuItem.addSelectionListener(menuListener);
        menuItem.setData("window", accordionWindow);
        menu.add(menuItem);

        menuItem = new MenuItem("Bogus Submenu");
        menuItem.setIcon(IconHelper.createStyle("bogus"));

        Menu sub = new Menu();

        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem("Bogus Window " + (i + 1));
            item.setData("window", desktopBasedGXT.createBogusWindow(i));
            item.addSelectionListener(menuListener);
            sub.add(item);
        }

        menuItem.setSubMenu(sub);
        menu.add(menuItem);

        // tools
        MenuItem tool = new MenuItem("Settings");
        tool.setIcon(IconHelper.createStyle("settings"));
        tool.addSelectionListener(new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Info.display("Event", "The 'Settings' tool was clicked");
            }
        });
        menu.addTool(tool);

        menu.addToolSeperator();

        tool = new MenuItem("Logout");
        tool.setIcon(IconHelper.createStyle("logout"));
        tool.addSelectionListener(new SelectionListener<MenuEvent>() {
            @Override
            public void componentSelected(MenuEvent ce) {
                Info.display("Event", "The 'Logout' tool was clicked");
            }
        });
        menu.addTool(tool);
    }
}
