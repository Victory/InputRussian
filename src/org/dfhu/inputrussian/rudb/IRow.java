package org.dfhu.inputrussian.rudb;

public interface IRow {
    String getString(String columnName);
    String setString(String columnName, String value);
}
