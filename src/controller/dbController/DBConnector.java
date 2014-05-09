package controller.dbController;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

/**
 * Class that wraps the functionality of connecting to the database, 
 * executing queries and returning the data set
 * 
 * Created by ruben og Ulrik on 5/8/14.
 */
public abstract class DBConnector
{
    public static final String DATABASE_NAME = "test.db";

    /**
     * Executes the sql query and returns the result set 
     * 
     * @param query Sql string to be executed
     * @param update true if the query is an update query otherwise false
     * @return The result set from an select query otherwise null
     */
    public static CachedRowSetImpl query(String query, boolean update)
    {
        return queryHelper(query, update);
    }

    /**
     * Executes a select query and returns the result set 
     * 
     * @param query Sql string to be executed
     * @return The result set from an select query otherwise null
     */
    public static CachedRowSetImpl query(String query)
    {
        return queryHelper(query, false);
    }    

    /**
     * Helper method to query methods
     * 
     * @param query
     * @param update
     * @return
     */
    private static CachedRowSetImpl queryHelper(String query, boolean update)
    {
        Connection c = null;
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DBConnector.DATABASE_NAME);

            stmt = c.createStatement();

            if (update)
            {
                stmt.executeUpdate(query);
                stmt.close();
                c.close();
                return null;
            } else
            {
                ResultSet results = stmt.executeQuery(query);
                CachedRowSetImpl rowset = new CachedRowSetImpl();
                rowset.populate(results);
                stmt.close();
                c.close();
                return rowset;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
    
}
