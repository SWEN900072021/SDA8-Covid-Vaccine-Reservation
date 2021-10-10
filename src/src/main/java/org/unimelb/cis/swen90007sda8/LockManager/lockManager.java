package org.unimelb.cis.swen90007sda8.LockManager;

import org.unimelb.cis.swen90007sda8.DBConnector.postgresqlConnector;
public class lockManager {
    public static void lock(String tableName){
        postgresqlConnector conn = new postgresqlConnector();
        String stmt = "Begin work;LOCK TABLE '"+ tableName+"' IN ACCESS EXCLUSIVE MODE;";
        conn.connect(stmt);
    }
    public static void unlock(){
        postgresqlConnector conn = new postgresqlConnector();
        String stmt = "Commit work;";
        conn.connect(stmt);
    }
}
