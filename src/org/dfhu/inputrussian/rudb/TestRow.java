package org.dfhu.inputrussian.rudb;

public class TestRow implements IRow {
    String kk;
    String vv;

    @Override
    public String toString () {
        return kk + " " + vv;
    }
}
