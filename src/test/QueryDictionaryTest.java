package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.sun.rowset.CachedRowSetImpl;

import controller.dbController.DBConnector;
import controller.dbController.QueryDictionary;

public class QueryDictionaryTest 
{
	

	@Test
	public void test() 
	{
		
		QueryDictionary.CreateDatabase();
		
		String sql = "INSERT INTO Game(Winner) VALUES('BLACK');";
		DBConnector.query(sql, true);
		
		
        sql = "SELECT * FROM Game";
        CachedRowSetImpl result = DBConnector.query(sql);

        Integer key = null;
        String winner = null;
       
        try
        {
            while (result.next())
            {
                key = result.getInt(1);
                winner = result.getString(2);
              
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }      
        

        assertEquals("Compare key", (long) 1, (long) key);
        assertEquals("Compare winner", "BLACK", winner);		
	}

}
