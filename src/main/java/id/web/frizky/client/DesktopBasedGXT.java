package id.web.frizky.client;

import com.extjs.gxt.samples.resources.client.TestData;
import com.extjs.gxt.samples.resources.client.model.Stock;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

import java.util.ArrayList;
import java.util.List;

public class DesktopBasedGXT {
    public DesktopBasedGXT() {
    }

    Window createAccordionWindow() {
        final Window w = new Window();
        w.setMinimizable(true);
        w.setMaximizable(true);
        w.setIcon(IconHelper.createStyle("accordion"));
        w.setHeading("Accordion Window");
        w.setWidth(200);
        w.setHeight(350);

        ToolBar toolBar = new ToolBar();
        Button item = new Button();
        item.setIcon(IconHelper.createStyle("icon-connect"));
        toolBar.add(item);

        toolBar.add(new SeparatorToolItem());
        w.setTopComponent(toolBar);

        item = new Button();
        item.setIcon(IconHelper.createStyle("icon-user-add"));
        toolBar.add(item);

        item = new Button();
        item.setIcon(IconHelper.createStyle("icon-user-delete"));
        toolBar.add(item);

        w.setLayout(new AccordionLayout());

        ContentPanel cp = new ContentPanel();
        cp.setAnimCollapse(false);
        cp.setHeading("Online Users");
        cp.setScrollMode(Style.Scroll.AUTO);
        cp.getHeader().addTool(new ToolButton("x-tool-refresh"));

        w.add(cp);

        TreeStore<ModelData> store = new TreeStore<ModelData>();
        TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
        tree.setIconProvider(new ModelIconProvider<ModelData>() {

            public AbstractImagePrototype getIcon(ModelData model) {
                if (model.get("icon") != null) {
                    return IconHelper.createStyle((String) model.get("icon"));
                } else {
                    return null;
                }
            }

        });
        tree.setDisplayProperty("name");

        ModelData m = newItem("Family", null);
        store.add(m, false);
        tree.setExpanded(m, true);

        store.add(m, newItem("Darrell", "user"), false);
        store.add(m, newItem("Maro", "user-girl"), false);
        store.add(m, newItem("Lia", "user-kid"), false);
        store.add(m, newItem("Alec", "user-kid"), false);
        store.add(m, newItem("Andrew", "user-kid"), false);

        m = newItem("Friends", null);
        store.add(m, false);
        tree.setExpanded(m, true);
        store.add(m, newItem("Bob", "user"), false);
        store.add(m, newItem("Mary", "user-girl"), false);
        store.add(m, newItem("Sally", "user-girl"), false);
        store.add(m, newItem("Jack", "user"), false);

        cp.add(tree);

        cp = new ContentPanel();
        cp.setAnimCollapse(false);
        cp.setHeading("Settings");
        cp.setBodyStyleName("pad-text");
        cp.addText(TestData.DUMMY_TEXT_SHORT);
        w.add(cp);

        cp = new ContentPanel();
        cp.setAnimCollapse(false);
        cp.setHeading("Stuff");
        cp.setBodyStyleName("pad-text");
        cp.addText(TestData.DUMMY_TEXT_SHORT);
        w.add(cp);

        cp = new ContentPanel();
        cp.setAnimCollapse(false);
        cp.setHeading("More Stuff");
        cp.setBodyStyleName("pad-text");
        cp.addText(TestData.DUMMY_TEXT_SHORT);
        w.add(cp);
        return w;
    }

    Window createGridWindow() {
        Window w = new Window();
        w.setIcon(IconHelper.createStyle("icon-grid"));
        w.setMinimizable(true);
        w.setMaximizable(true);
        w.setHeading("Grid Window");
        w.setSize(500, 400);
        w.setLayout(new FitLayout());

        GroupingStore<Stock> store = new GroupingStore<Stock>();
        store.add(TestData.getCompanies());
        store.groupBy("industry");

        ColumnConfig company = new ColumnConfig("name", "Company", 60);
        ColumnConfig price = new ColumnConfig("open", "Price", 20);
        price.setNumberFormat(NumberFormat.getCurrencyFormat());
        ColumnConfig change = new ColumnConfig("change", "Change", 20);
        ColumnConfig industry = new ColumnConfig("industry", "Industry", 20);
        ColumnConfig last = new ColumnConfig("date", "Last Updated", 20);
        last.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/y"));

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(company);
        config.add(price);
        config.add(change);
        config.add(industry);
        config.add(last);

        final ColumnModel cm = new ColumnModel(config);

        GroupingView view = new GroupingView();
        view.setForceFit(true);
        view.setGroupRenderer(new GridGroupRenderer() {
            public String render(GroupColumnData data) {
                String f = cm.getColumnById(data.field).getHeader();
                String l = data.models.size() == 1 ? "Item" : "Items";
                return f + ": " + data.group + " (" + data.models.size() + " " + l + ")";
            }
        });

        Grid<Stock> grid = new Grid<Stock>(store, cm);
        grid.setView(view);
        grid.setBorders(true);

        w.add(grid);
        return w;
    }

    Window createTabWindow() {
        Window w = new Window();
        w.setMinimizable(true);
        w.setMaximizable(true);
        w.setSize(740, 480);
        w.setIcon(IconHelper.createStyle("tabs"));
        w.setHeading("Tab Window");

        w.setLayout(new FitLayout());

        TabPanel panel = new TabPanel();

        for (int i = 0; i < 4; i++) {
            TabItem item = new TabItem("Tab Item " + (i + 1));
            item.addText("Something useful would be here");
            panel.add(item);
        }

        w.add(panel);
        return w;
    }

    Window createBogusWindow(int index) {
        Window w = new Window();
        w.setIcon(IconHelper.createStyle("bogus"));
        w.setMinimizable(true);
        w.setMaximizable(true);
        w.setHeading("Bogus Window " + ++index);
        w.setSize(400, 300);
        return w;
    }

    ModelData newItem(String text, String iconStyle) {
        ModelData m = new BaseModelData();
        m.set("name", text);
        m.set("icon", iconStyle);
        return m;
    }
}