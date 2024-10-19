package io.github.jeangiraldoo.sudoku.controller;

import io.github.jeangiraldoo.sudoku.Modelo;
import io.github.jeangiraldoo.sudoku.view.Alert;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.HashMap;

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
    private Modelo model = new Modelo();
    private HBox[] sections;


    public void initialize(){
        board.setPadding(new Insets(0, 200, 0, 200));
        sections = new HBox[] { section1, section2, section3 };
        buttonContainer.setSpacing(20);
        ayudaButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        ayudaButton.setOnMouseEntered(event ->{
            ayudaButton.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white;");
        });
        ayudaButton.setOnMouseExited(event ->{
            ayudaButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        });

        for (int section = 0; section < 3; section++) {
            for (int sectionBlock = 0; sectionBlock < 2; sectionBlock++) {
                HBox upperBlock = new HBox();
                upperBlock.setPrefWidth(400);
                HBox lowerBlock = new HBox();
                lowerBlock.setPrefWidth(400);
                for (int i = 0; i < 3; i++) {
                    TextField textBox = getTextField();
                    textBox.setPrefWidth(200);
                    upperBlock.getChildren().add(textBox);
                }
                for (int i = 0; i < 3; i++) {
                    TextField textBox = getTextField();
                    textBox.setPrefWidth(200);
                    lowerBlock.getChildren().add(textBox);
                }
                VBox block = new VBox(upperBlock, lowerBlock);
                block.setStyle("-fx-border-color: blue;");
                sections[section].getChildren().add(block);
                textFieldToLabel(section, sectionBlock);
                System.out.println(upperBlock.getChildren());
                System.out.println(lowerBlock.getChildren());
            }
        }
    }
    private void textFieldToLabel(int sectionNumber, int sectionBlock){
        int blockNumber;
        if (sectionNumber == 0){
            if(sectionBlock == 0){
                blockNumber = 0;
            } else{
                blockNumber = 1;
            }
        } else if (sectionNumber == 1) {
            if(sectionBlock == 0){
                blockNumber = 2;
            } else{
                blockNumber = 3;
            }
        }else{
            if(sectionBlock == 0){
                blockNumber = 4;
            } else{
                blockNumber = 5;
            }
        }
        HBox section = sections[sectionNumber];

        VBox block = (VBox) section.getChildren().get(sectionBlock);
        HBox upperHalf = (HBox) block.getChildren().get(0);
        HBox lowerHalf = (HBox) block.getChildren().get(1);

        int pos1= model.chooseRandomNumber(3);
        int pos2 = model.chooseRandomNumber(3);
        HashMap[] numbers = model.getNumbers();
        System.out.println(numbers[blockNumber].get("0"));
        int value1 = (int) numbers[blockNumber].get(Integer.toString(pos1));
        int value2 = (int) numbers[blockNumber].get(Integer.toString(pos2));
        System.out.println(value2);
        System.out.println(value1);
        Label label1 = new Label(Integer.toString(value1));
        label1.setPrefWidth(200);
        label1.setStyle("-fx-border-color: -fx-box-border; -fx-background-color: white;");
        HBox.setHgrow(label1, Priority.ALWAYS);
        label1.setMaxHeight(Double.MAX_VALUE);
        Label label2 = new Label(Integer.toString(value2));
        label2.setPrefWidth(200);
        label2.setStyle("-fx-border-color: -fx-box-border; -fx-background-color: white;");
        HBox.setHgrow(label2, Priority.ALWAYS);
        label2.setMaxHeight(Double.MAX_VALUE);
        upperHalf.getChildren().set(pos1, label1);
        lowerHalf.getChildren().set(pos2, label2);
    }

    private int chooseHalf(){
        return model.chooseRandomNumber(2);
    }
    private TextField getTextField() {
        TextField textBox = new TextField();
        textBox.setOnAction(event ->{
            boolean value = model.validateInput(textBox.getText());
            if(!value){
                Alert wrongInputAlert = new Alert();
                wrongInputAlert.showAlert("wrongInput", "error", "Entrada inválida", "La entrada ingresa no es valida. Debes ingresar un número entre 1 y 6.");
                textBox.setText("");
            }
        });
        return textBox;
    }

    public void handleInformacionButton(){
        io.github.jeangiraldoo.sudoku.view.Alert alert = new io.github.jeangiraldoo.sudoku.view.Alert();
        alert.showAlert("information", "Tutorial", "Tutorial de Sudoku 6x6","El juego es sudoku 6x6, es decir, el tablero tiene 6 filas y 6 columnas, y las cuadrículas son de 2x3" +
                "El objetivo del juego es llenar la cuadrícula con números del 1 al 6.");
    }
    public void handleAyudaButton(){

    }
}
