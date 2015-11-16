package org.dfhu.inputrussian;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
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

        Notification.show("Main View");
    }

}
