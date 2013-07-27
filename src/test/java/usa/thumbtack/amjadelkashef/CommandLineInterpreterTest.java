package usa.thumbtack.amjadelkashef;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommandLineInterpreterTest {

	private CommandLineInterpreter commandLineInterpreter = new CommandLineInterpreter();

	@Test
	public void testMatchSetCommandLine()
	{
		String command1 = "set a 10";
		String command2 = "set A 10";
		String command3 = "set aaa 10";
		String command4 = "set A 0010";
		String command5 = "set A1 10";
		String command6 = "set A1A 10";
		String command7 = "set A 1";
		String command8 = "SET A 10";
		assertTrue(commandLineInterpreter.matchSetCommandLine(command1));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command2));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command3));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command4));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command5));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command6));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command7));
		assertTrue(commandLineInterpreter.matchSetCommandLine(command8));

		String command9 = "get a 10";
		String command10 = "set 1a 10";
		String command11 = "set a b";
		String command12 = "set a 11a11";
		String command13 = "set a 10 11";
		String command14 = "sssset a 10";
		assertFalse(commandLineInterpreter.matchSetCommandLine(command9));
		assertFalse(commandLineInterpreter.matchSetCommandLine(command10));
		assertFalse(commandLineInterpreter.matchSetCommandLine(command11));
		assertFalse(commandLineInterpreter.matchSetCommandLine(command12));
		assertFalse(commandLineInterpreter.matchSetCommandLine(command13));
		assertFalse(commandLineInterpreter.matchSetCommandLine(command14));
	}

	@Test
	public void testMatchGetCommandLine()
	{
		String command1 = "get a";
		String command2 = "get A";
		String command3 = "get aaa";
		String command4 = "get AAA";
		String command5 = "get a1";
		String command6 = "get 1a";
		String command7 = "get 1";
		String command8 = "get 111";
		assertTrue(commandLineInterpreter.matchGetCommandLine(command1));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command2));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command3));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command4));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command5));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command6));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command7));
		assertTrue(commandLineInterpreter.matchGetCommandLine(command8));

		String command9 = "get a 1";
		String command10 = "Xget a";
		assertFalse(commandLineInterpreter.matchGetCommandLine(command9));
		assertFalse(commandLineInterpreter.matchGetCommandLine(command10));

	}

	@Test
	public void testMatchUnsetCommandLine()
	{
		String command1 = "unset a";
		String command2 = "unset A";
		String command3 = "unset aaa";
		String command4 = "unset AAA";
		String command5 = "unset 1a";
		String command6 = "unset a1";
		String command7 = "unset 1";
		String command8 = "unset 111";
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command1));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command2));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command3));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command4));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command5));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command6));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command7));
		assertTrue(commandLineInterpreter.matchUnsetCommandLine(command8));

		String command9 = "unset a 1";
		String command10 = "Xunset a";
		assertFalse(commandLineInterpreter.matchGetCommandLine(command9));
		assertFalse(commandLineInterpreter.matchGetCommandLine(command10));
	}

	@Test
	public void testMatchNumEqualToCommandLine()
	{
		String command1 = "numequalto 10";
		String command2 = "NUMEQUALTO 10";
		assertTrue(commandLineInterpreter.matchNumEqualToCommandLine(command1));
		assertTrue(commandLineInterpreter.matchNumEqualToCommandLine(command2));

		String command3 = "numequaltooo 10";
		String command4 = "nnnnumequalto 10";
		String command5 = "numequalto a";
		String command6 = "numequalto aaaa";
		String command7 = "numequalto 1aa1";
		String command8 = "numequalto 10 10";
		assertFalse(commandLineInterpreter.matchNumEqualToCommandLine(command3));
		assertFalse(commandLineInterpreter.matchNumEqualToCommandLine(command4));
		assertFalse(commandLineInterpreter.matchNumEqualToCommandLine(command5));
		assertFalse(commandLineInterpreter.matchNumEqualToCommandLine(command6));
		assertFalse(commandLineInterpreter.matchNumEqualToCommandLine(command7));
		assertFalse(commandLineInterpreter.matchNumEqualToCommandLine(command8));
	}

	@Test
	public void testMatchBeginCommandLine()
	{
		String command1 = "begin";
		String command2 = "BEGIN";
		String command3 = "bEgIn";
		assertTrue(commandLineInterpreter.matchBeginCommandLine(command1));
		assertTrue(commandLineInterpreter.matchBeginCommandLine(command2));
		assertTrue(commandLineInterpreter.matchBeginCommandLine(command3));

		String command4 = "gfdjfBEGIN";
		String command5 = "BEGINlqhlhglhgf";
		assertFalse(commandLineInterpreter.matchBeginCommandLine(command4));
		assertFalse(commandLineInterpreter.matchBeginCommandLine(command5));
	}

	@Test
	public void testMatchRollbackCommandLine()
	{
		String command1 = "rollback";
		String command2 = "ROLLBACK";
		String command3 = "roLlBaCk";
		assertTrue(commandLineInterpreter.matchRollbackCommandLine(command1));
		assertTrue(commandLineInterpreter.matchRollbackCommandLine(command2));
		assertTrue(commandLineInterpreter.matchRollbackCommandLine(command3));

		String command4 = "gfdjfROLLBACK";
		String command5 = "ROLLBACKlqhlhglhgf";
		assertFalse(commandLineInterpreter.matchRollbackCommandLine(command4));
		assertFalse(commandLineInterpreter.matchRollbackCommandLine(command5));
	}

	@Test
	public void testMatchCommitCommandLine()
	{
		String command1 = "commit";
		String command2 = "COMMIT";
		String command3 = "cOmMiT";
		assertTrue(commandLineInterpreter.matchCommitCommandLine(command1));
		assertTrue(commandLineInterpreter.matchCommitCommandLine(command2));
		assertTrue(commandLineInterpreter.matchCommitCommandLine(command3));

		String command4 = "gfdjfCOMMIT";
		String command5 = "COMMITlqhlhglhgf";
		assertFalse(commandLineInterpreter.matchCommitCommandLine(command4));
		assertFalse(commandLineInterpreter.matchCommitCommandLine(command5));
	}
}
