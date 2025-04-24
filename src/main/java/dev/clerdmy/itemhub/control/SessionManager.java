package dev.clerdmy.itemhub.control;

import dev.clerdmy.itemhub.model.User;
import dev.clerdmy.itemhub.response.UserResult;
import dev.clerdmy.itemhub.response.UserStatus;
import dev.clerdmy.itemhub.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static User currentUser;
    private static final List<SessionListener> listeners = new ArrayList<>();
    private static final UserService userService = new UserService();

    private static void notifyListeners() {
        for (SessionListener listener : listeners) {
            listener.sessionChanged(currentUser);
        }
    }

    public static void addSessionListener(SessionListener listener) {
        listeners.add(listener);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
        notifyListeners();
    }

    public static UserStatus register(String username, String email, String password, String confirmPassword) {
        UserResult result = userService.registerUser(username, email, password, confirmPassword);
        if (result.isSuccess()) {
            currentUser = result.user();
            notifyListeners();
        }
        return result.status();
    }

    public static UserStatus login(String email, String password) {
        UserResult result = userService.loginUser(email, password);
        if (result.isSuccess()) {
            currentUser = result.user();
            notifyListeners();
        }
        return result.status();
    }

}