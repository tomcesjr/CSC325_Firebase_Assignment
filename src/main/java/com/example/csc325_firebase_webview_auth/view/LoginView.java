package com.example.csc325_firebase_webview_auth.view;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;

public class LoginView {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleRegister(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter email and password.");
            return;
        }

        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            App.fauth.createUser(request);
            messageLabel.setText("Registration successful.");
        } catch (FirebaseAuthException e) {
            messageLabel.setText("Registration failed: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            messageLabel.setText("Please enter your email.");
            return;
        }

        try {
            UserRecord user = App.fauth.getUserByEmail(email);

            if (user != null) {
                messageLabel.setText("Login successful.");
                App.setRoot("/files/AccessFBView.fxml");
            }
        } catch (FirebaseAuthException e) {
            messageLabel.setText("Login failed: user not found.");
        } catch (IOException e) {
            messageLabel.setText("Could not open main screen.");
            e.printStackTrace();
        }
    }
}