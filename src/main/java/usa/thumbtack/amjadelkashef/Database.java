package usa.thumbtack.amjadelkashef;

import java.util.HashMap;

/**
 * This class applies a singleton pattern.
 * 
 * @author amjadelkashef
 * 
 */

public class Database extends AbstractDatabase {

	private static Database database = new Database();

	private Database()
	{
		variables = new HashMap<String, Integer>();
		valuesOccurrences = new HashMap<Integer, Integer>();
	}

	public static Database getInstance()
	{
		return database;
	}

	/* For test purposes only */

	void clear()
	{
		variables.clear();
		valuesOccurrences.clear();
	}
}
