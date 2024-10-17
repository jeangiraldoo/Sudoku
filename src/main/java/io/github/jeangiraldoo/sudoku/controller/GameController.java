package io.github.jeangiraldoo.sudoku.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameController {
    @FXML
    private VBox board;
    @FXML
    private HBox section1;
    @FXML
    private HBox section2;
    @FXML
    private HBox section3;
    @FXML
    private Button ayudaButton;
    @FXML
    private HBox buttonContainer;

    public void initialize(){
        buttonContainer.setSpacing(20);
        ayudaButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        ayudaButton.setOnMouseEntered(event ->{
            ayudaButton.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white;");
        });
        ayudaButton.setOnMouseExited(event ->{
            ayudaButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        });
        HBox[] sections = {section1, section2, section3};
        for (int section = 0; section < 3; section++) {
            for (int sectionBlock = 0; sectionBlock < 2; sectionBlock++) {
                HBox upperBlock = new HBox(new TextField(), new TextField(), new TextField());
                HBox lowerBlock = new HBox(new TextField(), new TextField(), new TextField());
                VBox block = new VBox(upperBlock, lowerBlock);
                block.setStyle("-fx-border-color: blue;");
                sections[section].getChildren().add(block);
            }
        }
    }
    public void handleInformacionButton(){
        io.github.jeangiraldoo.sudoku.view.Alert alert = new io.github.jeangiraldoo.sudoku.view.Alert();
        alert.showAlert("information", "Tutorial", "Tutorial de Sudoku 6x6","El juego es sudoku 6x6, es decir, el tablero tiene 6 filas y 6 columnas, y las cuadrículas son de 2x3" +
                "El objetivo del juego es llenar la cuadrícula con números del 1 al 6.");
    }
    public void handleAyudaButton(){

    }
}
