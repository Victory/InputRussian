package org.dfhu.inputrussian.rudb;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dfhu.inputrussian.rudb.PhraseRow.C;

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


    @Override
    public String getTableName () {
        return "phrases";
    }

    @Override
    public PhraseRow populateRow(ResultSet results) {
        PhraseRow row = new PhraseRow();
        try {
            row.setString(C.targetWord, results.getString(C.targetWord));
            row.setString(C.targetPhrase, results.getString(C.targetPhrase));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return row;
    }
}
