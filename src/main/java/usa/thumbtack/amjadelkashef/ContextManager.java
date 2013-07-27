package usa.thumbtack.amjadelkashef;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class applies a singleton pattern.
 * 
 * @author amjadelkashef
 * 
 */

public class ContextManager {

	private static ContextManager transactionManager = new ContextManager();
	private Deque<Transaction> transactions;
	private Database database;

	private ContextManager()
	{
		this.transactions = new ArrayDeque<Transaction>();
		this.database = Database.getInstance();
	}

	public static ContextManager getInstance()
	{
		return transactionManager;
	}

	public void begin()
	{
		transactions.push(new Transaction());
	}

	public void rollback()
	{
		if (transactions.size() > 0)
		{
			transactions.pop();
		} else
		{
			System.out.println("INVALID ROLLBACK");
		}
	}

	/**
	 * Iterates on transactions by order of creation to : 1. unset from database
	 * the variables marked as toBeUnsetted in transactions; 2.add or update
	 * values of variables created or updated in transactions.
	 */
	public void commit()
	{
		Iterator<Transaction> iterator = transactions.descendingIterator();
		while (iterator.hasNext())
		{
			Transaction currentTransaction = iterator.next();

			// Unsetting the variables in the ToBeUnsettedVariables set
			for (String variableName : currentTransaction.getToBeUnsetVariables().keySet())
			{
				database.unset(variableName);
			}

			// Updating or adding variables within current transaction to the
			// database
			Map<String, Integer> transactionVariables = currentTransaction.getVariables();
			Set<String> transactionVariablesNames = transactionVariables.keySet();
			for (String transactionVariableName : transactionVariablesNames)
			{
				Integer transactionVariableValue = transactionVariables.get(transactionVariableName);
				database.set(transactionVariableName, transactionVariableValue);
			}
		}
		transactions.clear();
	}

	/**
	 * If no transaction is pending, sets variable directly in database; sets it
	 * in the last transaction otherwise.
	 * 
	 * @param variableName
	 * @param variableValue
	 */
	public void set(String variableName, Integer variableValue)
	{
		if (transactions.size() == 0)
		{
			database.set(variableName, variableValue);
		} else
		{
			Transaction transaction = transactions.peek();
			if (transaction.getToBeUnsetVariables().keySet().contains(variableName))
			{
				transaction.getToBeUnsetVariables().remove(variableName);
			}
			transaction.set(variableName, variableValue);
		}
	}

	/**
	 * Returns the value associated to variableName from database if no
	 * transaction is pending; from last transaction otherwise.
	 * 
	 * @param variableName
	 * @return variableValue from database or last transaction
	 */
	public Integer get(String variableName)
	{
		if (transactions.size() == 0)
		{
			return database.get(variableName);
		} else
		{
			Transaction transaction = transactions.peek();

			if (transaction.getToBeUnsetVariables().keySet().contains(variableName))
			{
				return null;
			}
			Integer variableValue = transaction.get(variableName);
			if (variableValue != null)
			{
				return variableValue;
			}
			return database.get(variableName);
		}
	}

	/**
	 * If no transaction is pending, unsets variable directly from database;
	 * mark variable to be unsetted otherwise. Variables are actually unsetted
	 * from database when transactions are committed.
	 * 
	 * @param variableName
	 */
	public void unset(String variableName)
	{
		if (transactions.size() == 0)
		{
			database.unset(variableName);
		} else
		{
			Transaction transaction = transactions.peek();
			Integer variableValue = get(variableName);
			if (variableValue != null)
			{
				transaction.addVariableToToBeUnsetVariables(variableName, variableValue);
			}
		}
	}

	/**
	 * If no transaction is pending, return number of occurrences of a value in
	 * database; sums number of occurrences in database and in every transaction
	 * otherwise.
	 * 
	 * @param value
	 * @return
	 */
	public int numEqualTo(int value)
	{
		int valueOccurrences = database.numEqualTo(value);
		if (transactions.size() > 0)
		{
			Iterator<Transaction> iterator = transactions.iterator();
			while (iterator.hasNext())
			{
				Transaction currentTransaction = iterator.next();
				valueOccurrences += currentTransaction.numEqualTo(value);

				// Substracting the occurrences of variables marked to be
				// unsetted
				Map<String, Integer> toBeUnsetVariables = currentTransaction.getToBeUnsetVariables();
				for (String variableName : toBeUnsetVariables.keySet())
				{
					Integer variableValue = toBeUnsetVariables.get(variableName);
					if (variableValue == value)
					{
						valueOccurrences--;
					}
				}
			}
		}
		return valueOccurrences;
	}

	/* For test purposes only */

	Deque<Transaction> getTransactions()
	{
		return this.transactions;
	}

	void clear()
	{
		transactions.clear();
		database.clear();
	}

}
