package org.dfhu.inputrussian;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

       InputForm form = new InputForm();
       this.addComponent(form);



       Notification.show("Input Russian");
    }

}
