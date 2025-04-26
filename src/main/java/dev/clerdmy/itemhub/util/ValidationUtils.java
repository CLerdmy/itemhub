package dev.clerdmy.itemhub.util;

import dev.clerdmy.itemhub.model.User;
import dev.clerdmy.itemhub.response.UserResult;
import dev.clerdmy.itemhub.response.UserStatus;
import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+(?:\\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isInvalidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return true;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return !matcher.matches();
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static UserResult checkLoginInput(String email, String password) {
        if (isEmpty(email)) return new UserResult(null, UserStatus.EMPTY_EMAIL);
        if (isEmpty(password)) return new UserResult(null, UserStatus.EMPTY_PASSWORD);
        return null;
    }

    public static UserResult checkRegistrationInput(String username, String email, String password, String confirmPassword) {
        if (isEmpty(username)) return new UserResult(null, UserStatus.EMPTY_NAME);
        if (isEmpty(email)) return new UserResult(null, UserStatus.EMPTY_EMAIL);
        if (isEmpty(password)|| isEmpty(confirmPassword)) return new UserResult(null, UserStatus.EMPTY_PASSWORD);
        if (ValidationUtils.isInvalidEmail(email)) return new UserResult(null, UserStatus.INVALID_EMAIL);
        if (!password.equals(confirmPassword)) return new UserResult(null, UserStatus.MISMATCHING_PASSWORDS);
        return null;
    }

    private static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

}