package by.kazlova.command;


import by.kazlova.command.impl.LoginCommand;
import by.kazlova.command.impl.RegistrationCommand;

public enum CommandType { // todo name CommandType?
	REGISTRATION(new RegistrationCommand()), //todo role!
	LOGIN(new LoginCommand());
//	LOGOUT (new LogoutCommand()),
//	NEW_POST(new NewPostCommand());
	
	//GetAllPosts(new LogicPost )

	private Command command;

	private CommandType(Command command) { // создать константы перечисления с помощью конструктора мы можем только внутри перечисления.
		this.command = command;
	}

	public Command getCommand() { //getCurrentCommand()
		return command;
	}
	
}
