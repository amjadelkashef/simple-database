package usa.thumbtack.amjadelkashef;

import java.util.HashMap;
import java.util.Map;

public class Transaction extends AbstractDatabase {

	private Map<String, Integer> toBeUnsettedVariables;

	public Transaction()
	{
		this.variables = new HashMap<String, Integer>();
		this.valuesOccurrences = new HashMap<Integer, Integer>();
		this.toBeUnsettedVariables = new HashMap<String, Integer>();
	}

	public void addVariableToToBeUnsetVariables(String variableName, Integer variableValue)
	{
		toBeUnsettedVariables.put(variableName, variableValue);
	}

	public Map<String, Integer> getVariables()
	{
		return this.variables;
	}

	public Map<String, Integer> getToBeUnsetVariables()
	{
		return this.toBeUnsettedVariables;
	}
}
