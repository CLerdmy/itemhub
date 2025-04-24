package dev.clerdmy.itemhub.response;

import dev.clerdmy.itemhub.model.User;

public record UserResult(User user, UserStatus status) {

    public boolean isSuccess() {
        return user != null;
    }

}