package org.dfhu.inputrussian.rudb;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class PhraseRow extends AbstractRow {
    HashMap<String, String> stringValues = new HashMap<String, String>();

    public static class C {
        public static final String targetWord = "target_word";
        public static final String targetPhrase = "target_phrase";
        public static final String translation = "translation";
        public static final String ruCase = "ru_case";
        public static final String singular = "singular_plural";
        public static final String gender = "gender";

        public static boolean save (HashMap<String, String> stringValues) {
            ArrayList<Field> fields = new ArrayList<Field>(Arrays.asList(C.class.getFields()));
            fields.sort(new Comparator<Field>() {
                @Override
                public int compare(Field lhs, Field rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }

            });

            ArrayList<String> fieldsStrings = new ArrayList<>();
            for (Field field: fields) {
                try {
                    fieldsStrings.add((String) field.get(null));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            String fieldsString;
            fieldsString = " (" + String.join(", ", fieldsStrings) + ") ";
            fieldsString += " VALUES ";

            // the question marks TODO: make a string join/repeat utility
            String qms = String.format("%0" + fieldsStrings.size() + "d", 0).replace("0", "?,");
            qms = qms.substring(0, qms.length() - 1);

            fieldsString += " (" + qms + ") ";
            String sql = "INSERT INTO phrases " + fieldsString;

            Connection con;
            PreparedStatement stmt;
            try {
                // XXX: close on exception
                con = RuDb.getDbPool().reserveConnection();
                stmt = con.prepareStatement(sql);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            int ii = 1;
            for (Field field: fields) {
                try {
                    String kk = (String) field.get(null);
                    String value = stringValues.get(kk);
                    stmt.setString(ii++, value);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            try {
                if (stmt.executeUpdate() == 0) {
                    throw new SQLException("No rows were updated");
                }
                stmt.close();
                con.commit();
                RuDb.getDbPool().releaseConnection(con);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    public Boolean save () {
        return C.save(stringValues);
    }

    @Override
    public String toString() {
        return getString(C.targetWord) + " "
                + getString(C.targetPhrase) + " "
                + getString(C.translation);
    }

    @Override
    public String getString(String columnName) {
        return stringValues.get(columnName);
    }

    @Override
    public String setString(String columnName, String value) {
        String oldValue = stringValues.get(columnName);
        stringValues.put(columnName, value);
        return oldValue;
    }
}
