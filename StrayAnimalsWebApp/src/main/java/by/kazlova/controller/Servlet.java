package by.kazlova.controller;

import by.kazlova.command.Command;
import by.kazlova.command.CommandFactory;
import by.kazlova.command.Router;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(request);
        SessionRequestContent content = new SessionRequestContent(request);
        Router page = command.execute(content);
        System.err.println(page.toString()); //
        String uri = page.getUri();
        content.insert(request); //кладем данные из content в  request, который идeт в jsp
        Router.TransitionType transitionType = page.getTransitionType();
        switch (transitionType) {
            case FORWARD:
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(uri);
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + uri);
                break;   
        }
    }
}
