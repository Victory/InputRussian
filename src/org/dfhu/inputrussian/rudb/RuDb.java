package org.dfhu.inputrussian.rudb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.ui.Notification;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class RuDb {

    private static SimpleJDBCConnectionPool pool = getDbPool();

    public static SimpleJDBCConnectionPool getDbPool() {
        String path = System.getProperty("user.home") + "/db/russian.sqlite";
        Logger.getLogger("testing").warning(path);
        try {
            return new SimpleJDBCConnectionPool(
                    "org.sqlite.JDBC",
                    "jdbc:sqlite:" + path, "", "");
        } catch (SQLException e) {
            Notification.show("sqlite error " + e.getMessage());
            return null;
        } catch (RuntimeException e) {
            Notification.show("runtime sqlite error " + e.getMessage());
            return null;
        }
    }

    /**
     * get the first value from the selected table using the given sql where clause
     *
     * @param table table name from RuDb.Tables
     * @param where where clause without the leading "WHERE"
     * @return first value
     */
     protected static ResultSet getFirstResultSet(String table, String where) {
        String sql = "SELECT * FROM " + table + " WHERE " + where + " LIMIT 1";
        try {
            Connection connection = getDbPool().reserveConnection();
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(sql);
            boolean result = results.next();
            if (!result) {
                return null;
            }
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Runtime SQL Exception: " + e.getMessage());
        }
    }

    protected String getTableName () {
        throw new NotImplementedException();
    }
    protected IRow populateRow (ResultSet results) throws SQLException {
        throw new NotImplementedException();
    }

    public IRow first(String where) throws SQLException {
        ResultSet results = getFirstResultSet(getTableName(), where);
        return this.populateRow(results);
    }

    public ArrayList<IRow> whereMany(String where) {
        ResultSet results = getWhereManyResultSet(getTableName(), where);
        ArrayList<IRow> rows = new ArrayList<>();

        try {
            while (results.next()) {
                rows.add(this.populateRow(results));
            }
            results.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Runtime SQL Exception: " + e.getMessage());
        }
        return rows;
    }

    private ResultSet getWhereManyResultSet(String tableName, String where) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + where;
        try {
            Connection connection = getDbPool().reserveConnection();
            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(sql);
            getDbPool().releaseConnection(connection);
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Runtime SQL Exception: " + e.getMessage());
        }
    }
}
