package usa.thumbtack.amjadelkashef;

import static org.junit.Assert.assertEquals;

import java.util.Deque;

import org.junit.Before;
import org.junit.Test;

public class ContextManagerTest {

	private ContextManager transactionManager = ContextManager.getInstance();

	@Before
	public void setUp()
	{
		transactionManager.clear();
	}

	@Test
	public void testBegin()
	{
		transactionManager.begin();
		Deque<Transaction> transactions = transactionManager.getTransactions();
		assertEquals(1, transactions.size());
	}

	@Test
	public void testRollback()
	{
		transactionManager.begin();
		transactionManager.begin();
		assertEquals(2, transactionManager.getTransactions().size());
		transactionManager.rollback();
		assertEquals(1, transactionManager.getTransactions().size());
	}

	@Test
	public void testCommit()
	{
		transactionManager.begin();
		transactionManager.begin();
		assertEquals(2, transactionManager.getTransactions().size());
		transactionManager.commit();
		assertEquals(0, transactionManager.getTransactions().size());
	}

	@Test
	public void testGetAndSet()
	{
		transactionManager.set("a", 10);
		assertEquals(10, transactionManager.get("a").intValue());

		transactionManager.begin();
		transactionManager.set("b", 11);
		assertEquals(11, transactionManager.get("b").intValue());

		transactionManager.rollback();
		assertEquals(null, transactionManager.get("b"));

		transactionManager.begin();
		transactionManager.set("c", 12);
		transactionManager.begin();
		transactionManager.set("c", 13);
		assertEquals(13, transactionManager.get("c").intValue());
	}

	@Test
	public void testUnset()
	{
		transactionManager.set("a", 10);
		transactionManager.unset("a");
		assertEquals(null, transactionManager.get("a"));

		transactionManager.set("b", 11);
		transactionManager.begin();
		transactionManager.unset("b");
		assertEquals(null, transactionManager.get("b"));

		transactionManager.rollback();
		assertEquals(11, transactionManager.get("b").intValue());
	}

	@Test
	public void testNumEqualTo()
	{
		transactionManager.set("a", 10);
		assertEquals(1, transactionManager.numEqualTo(10));

		transactionManager.unset("a");
		assertEquals(0, transactionManager.numEqualTo(10));

		transactionManager.begin();
		transactionManager.set("a", 10);
		transactionManager.begin();
		transactionManager.set("b", 10);
		assertEquals(2, transactionManager.numEqualTo(10));

		transactionManager.rollback();
		assertEquals(1, transactionManager.numEqualTo(10));
	}

	@Test
	public void testNumEqualTo2()
	{
		transactionManager.set("a", 10);
		assertEquals(1, transactionManager.numEqualTo(10));

		transactionManager.begin();
		transactionManager.unset("a");
		assertEquals(0, transactionManager.numEqualTo(10));

		transactionManager.rollback();
		assertEquals(1, transactionManager.numEqualTo(10));
	}
}
