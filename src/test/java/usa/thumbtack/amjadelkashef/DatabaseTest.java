package usa.thumbtack.amjadelkashef;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

	private Database database = Database.getInstance();

	@Before
	public void setUp()
	{
		database.clear();
	}

	@Test
	public void testSet()
	{
		database.set("b", 20);
		assertEquals(20, database.get("b").intValue());
	}

	@Test
	public void testGet()
	{
		database.set("a", 10);
		assertEquals(10, database.get("a").intValue());
	}

	@Test
	public void testUnset()
	{
		database.set("a", 10);
		database.unset("a");
		assertEquals(null, database.get("a"));
	}

	@Test
	public void testNumEqualTo()
	{
		assertEquals(0, database.numEqualTo(13));
		database.set("a", 13);
		database.set("b", 13);
		assertEquals(2, database.numEqualTo(13));
		database.unset("b");
		assertEquals(1, database.numEqualTo(13));
		assertEquals(0, database.numEqualTo(42));
	}
}
