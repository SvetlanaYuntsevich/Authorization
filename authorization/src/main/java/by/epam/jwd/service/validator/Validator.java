package by.epam.jwd.service.validator;

import java.util.List;

import by.epam.jwd.entity.User;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class Validator {

	private static final String NAME_AND_SURNAME_PATTERN = "[A-Za-zА-Яа-яёЁ]{1,15}";
	private static final String LOGIN_AND_PASSWORD_PATTERN = "[A-Za-z\\d]{1,10}";
	private static final String EMAIL_PATTERN = "([A-Za-z\\d]+)@([A-Za-z]+)\\.[A-Za-z]{1,5}";
	private static final String DATE_PATTERN = "[2][\\d]{3}\\-[0-1][0-9]\\-[0-3][0-9]";

	private static final Validator instance = new Validator();

	public Validator() {
	}

	public static Validator getInstance() {
		return instance;
	}

	public boolean validateNameOrSurname(String string) {
		return string.matches(NAME_AND_SURNAME_PATTERN);
	}

	public boolean validateLoginOrPassword(String string) {
		return string.matches(LOGIN_AND_PASSWORD_PATTERN);
	}

	public boolean validateEmail(String string) {
		return string.matches(EMAIL_PATTERN);
	}

	public boolean validateDate(String string) {
		return string.matches(DATE_PATTERN);
	}

	public boolean validateUniqueLogin(String login) throws ServiceException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		List<User> users = userService.findAll();
		int count = 0;
		int usersCount = users.size();
		for (int i = 0; i < usersCount; i++) {
			User user = users.get(i);
			if (user.getLogin().equals(login)) {
				count++;
			}
		}
		return count == 0;
	}
}
