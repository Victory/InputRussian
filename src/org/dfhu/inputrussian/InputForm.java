package org.dfhu.inputrussian;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;

public class InputForm extends FormLayout {

    private TextField targetWord = new TextField("targetWord");
    private TextField targetPhrase = new TextField("targetPhrase");
    private OptionGroup ruCase = new OptionGroup("Case");

    public InputForm() {
        super();

        targetWord.setCaption("Target Word");
        targetWord.setInputPrompt("A russian word");
        targetWord.setRequired(true);
        this.addComponent(targetWord);

        targetPhrase.setCaption("Target Phrase");
        targetPhrase.setInputPrompt("Russian phrase");
        targetPhrase.setRequired(true);
        this.addComponent(targetPhrase);

        ruCase.addItems("nominative", "genitive", "dative", "accusative", "instrumental", "prepositional");
        this.addComponent(ruCase);

        Button button = new Button("Give me a click");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show(targetWord.getValue());
            }
        });

        this.addComponent(button);

    }
}
