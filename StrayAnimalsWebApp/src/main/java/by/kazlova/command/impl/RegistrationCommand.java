package by.kazlova.command.impl;

import by.kazlova.command.Command;
import by.kazlova.command.Router;
import by.kazlova.controller.SessionRequestContent;

public class RegistrationCommand implements Command {
    public static final String JSP_Login = "path.page.login";
    public static final String JSP_REGISTRATION = "path.page.registration";
    public static final String PARAMETR_USERNAME = "username";
    public static final String PARAMETR_PASSWORDE = "password";
    public static final String PARAMETR_EMAIL = "email";
    public static final String PARAMETR_ROLE = "roleType";
    public static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    public static final String SIGNUP_ERROR = "message.signup.error";
    public static final String SIGNUP_SERVER_ERROR = "message.signup.error.server";
    public static final String ATTRIBUTE_MESSAGE = "infoMessage"; // todo
    private static final String SIGNUP_SUCCESS = "message.signup.success";
    private static final String SIGNUP_ERROR_JOKE = "message.signup.error.joke";
    private AccountService accountService;
    private LanguageService languageService;

    public RegistrationCommand() {
        this.accountService = new AccountServiceImpl();
        this.languageService = new LanguageServiceImpl();

    }

    @Override
    public Router execute(SessionRequestContent content) { // throws CommandException
        String uri = ConfigurationManager.getProperty(JSP_Login);
        LocaleType localeType = languageService.defineLocale(content);
        System.err.println("ok");
        if (isEnterDataExist(content)) {
            System.err.println("ok2");
            String login = content.getParameter(PARAMETR_USERNAME);
            String password = content.getParameter(PARAMETR_PASSWORDE);
            String email = content.getParameter(PARAMETR_EMAIL);
            Role role = Role.valueOf(content.getParameter(PARAMETR_ROLE).toUpperCase());
            System.out.println("3");
            try {
                if (accountService.checkUserByLoginEmail(login, email)) { //todo
                    System.err.println("ok3");
                    accountService.signupUser(login, password, email, role); //проверять эту строчку и перенаправлять опять на стр регистрации
                    System.err.println("ok4");
                    content.setAttribute(ATTRIBUTE_MESSAGE, MessageManager.getProperty(SIGNUP_SUCCESS,localeType));

                } else {
                    content.setAttribute(ATTRIBUTE_ERROR_MESSAGE,
                            manager.MessageManager.getProperty(SIGNUP_ERROR, localeType)); // уже существует логие или
                    // пассворд
                    uri = ConfigurationManager.getProperty(JSP_REGISTRATION);
                }
            } catch (ServiceException e) {
                content.setAttribute(ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.getProperty(SIGNUP_SERVER_ERROR, localeType));
            }
        } else {
            content.setAttribute(ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.getProperty(SIGNUP_ERROR_JOKE, localeType));
            uri = ConfigurationManager.getProperty(JSP_REGISTRATION);

        }
        return new Router(uri);
    }

    private boolean isEnterDataExist(SessionRequestContent content) {
        return !content.getParameter(PARAMETR_USERNAME).isEmpty() && !content.getParameter(PARAMETR_PASSWORDE).isEmpty()
                && !content.getParameter(PARAMETR_PASSWORDE).isEmpty()
                && !content.getParameter(PARAMETR_ROLE).isEmpty();
    }
}
//if (registrationLogic.registerUser(userBean)) {
//	request.setAttribute("user", userBean.getFirstName());
//	page = "/jsp/Main.jsp"; // "/jsp/LoginUser.jsp";
//} else {
//	request.setAttribute("errorRegistrationMessage", "Such user already exists!!!");
//	page = "/jsp/RegistrationUser.jsp";
//}
}
