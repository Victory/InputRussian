package org.dfhu.inputrussian;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("inputrussian")
public class InputrussianUI extends UI {

    Navigator navigator;
    protected static final String MAIN_VIEW = "main";

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = InputrussianUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Input Russian");

        navigator = new Navigator(this, this);
        navigator.addView("input", new InputView(navigator));
        navigator.addView("export", new ExportView(navigator));
        navigator.addView("", new MainView(navigator));
    }

}