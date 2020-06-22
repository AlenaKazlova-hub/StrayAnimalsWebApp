package by.kazlova.service.impl;

import by.kazlova.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UserDao;
import dao.impl.DaoException;
import dao.impl.UserDaoImpl;
import entity.Role;
import entity.impl.User;
import manager.ConfigurationManager;
import provider.UserProvider;
import service.AccountService;
import service.ServiceException;
import validator.InputDataValidator;

//receiver
public class AccountServiceImpl implements AccountService {
	public static final Logger logger = LogManager.getLogger();

	private UserDao userDao;
private InputDataValidator inputDataValidator;
	public AccountServiceImpl() {
		this.userDao = new UserDaoImpl();
		this.inputDataValidator = new InputDataValidator();
	}

	@Override
	public boolean checkUserByLoginPassword(String login, String password) throws ServiceException {
		try {
			return userDao.isLoginPasswordMatch(login, password);
		} catch (DaoException e) {
			logger.error("Cannot check login/password", e); // todo
			throw new ServiceException(e);
		}
	}

	@Override
	public User defineUserByLogin(String login) throws ServiceException {
		try {
			return userDao.findUserByLogin(login);
		} catch (DaoException e) {
			logger.error("Cannot define user", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean checkUserByLoginEmail(String login, String eMail) throws ServiceException {
		if (isInputDataValid(login, eMail)) {
			try {
				return !userDao.isExist(login, eMail);
			} catch (DaoException e) {
				logger.error("Cannot check login/email", e);
				throw new ServiceException(e);
			}
		} else {
			return false;
		}
	}
	

	@Override
	public void signupUser(String login, String password, String eMail, Role role) throws ServiceException {
		try {
			if (!userDao.isExist(login, eMail)) {
				User user = UserProvider.getInstance().create(login, password, eMail, role );
				userDao.create(user);
			}
		} catch (DaoException e) {
			logger.error("Cannot signup new user", e);
			throw new ServiceException(e);
		}
	}
	 private boolean isInputDataValid(String login, String eMail) {
	        return inputDataValidator.isLoginValid(login) && inputDataValidator.isEMailValid(eMail);
	    }
}
//	public boolean checkLoginData(Login loginBean) throws ServiceException { // todo and name of method
//
//		Login loginBeanDao;
//		try {
//			loginBeanDao = LoginDao.takeUserData(loginBean);
//		} catch (DaoException e) {
//			throw new ServiceException(e);
//		}
//		System.out.println(loginBeanDao);
//		boolean userIsExict = false;
//		if (LoginValidator.existUser(loginBean)) { // validation
//			if (loginBean.getUserName().equals(loginBeanDao.getUserName())
//					&& loginBean.getPassword().equals(loginBeanDao.getPassword())) {
//				// и проверяем роль??
//				userIsExict = true;
//			}
//		}
//
//		return userIsExict;
//	}

