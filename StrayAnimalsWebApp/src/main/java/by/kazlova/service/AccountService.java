package by.kazlova.service;

import entity.Role;
import entity.impl.User;

public interface AccountService {
	boolean checkUserByLoginPassword(String login, String password) throws ServiceException;
	
	public User defineUserByLogin(String login) throws ServiceException;
	
	boolean checkUserByLoginEmail(String login, String eMail) throws ServiceException ;
	
	void signupUser(String login, String eMail, String password, Role role) throws ServiceException;
}
