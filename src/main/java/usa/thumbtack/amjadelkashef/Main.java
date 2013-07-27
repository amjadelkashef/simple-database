package usa.thumbtack.amjadelkashef;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		CommandLineInterpreter commandLineInterpreter = new CommandLineInterpreter();

		String input = null;
		Scanner reader = new Scanner(System.in);

		do
		{
			try
			{
				input = reader.nextLine();
				if (commandLineInterpreter.matchSetCommandLine(input))
				{
					executeSetCommand(input);
				} else if (commandLineInterpreter.matchGetCommandLine(input))
				{
					executeGetCommand(input);
				} else if (commandLineInterpreter.matchUnsetCommandLine(input))
				{
					executeUnsetCommand(input);
				} else if (commandLineInterpreter.matchNumEqualToCommandLine(input))
				{
					executeNumEqualToCommand(input);
				} else if (commandLineInterpreter.matchBeginCommandLine(input))
				{
					executeBeginCommand(input);
				} else if (commandLineInterpreter.matchRollbackCommandLine(input))
				{
					executeRollbackCommand(input);
				} else if (commandLineInterpreter.matchCommitCommandLine(input))
				{
					executeCommitCommand(input);
				} else if (input.equalsIgnoreCase("end"))
				{
					System.out.println("Good bye!");
				} else
				{
					showUnrecognizedCommandLineMessage();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} while (!input.equalsIgnoreCase("end"));

		reader.close();
	}

	private static void executeSetCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		String command[] = input.split("\\s");
		String variableName = command[1];
		int variableValue = Integer.parseInt(command[2]);
		transactionManager.set(variableName, variableValue);
	}

	private static void executeGetCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		String command[] = input.split("\\s");
		String variableName = command[1];
		System.out.println(transactionManager.get(variableName));
	}

	private static void executeUnsetCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		String command[] = input.split("\\s");
		String variableName = command[1];
		transactionManager.unset(variableName);
	}

	private static void executeNumEqualToCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		String command[] = input.split("\\s");
		int value = Integer.parseInt(command[1]);
		System.out.println(transactionManager.numEqualTo(value));
	}

	private static void executeBeginCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		transactionManager.begin();
	}

	private static void executeRollbackCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		transactionManager.rollback();
	}

	private static void executeCommitCommand(String input)
	{
		ContextManager transactionManager = ContextManager.getInstance();
		transactionManager.commit();
	}

	private static void showUnrecognizedCommandLineMessage()
	{
		System.out.println("\nThis command line is not recognized. Please respect one of the following interfaces:");
		System.out.println("SET [name] [value]");
		System.out.println("GET [name]");
		System.out.println("UNSET [name]");
		System.out.println("NUMEQUALTO [value]");
		System.out.println("BEGIN");
		System.out.println("ROLLBACK");
		System.out.println("COMMIT");
		System.out.println("END\n");
	}
}
