package dev.clerdmy.itemhub.ui.controllers;

import dev.clerdmy.itemhub.control.SessionManager;
import dev.clerdmy.itemhub.response.UserStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label hint;

    @FXML
    private void onLogin() {
        UserStatus status = SessionManager.login(loginField.getText(), passwordField.getText());
        hint.setText(status.toString());
    }

}