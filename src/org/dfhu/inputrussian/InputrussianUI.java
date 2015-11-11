package org.dfhu.inputrussian;

import javax.servlet.annotation.WebServlet;

import org.dfhu.inputrussian.rudb.TestDb;
import org.dfhu.inputrussian.rudb.TestRow;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("inputrussian")
public class InputrussianUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = InputrussianUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		FormLayout form = new FormLayout();
		
		TextField targetWord = new TextField("targetWord");
		
		targetWord.setCaption("Target word");
		targetWord.setInputPrompt("A russian word");
		form.addComponent(targetWord);
		Button button = new Button("Give me a click");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
				TestRow testRow = (TestRow) TestDb.getInstance().first("1 = 1");
				Notification.show(targetWord.getValue());
			}
		});
		form.addComponent(button);
		
		layout.addComponent(form);
	}

}