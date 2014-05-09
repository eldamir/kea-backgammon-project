package controller.dbController;

/**
 * Class that contains methods for the predefined sql queries 
 * 
 * @author Ruben Nielsen & Ulrik Christensen
 *
 */
public abstract class QueryDictionary {
	
	
	/**
	 * Creates a .db file with initial table structure
	 * 
	 */
	public static void CreateDatabase()
	{
		boolean debug = true;
		String sql = "";
		
		if (debug)
		{
			sql += "DROP TABLE IF EXISTS Game;\n ";
			sql += "DROP TABLE IF EXISTS Move;\n ";
		}
		
		sql += " CREATE TABLE IF NOT EXISTS Game " +
		"(id           INTEGER PRIMARY KEY            AUTOINCREMENT NOT NULL, " +
		"winner 		VARCHAR(20)	);\n";
		
		sql += "CREATE TABLE IF NOT EXISTS Move" +
		"(id            INTEGER PRIMARY KEY           AUTOINCREMENT NOT NULL, " +
		"game_id       INT                            NOT NULL, " +
		"start          INT                           NOT NULL, " +
		"end            INT                           NOT NULL, " +
		"dice          INT                            NOT NULL, " +
		"piece_color   VARCHAR(20)                    NOT NULL, " +
		"FOREIGN KEY(game_id) REFERENCES Game(id));\n";		

		DBConnector.query(sql, true);		
	}

}
