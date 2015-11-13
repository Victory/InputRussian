package org.dfhu.inputrussian.rudb;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

abstract class AbstractRow implements IRow {

    @Override
    public String getString (String columnName) {
        throw new NotImplementedException();
    }

    @Override
    public String setString(String columnName, String value) {
        throw new NotImplementedException();
    }
}
