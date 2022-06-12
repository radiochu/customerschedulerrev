package Main;

import DBConnection.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("login", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"), bundle);
        primaryStage.setTitle("Customer Scheduling System");
        primaryStage.setScene(new Scene(root, 300, 325));
        primaryStage.show();
    }
}