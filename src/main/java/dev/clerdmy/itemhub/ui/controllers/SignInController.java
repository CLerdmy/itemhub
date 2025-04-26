package dev.clerdmy.itemhub.ui.controllers;

import dev.clerdmy.itemhub.control.SessionManager;
import dev.clerdmy.itemhub.response.UserStatus;
import dev.clerdmy.itemhub.ui.SceneManager;
import dev.clerdmy.itemhub.ui.UIConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label hint;

    @FXML
    private void onSignIn() throws IOException {
        UserStatus status = SessionManager.login(loginField.getText(), passwordField.getText());
        hint.setText(status.toString());
        if (status == UserStatus.SUCCESS) SceneManager.showLoginOrMain();
    }

    @FXML
    private void onHyperLink() throws IOException {
        SceneManager.switchTo(UIConstants.SIGN_UP_FXML_PATH);
    }

}