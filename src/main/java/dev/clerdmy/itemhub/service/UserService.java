package dev.clerdmy.itemhub.service;

import dev.clerdmy.itemhub.dao.UserDao;
import dev.clerdmy.itemhub.model.User;
import dev.clerdmy.itemhub.response.UserResult;
import dev.clerdmy.itemhub.response.UserStatus;
import dev.clerdmy.itemhub.util.ValidationUtils;

import java.util.Optional;

public class UserService {

    private final UserDao userDao = new UserDao();

    public UserResult registerUser(String username, String email, String password, String confirmPassword) {
        UserResult inputResult = ValidationUtils.checkRegistrationInput(username, email, password, confirmPassword);
        if (inputResult != null) return inputResult;

        if (userDao.readUserByEmail(email).isPresent()) return new UserResult(null, UserStatus.EMAIL_ALREADY_EXISTS);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setHashedPassword(ValidationUtils.hashPassword(password));

        boolean success = userDao.createUser(user);
        return success ? new UserResult(user, UserStatus.SUCCESS) : new UserResult(null, UserStatus.ERROR);
    }

    public UserResult loginUser(String email, String password) {
        UserResult inputResult = ValidationUtils.checkLoginInput(email, password);
        if (inputResult != null) return inputResult;

        Optional<User> user = userDao.readUserByEmail(email);
        if (user.isEmpty()) return new UserResult(null, UserStatus.INCORRECT_EMAIL);
        if (!ValidationUtils.checkPassword(password, user.get().getHashedPassword())) return new UserResult(null, UserStatus.INCORRECT_PASSWORD);

        return new UserResult(user.get(), UserStatus.SUCCESS);
    }

}