package Utils;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * this method sets up the ability to query the DB
 */
public class DBQuery {

    /**
     * tells the compiler what to perform-instructions
     */
    private static Statement statement;

    /**
     * creates a statement object
     * @param conn sets statement to Connection
     * @throws SQLException when accessing SQL an exception can occur this catches that exception
     */
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    /**
     * gets statement
     * @return returns statement
     */
    public static Statement getStatement(){
        return statement;
    }
}


