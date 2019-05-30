package api;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApi extends Application {

    public static ArrayList<String> sequences = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception { // Budowa Api
        primaryStage.setTitle("Bioinformatyka");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Window.fxml")), 600, 400, Color.GREEN));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    public static void main(String[] args) { // Main
        launch(args);
    }
}

