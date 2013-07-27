package usa.thumbtack.amjadelkashef;

import java.util.Map;

public abstract class AbstractDatabase {

	protected Map<String, Integer> variables;
	protected Map<Integer, Integer> valuesOccurrences;

	/**
	 * Adds a variable to the variables map. If variable already exists, updates
	 * its value. Synchronizes the occurences of the variable's value in
	 * valuesOccurrences map.
	 * 
	 * @param variableName
	 * @param variableValue
	 */
	public void set(String variableName, Integer variableValue)
	{
		variables.put(variableName, variableValue);
		Integer nbOccurrencesForValue = valuesOccurrences.get(variableValue);
		if (nbOccurrencesForValue == null)
		{
			valuesOccurrences.put(variableValue, 1);
		} else
		{
			nbOccurrencesForValue++;
			valuesOccurrences.put(variableValue, nbOccurrencesForValue);
		}
	}

	/**
	 * Retrieves the value of a variable by its name.
	 * 
	 * @param variableName
	 * @return the variable's value if it exists in the database, null otherwise
	 */
	public Integer get(String variableName)
	{
		if (!variables.containsKey(variableName))
		{
			return null;
		}
		return variables.get(variableName);
	}

	/**
	 * Removes a variable from the variables map and decrement its value's
	 * occurrences. If variable doesn't exist, doesn't do anything.
	 * 
	 * @param variableName
	 */
	public void unset(String variableName)
	{
		if (variables.containsKey(variableName))
		{
			Integer variableValue = variables.get(variableName);

			variables.remove(variableName);

			Integer nbOccurrencesForValue = valuesOccurrences.get(variableValue);
			if (nbOccurrencesForValue != null)
			{
				nbOccurrencesForValue--;
				valuesOccurrences.put(variableValue, nbOccurrencesForValue);
			}
		}
	}

	/**
	 * Returns the number of occurences of a value.
	 * 
	 * @param value
	 * @return number of occurrences of the value parameter, or 0 if value
	 *         doesn't occur
	 */
	public int numEqualTo(int value)
	{
		if (!valuesOccurrences.containsKey(value))
		{
			return 0;
		}
		return valuesOccurrences.get(value);
	}
}
