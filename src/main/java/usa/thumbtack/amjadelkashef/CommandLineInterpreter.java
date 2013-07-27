package usa.thumbtack.amjadelkashef;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineInterpreter {

	private static final String GET_COMMAND_LINE_PATTERN = "^get\\s\\w+\\z";
	private static final String SET_COMMAND_LINE_PATTERN = "^set\\s[a-zA-Z]+\\w*\\s\\d+\\z";
	private static final String UNSET_COMMAND_LINE_PATTERN = "^unset\\s\\w+\\z";
	private static final String NUMEQUALTO_COMMAND_LINE_PATTERN = "^numequalto\\s\\d*\\z";
	private static final String BEGIN_COMMAND_LINE_PATTERN = "^begin\\z";
	private static final String ROLLBACK_COMMAND_LINE_PATTERN = "^rollback\\z";
	private static final String COMMIT_COMMAND_LINE_PATTERN = "^commit\\z";

	public boolean matchSetCommandLine(String input)
	{
		return matchCommandLine(SET_COMMAND_LINE_PATTERN, input);
	}

	public boolean matchGetCommandLine(String input)
	{
		return matchCommandLine(GET_COMMAND_LINE_PATTERN, input);
	}

	public boolean matchUnsetCommandLine(String input)
	{
		return matchCommandLine(UNSET_COMMAND_LINE_PATTERN, input);
	}

	public boolean matchNumEqualToCommandLine(String input)
	{
		return matchCommandLine(NUMEQUALTO_COMMAND_LINE_PATTERN, input);
	}

	public boolean matchBeginCommandLine(String input)
	{
		return matchCommandLine(BEGIN_COMMAND_LINE_PATTERN, input);
	}

	public boolean matchRollbackCommandLine(String input)
	{
		return matchCommandLine(ROLLBACK_COMMAND_LINE_PATTERN, input);
	}

	public boolean matchCommitCommandLine(String input)
	{
		return matchCommandLine(COMMIT_COMMAND_LINE_PATTERN, input);
	}

	private boolean matchCommandLine(String commandLinePattern, String input)
	{
		Pattern pattern = Pattern.compile(commandLinePattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}
}
