package org.dfhu.inputrussian.rudb;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDb extends RuDb {


    private static final TestDb INSTANCE = new TestDb();

    private TestDb() {}

    public static TestDb getInstance () {
        return INSTANCE;
    }

    @Override
    protected String getTableName () {
        return "test";
    }

    @Override
    protected TestRow populateRow(ResultSet results) {
        TestRow row = new TestRow();

        try {
            row.kk = results.getString("kk");
            row.vv = results.getString("vv");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Runtime SQLException " + e.getMessage());
        }
        return row;


    }
}
