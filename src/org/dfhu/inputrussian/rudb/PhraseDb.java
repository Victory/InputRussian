package org.dfhu.inputrussian.rudb;

public class PhraseDb extends RuDb {

    public static final String CREATE_TABLE = "CREATE TABLE phrases ("
            + "target_word VARCHAR(255) NOT NULL,"
            + "target_phrase TEXT NOT NULL,"
            + "translation TEXT NOT NULL,"
            + "ru_case VARCHAR(20) NOT NULL,"
            + "singular_plural VARCHAR(20),"
            + "gender VARCHAR(20)"
            + ")";

    private static final PhraseDb INSTANCE = new PhraseDb();

    private PhraseDb() {}

    public static PhraseDb getInstance () {
        return INSTANCE;
    }
}
