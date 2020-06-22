package by.kazlova.command.impl;

import by.kazlova.command.Command;
import by.kazlova.command.LocaleType;
import by.kazlova.command.Router;
import by.kazlova.controller.SessionRequestContent;
import by.kazlova.entity.impl.User;
import by.kazlova.service.AccountService;

public class LoginCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    //public/private
    public static final String PARAMETR_USERNAME = "username";
    public static final String PARAMETR_PASSWORD = "password";
    public static final String PARAMETER_ROLE = "role";
    private static final String JSP_MAIN = "path.page.main";
    private static final String JSP_Login = "path.page.login";
    private static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    private static final String ERROR_SERVER_MESSAGE = "message.login.error.server";
    private static final String ATTRIBUTE_USER_ROLE = "userRole";
    private static final String ATTRIBUTE_USER_LOGIN = "userLogin";
    private static final String ATTRIBUTE_USER_PASSWORD = "userPassword"; // ??
    private static final String ATTRIBUTE_USER_ID = "userId";
    private static final String ATTRIBUTE_USER_EMAIL = "userEmail";
    private static final String SIGNUP_ERROR_JOKE = "message.signup.error.joke";
    private static final String ERROR_MESSAGE = "message.login.error";

    private AccountService accountService;
    private LanguageService languageService;
    //private PostService postService;

    public LoginCommand() {
        this.accountService = new AccountServiceImpl();
        this.languageService = new LanguageServiceImpl();
        //this.postService = new PostServiceImpl();
    }

    @Override
    public Router execute(SessionRequestContent content) {
        String uri = ConfigurationManager.getProperty(JSP_MAIN); //todo
        LocaleType localeType = languageService.defineLocale(content); // locale  для вывода сообщения только!
        if (isEnterDataExist(content) ) {
            System.out.println("ok2");
            String login = content.getParameter(PARAMETR_USERNAME);
            String password = content.getParameter(PARAMETR_PASSWORD);
            try {
                if (accountService.checkUserByLoginPassword(login, password)) {
                    System.out.println("ok3");
                    User user = accountService.defineUserByLogin(login);
                    System.out.println("ok4");
                    fillSession(content, user);
                    //fillRequest(content, user, localeType);
                    Role role = (Role) content.getSessionAttribute(ATTRIBUTE_USER_ROLE);
                    uri = ConfigurationManager.getProperty(role.getPage()); //todo //переход в профиль(по логике) в зависимости от роли, у каждой роли своя jsp
                    logger.info(String.format("User: %s has logged in", user));
                } else {
                    content.setAttribute(ATTRIBUTE_ERROR_MESSAGE, MessageManager.getProperty(ERROR_MESSAGE, localeType));
                    uri = ConfigurationManager.getProperty(JSP_Login);//uri = "/jsp/RegistrationUser.jsp"; //todo - ошибку выдает
                }
            } catch (ServiceException e) {
                content.setAttribute(ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.getProperty(ERROR_SERVER_MESSAGE, localeType));
            }
        } else {
            content.setAttribute(ATTRIBUTE_ERROR_MESSAGE, MessageManager.getProperty(SIGNUP_ERROR_JOKE, localeType));// todo //Пожалуйста, не ломайте систему!
            uri = ConfigurationManager.getProperty(JSP_Login);
        }
        return new Router(uri);
    }

//		String uri = null; // String page = null;
//		Login loginBean = new Login();
//
//		loginBean.setUserName(content.getParameter("username")); // request.getParameter("username")
//		loginBean.setPassword(content.getParameter("password"));

    // System.out.println(loginLogic.checkLoginData(loginBean));
    // установка значений из беан в сессию
//
//		try {
//			if (loginLogic.checkLoginData(loginBean)) {
//				content.setAttribute("user", loginBean.getUserName()); // userBean.getFirstName()
//				uri = "/jsp/Main.jsp"; // constant
//			} else {
//				content.setAttribute("errorLoginPassMessage", "Such user not found!!!");
//				uri = "/jsp/RegistrationUser.jsp";
//			}
//		} catch (ServiceException e) {
//			// throw new CommandException(e);
//		}
//		return new Router(uri);
//	}
//study fillRequest
    private void fillRequest(SessionRequestContent content, User user, LocaleType localeType) {
//        Role role = user.getRole();
//        if (role == Role.PARTICIPANT) {
//            fillBeerFoodList(content, localeType);
//        }
        content.setAttribute(ATTRIBUTE_USER_LOGIN, user.getLogin());
        // content.setAttribute(ATTRIBUTE_USER_AVATAR_URL, user.getAvatarUrl());
        content.setAttribute(ATTRIBUTE_USER_EMAIL, user.getEmail());
        content.setAttribute(ATTRIBUTE_USER_ROLE, user.getRole());
        // content.setAttribute(ATTRIBUTE_USER_STATUS, user.getStatus());
    }

    private void fillSession(SessionRequestContent content, User user) {
        content.setSessionAttribute(ATTRIBUTE_USER_ID, user.getId());
        content.setSessionAttribute(ATTRIBUTE_USER_LOGIN, user.getLogin());
        content.setSessionAttribute(ATTRIBUTE_USER_PASSWORD, user.getPassword()); // todo needed?
        content.setSessionAttribute(ATTRIBUTE_USER_EMAIL, user.getEmail());
        content.setSessionAttribute(ATTRIBUTE_USER_ROLE, user.getRole());// почему вынесено отдельно в переменную??
    }

    private boolean isEnterDataExist(SessionRequestContent content) {
        return !content.getParameter(PARAMETR_USERNAME).isEmpty()
                && !content.getParameter(PARAMETR_PASSWORD).isEmpty();
    }

//
}
