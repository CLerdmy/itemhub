package dev.clerdmy.itemhub.control;

import dev.clerdmy.itemhub.model.User;

public interface SessionListener {

    void sessionChanged(User newUser);

}