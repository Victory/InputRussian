package org.dfhu.inputrussian;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class InputView extends VerticalLayout implements View {

    private Navigator navigator;

    public InputView(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void enter(ViewChangeEvent event) {
       this.setMargin(true);
       Button nav = new Button("Home");
       nav.addClickListener(new Button.ClickListener() {
           @Override
           public void buttonClick(ClickEvent event) {
               navigator.navigateTo("");
           }
       });
       addComponent(nav);

       InputForm form = new InputForm();
       addComponent(form);

       Notification.show("Input Russian");
    }

}
