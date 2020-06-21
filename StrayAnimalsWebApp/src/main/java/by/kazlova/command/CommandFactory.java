package by.kazlova.command;

import javax.servlet.http.HttpServletRequest;
//todo singleton??

public class CommandFactory {
	private static final String PARAMETER_COMMAND = "command";

	public static Command defineCommand(HttpServletRequest request)  {//throws CommandException
		String jspCommand = request.getParameter(PARAMETER_COMMAND);
		Command currentCommand = null;

		try { // try- catch ? IllegalArgumentException e - ????
			CommandType currentCommandType = CommandType.valueOf(jspCommand.toUpperCase()); // LOGIN находи
																							// соответствующий пришедшей
																							// команде объект в enum

			// get the object of this command
			currentCommand = currentCommandType.getCommand(); // ?? new LoginCommand запускаем объект enum
																// соответствующий пришедшей команде
		} catch (IllegalArgumentException e) {
			//throw new CommandException(e);
		}
		return currentCommand;

	}
}
