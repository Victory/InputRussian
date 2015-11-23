package org.dfhu.inputrussian;

import org.dfhu.inputrussian.rudb.PhraseRow;
import org.dfhu.inputrussian.rudb.PhraseRow.C;

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

                if (!isValid()) {
                    Notification.show("Did not add phrase. All fields are required!");
                    return;
                }
                PhraseRow row = new PhraseRow();

                row.setString(C.targetPhrase, targetPhrase.getValue());
                row.setString(C.targetWord, targetWord.getValue());
                row.setString(C.translation, translation.getValue());
                row.setString(C.ruCase, (String) ruCase.getValue());
                row.setString(C.singular, (String) singularPlural.getValue());
                row.setString(C.gender, (String) gender.getValue());
                row.save();
                Notification.show(row.toString());
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

    private boolean isValid() {
        return !targetPhrase.getValue().isEmpty() &&
                !targetWord.getValue().isEmpty() &&
                !translation.getValue().isEmpty() &&
                !((String) ruCase.getValue()).isEmpty() &&
                !((String) singularPlural.getValue()).isEmpty() &&
                !((String) gender.getValue()).isEmpty();
    }
}
