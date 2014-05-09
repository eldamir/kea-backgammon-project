package test;

import static org.junit.Assert.assertEquals;

import com.sun.rowset.CachedRowSetImpl;
import controller.dbController.DBConnector;
import model.internationalization.LanguageResource;

import org.junit.Test;

import java.sql.SQLException;

public class DBConnectorTest
{

    @Test
    public void testConnection()
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

        Integer key = null;
        Integer age = null;
        String name = null;
        String address = null;
        Double salary = null;
        try
        {
            while (result.next())
            {
                key = result.getInt(1);
                age = result.getInt(2);
                name = result.getString(3);
                address = result.getString(4);
                salary = result.getDouble(5);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        assertEquals("Compare key", (long) 1, (long) key);
        assertEquals("Compare age", (long) 25, (long) age);
        assertEquals("Compare name", "Ruben", name);
        assertEquals("Compare address", null, address);
        assertEquals("Compare salary", 0.0, (double) salary, 0.0);
    }
}
