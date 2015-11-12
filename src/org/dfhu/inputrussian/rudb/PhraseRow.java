package org.dfhu.inputrussian.rudb;

public class PhraseRow implements IRow {
    private String targetWord;
    private String targetPhrase;
    private String translation;

    @Override
    public String toString() {
        return getTargetWord() + " " + getTargetPhrase() + " " + getTranslation();
    }

    public String getTargetWord() {
        return targetWord;
    }
    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }
    public String getTargetPhrase() {
        return targetPhrase;
    }
    public void setTargetPhrase(String targetPhrase) {
        this.targetPhrase = targetPhrase;
    }
    public String getTranslation() {
        return translation;
    }
    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
