package org.dfhu.inputrussian;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class ExportView extends VerticalLayout implements View {

    private Navigator navigator;
    Button homeNav;

    public ExportView(Navigator navigator) {
        this.navigator = navigator;
        homeNav = new Button("Home");
        homeNav.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo("");
            }
        });
        addComponent(homeNav);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

}
