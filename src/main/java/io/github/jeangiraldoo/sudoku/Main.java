package io.github.jeangiraldoo.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;


public class
Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/io/github/jeangiraldoo/sudoku/view/view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(getClass().getResource("/io/github/jeangiraldoo/sudoku/styles.css").toExternalForm());
        Image icon = new Image(getClass().getResource("/io/github/jeangiraldoo/sudoku/sudoku-logo.jpg").toExternalForm());
        Rectangle2D screenDimensions = Screen.getPrimary().getBounds();
        double screenHeight = screenDimensions.getHeight();
        double screenWidth = screenDimensions.getWidth();
        stage.setHeight(screenHeight * 0.8);
        stage.setWidth(screenWidth * 0.7);
        stage.setTitle("Sudoku");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}