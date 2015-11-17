package org.dfhu.inputrussian;

import org.dfhu.inputrussian.rudb.PhraseDb;
import org.dfhu.inputrussian.rudb.PhraseRow;
import org.dfhu.inputrussian.rudb.PhraseRow.C;

import com.vaadin.data.Item;
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

    public MainView(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Button nav = new Button("Navigate");
        nav.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo("input");
            }
        });
        addComponent(nav);

        Table table = new Table("Current Phrases");

        table.addContainerProperty("Word", String.class, "");
        table.addContainerProperty("Phrase", String.class, "");

        PhraseRow row = (PhraseRow) PhraseDb.getInstance().first("1 = 1");
        Object itemId = table.addItem();
        Item item = table.getItem(itemId);
        item.getItemProperty("Word").setValue(row.getString(C.targetWord));
        item.getItemProperty("Phrase").setValue(row.getString(C.targetPhrase));

        table.setPageLength(table.size());
        addComponent(table);
        Notification.show("Main View");
    }

}
