package dev.clerdmy.itemhub.ui.controllers;

import dev.clerdmy.itemhub.control.SessionManager;
import dev.clerdmy.itemhub.response.UserStatus;
import dev.clerdmy.itemhub.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label hint;

    @FXML
    private void onSignUp() {
        UserStatus status = SessionManager.register(usernameField.getText(), emailField.getText(), passwordField.getText(), confirmPasswordField.getText());
        hint.setText(status.toString());
    }

    @FXML
    private void onHyperLink() throws IOException {
        SceneManager.switchTo("/view/signin.fxml");
    }

}