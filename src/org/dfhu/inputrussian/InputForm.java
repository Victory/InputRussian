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
    private TextField translation = new TextField("Translation");
    private OptionGroup singularPlural = new OptionGroup("SingularPlural");
    private OptionGroup gender = new OptionGroup("Gender");

    private Button submitButton = new Button("Give me a click");
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

        translation.setInputPrompt("Translation");
        translation.setCaption("Translation");
        this.addComponent(translation);

        ruCase.addItems("Nominative", "Genitive", "Dative", "Accusative", "Instrumental", "Prepositional");
        this.addComponent(ruCase);

        singularPlural.setCaption("Singular/Plural");
        singularPlural.addItems("Singular", "Plural");
        this.addComponent(singularPlural);

        gender.addItems("Feminine", "Masculine", "Neuter");
        this.addComponent(gender);

        submitButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show(InputForm.this.toString());
            }
        });

        this.addComponent(submitButton);
    }

    @Override
    public String toString () {
        String msg = "";
        msg += targetWord.getValue();
        msg += " " + targetPhrase.getValue();
        return msg;
    }
}
