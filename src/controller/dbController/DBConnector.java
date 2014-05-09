package controller.dbController;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

/**
 * Created by ruben on 5/8/14.
 */
public abstract class DBConnector
{
    public static final String DATABASE_NAME = "test.db";

    public static CachedRowSetImpl query(String query, boolean update)
    {
        return queryHelper(query, update);
    }

    public static CachedRowSetImpl query(String query)
    {
        return queryHelper(query, false);
    }

    private static void parseResultSet(ResultSet results)
    {

    }

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

    public static void main(String[] args)
    {
        String sql = "DROP TABLE COMPANY";
        DBConnector.query(sql, true);

        sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                "(ID             INTEGER PRIMARY KEY     AUTOINCREMENT NOT NULL," +
                " AGE            INT                     NOT NULL, " +
                " NAME           TEXT                    NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
        DBConnector.query(sql, true);

        sql = "INSERT INTO COMPANY(NAME, AGE) VALUES ('Ruben', 25)";
        DBConnector.query(sql, true);

        sql = "SELECT * FROM COMPANY";
        CachedRowSetImpl result = DBConnector.query(sql);

        try
        {
            while (result.next())
            {
                System.out.println(result.getInt(1));
                System.out.println(result.getInt(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println(result.getDouble(5));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }
}
