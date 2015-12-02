package org.dfhu.inputrussian;

import java.util.ArrayList;

import org.dfhu.inputrussian.rudb.IRow;
import org.dfhu.inputrussian.rudb.PhraseDb;
import org.dfhu.inputrussian.rudb.PhraseRow.C;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {

    private Navigator navigator;
    Table table;
    Button nav;
    Button exportNav;

    public MainView(Navigator navigator) {
        this.navigator = navigator;
        this.setMargin(true);
        nav = new Button("Input");
        nav.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo("input");
            }
        });
        addComponent(nav);

        exportNav = new Button("Export");
        exportNav.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo("export");
            }
        });
        addComponent(exportNav);

        table = new Table("Current Phrases");
        table.addContainerProperty("Word", String.class, "");
        table.addContainerProperty("Phrase", String.class, "");
        table.addContainerProperty("Case", String.class, "");
        table.addContainerProperty("Translation", String.class, "");
        table.addContainerProperty("Singular", String.class, "");
        table.addContainerProperty("Gender", String.class, "");

        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {

        table.removeAllItems();
        ArrayList<IRow> rows = PhraseDb.getInstance().whereMany("1 = 1");
        for (IRow row: rows) {
            Object itemId = table.addItem();
            ItemHelper item = new ItemHelper(table.getItem(itemId));

            item.add("Word", row.getString(C.targetWord));
            item.add("Phrase",row.getString(C.targetPhrase));
            item.add("Case", row.getString(C.ruCase));
            item.add("Translation", row.getString(C.translation));
            item.add("Singular", row.getString(C.singular));
            item.add("Gender", row.getString(C.gender));
        }
        table.setPageLength(table.size());
        Notification.show("Main View");
    }
}
