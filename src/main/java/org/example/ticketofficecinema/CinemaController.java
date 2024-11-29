package org.example.ticketofficecinema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CinemaController implements Initializable {

    @FXML
    private Button signIn_close;

    @FXML
    private Hyperlink signIn_createAccount;

    @FXML
    private AnchorPane signIn_form;

    @FXML
    private Button signIn_loginBtn;

    @FXML
    private Button signIn_minimize;

    @FXML
    private PasswordField signIn_password;

    @FXML
    private TextField signIn_username;

    @FXML
    private Hyperlink signUp_alreadyHaveAccount;

    @FXML
    private Button signUp_btn;

    @FXML
    private Button signUp_close;

    @FXML
    private TextField signUp_email;

    @FXML
    private AnchorPane signUp_form;

    @FXML
    private Button signUp_minimize;

    @FXML
    private PasswordField signUp_password;

    @FXML
    private TextField signUp_username;

    // Tools for database
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public boolean validEmail() {

        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(signUp_email.getText());

        Alert alert;

        if (match.find() && match.group().matches(signUp_email.getText())) {
            return true;
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода почты");
            alert.setHeaderText(null);
            alert.setContentText("Введите правильную почту!");
            alert.showAndWait();
        }
        return false;
    }

    public void signup() {

        String sqlInsert = "INSERT INTO admin (email, username, password) VALUES (?, ?, ?)";
        String sqlCheck = "SELECT * FROM admin WHERE username = ? OR email = ?";

        connect = DataBase.connectDb();

        try {
            if (connect != null) {

                Alert alert;

                if (signUp_email.getText().isEmpty() || signUp_username.getText().isEmpty()
                        || signUp_password.getText().isEmpty()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка ввода");
                    alert.setHeaderText(null);
                    alert.setContentText("Введите почту, логин и пароль!");
                    alert.showAndWait();

                } else if (signUp_password.getText().length() < 8) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Недостаточно данных");
                    alert.setHeaderText(null);
                    alert.setContentText("Придумайте сложный пароль!");
                    alert.showAndWait();

                } else {

                    if (validEmail()) {

                        // Проверка на существование пользователя с таким именем или почтой
                        prepare = connect.prepareStatement(sqlCheck);
                        prepare.setString(1, signUp_username.getText());
                        prepare.setString(2, signUp_email.getText());
                        result = prepare.executeQuery();

                        if (result.next()) {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Измените данные");
                            alert.setHeaderText(null);
                            alert.setContentText("Пользователь с именем или почтой уже существует!");
                            alert.showAndWait();

                        } else {
                            // Вставка нового пользователя
                            prepare = connect.prepareStatement(sqlInsert);
                            prepare.setString(1, signUp_email.getText());
                            prepare.setString(2, signUp_username.getText());
                            prepare.setString(3, signUp_password.getText());
                            prepare.executeUpdate();

                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Данные верны");
                            alert.setHeaderText(null);
                            alert.setContentText("Учетная запись успешно создана!");
                            alert.showAndWait();

                            // Очистка полей
                            signUp_email.setText("");
                            signUp_username.setText("");
                            signUp_password.setText("");
                        }
                    }
                }

            } else {
                System.out.println("Ошибка подключения к базе данных!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double x = 0;
    private double y = 0;

    public void signin() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = DataBase.connectDb();

        try {
            if (connect != null) {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, signIn_username.getText());
                prepare.setString(2, signIn_password.getText());

                result = prepare.executeQuery();

                Alert alert;

                if (signIn_username.getText().isEmpty() || signIn_password.getText().isEmpty()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка ввода");
                    alert.setHeaderText(null);
                    alert.setContentText("Введите логин и пароль!");
                    alert.showAndWait();

                } else {
                    if (result.next()) {

                        getData.username = signIn_username.getText();

//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Сообщение");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Данные успешно введены!");
//                        alert.showAndWait();

                        signIn_loginBtn.getScene().getWindow().hide();

                        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        root.setOnMousePressed((MouseEvent event) -> {
                            x = event.getSceneX();
                            y = event.getSceneY();
                        });

                        root.setOnMouseDragged((MouseEvent event) -> {
                            stage.setX(event.getScreenX() - x);
                            stage.setY(event.getScreenY() - y);
                        });

                        stage.initStyle(StageStyle.TRANSPARENT);

                        stage.setScene(scene);
                        stage.show();

                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Сообщение об ошибке");
                        alert.setHeaderText(null);
                        alert.setContentText("Введите правильный логин или пароль!");
                        alert.showAndWait();
                    }
                }
            } else {
                System.out.println("Ошибка подключения к базе данных!");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == signIn_createAccount) {
            signIn_form.setVisible(false);
            signUp_form.setVisible(true);
        } else if (event.getSource() == signUp_alreadyHaveAccount) {
            signIn_form.setVisible(true);
            signUp_form.setVisible(false);
        }
    }

    public void signIn_close() {
        System.exit(0);
    }

    public void signIn_minimize() {
        Stage stage = (Stage) signIn_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signUp_close() {
        System.exit(0);
    }

    public void signUp_minimize() {
        Stage stage = (Stage) signUp_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        return pattern.matcher(email).matches();
    }
}