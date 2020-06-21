package by.kazlova.command;

import by.kazlova.controller.SessionRequestContent;

//@FunctionalInterface
public interface Command  {
	Router execute(SessionRequestContent content);
}

//@FunctionalInterface
//public interface ActionCommand {
//    /**
//     * Sets a common interface for specific command classes.
//     *
//     * @param content object that contain request, response and session information.
//     * @return {@code Router} which contains path to resources and router type.
//     */
//    Router execute(SessionRequestContent content);
//}