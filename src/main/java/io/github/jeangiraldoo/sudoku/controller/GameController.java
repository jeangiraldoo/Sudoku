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
            }
        }
    }
    private void textFieldToLabel(int sectionNumber, int sectionBlock){
        /**
         * Replaces 2 textFields in a block with 2 labels, each one containing the value associated with the
         * numbers array in the Modelo class, serving as starter hints for the user.
         * 
         */
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
        int numbersPos;
        if(pos2 == 0){
            numbersPos =3;
        }else if (pos2 == 1){
            numbersPos = 4;
        }else {
            numbersPos = 5;
        }
        HashMap[] numbers = model.getNumbers();
        int value1 = (int) numbers[blockNumber].get(Integer.toString(pos1));
        int value2 = (int) numbers[blockNumber].get(Integer.toString(numbersPos));
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
        System.out.println(Arrays.toString(numbers));
    }
    /**
     * This function is executed when the user types something in a textField and then presses the
     * enter key. It will validate if the input is valid, and if it is, it will modify the graphical board
     * accordingly.
     */
    private TextField getTextField() {
        TextField textBox = new TextField();
        textBox.setOnAction(event ->{
            boolean value = model.validateInput(textBox.getText());
            if(!value){
                Alert wrongInputAlert = new Alert();
                wrongInputAlert.showAlert("wrongInput", "error", "Entrada inválida", "La entrada ingresa no es valida. Debes ingresar un número entre 1 y 6.");
                textBox.setText("");
            }else{
                int blockNumber;
                int number = Integer.parseInt(textBox.getText());
                HBox halfBlock = (HBox) textBox.getParent();
                System.out.println("halfblock: " + halfBlock.getChildren());
                VBox block = (VBox) halfBlock.getParent();
                System.out.println("block: " + block.getChildren());
                int halfBlockPos = block.getChildren().indexOf(halfBlock);
                System.out.println("halfblockPos: " + halfBlockPos);
                int textFieldPos = halfBlock.getChildren().indexOf(textBox);
                System.out.println("TextfieldPos: " + textFieldPos);
                HBox section = (HBox) block.getParent();
                int sectionPos = board.getChildren().indexOf(section);
                int blockPos = section.getChildren().indexOf(block);
                int numbersPos = textFieldPos;
                if(sectionPos == 0){
                    if(blockPos == 0){
                        blockNumber = 0;
                        if(halfBlockPos == 1){
                            if(numbersPos == 0){
                                numbersPos = 3;
                            } else if (numbersPos == 1) {
                                numbersPos = 4;
                            } else if (numbersPos == 2) {
                                numbersPos = 5;
                            }
                        }
                    } else {
                        blockNumber = 1;
                        if(halfBlockPos == 1){
                            if(numbersPos == 0){
                                numbersPos = 3;
                            } else if (numbersPos == 1) {
                                numbersPos = 4;
                            } else if (numbersPos == 2) {
                                numbersPos = 5;
                            }
                        }
                    }
                }else if(sectionPos == 1){
                    if(blockPos == 0){
                        blockNumber = 2;
                        if(halfBlockPos == 1){
                            if(numbersPos == 0){
                                numbersPos = 3;
                            } else if (numbersPos == 1) {
                                numbersPos = 4;
                            } else if (numbersPos == 2) {
                                numbersPos = 5;
                            }
                        }
                    } else {
                        blockNumber = 3;
                        if(halfBlockPos == 1){
                            if(numbersPos == 0){
                                numbersPos = 3;
                            } else if (numbersPos == 1) {
                                numbersPos = 4;
                            } else if (numbersPos == 2) {
                                numbersPos = 5;
                            }
                        }
                    }
                }else {
                    if(blockPos == 0){
                        blockNumber = 4;
                        if(halfBlockPos == 1){
                            if(numbersPos == 0){
                                numbersPos = 3;
                            } else if (numbersPos == 1) {
                                numbersPos = 4;
                            } else if (numbersPos == 2) {
                                numbersPos = 5;
                            }
                        }
                    } else{
                        blockNumber = 5;
                        if(halfBlockPos == 1){
                            if(numbersPos == 0){
                                numbersPos = 3;
                            } else if (numbersPos == 1) {
                                numbersPos = 4;
                            } else if (numbersPos == 2) {
                                numbersPos = 5;
                            }
                        }
                    }
                }
                boolean validatedNumber = model.validateNumber(number, blockNumber, numbersPos);
                if(validatedNumber){
                    System.out.println("blockNumber: " + blockNumber);
                    Label label = new Label(Integer.toString(number));
                    label.setPrefWidth(200);
                    label.setMaxHeight(Double.MAX_VALUE);
                    label.setStyle("-fx-border-color: -fx-box-border; -fx-background-color: white;");
                    halfBlock.getChildren().set(textFieldPos, label);
                    model.increaseTotalLabels();
                    checkVictory();
                }else {
                    textBox.setStyle("-fx-border-color: red;");
                }
            }
        });
        return textBox;
    }
    /**
     * Checks if there's no more textFields in the board, in which case it will replace the
     * buttonContainer with a congratulations message.
     */
    public void checkVictory(){
        if(model.getTotalLabels() >= 36){
            buttonContainer.getChildren().clear();
            Label celebrationMessage = new Label("Felicitaciones! Ganaste!");
            celebrationMessage.setFont(new Font(25));
            celebrationMessage.setStyle("-fx-text-fill: green;");
            buttonContainer.getChildren().add(celebrationMessage);
        }
    }
    /**
     * Shows information about the game when the información button is pressed.
     */
    public void handleInformacionButton(){
        io.github.jeangiraldoo.sudoku.view.Alert alert = new io.github.jeangiraldoo.sudoku.view.Alert();
        alert.showAlert("information", "Tutorial", "Tutorial de Sudoku 6x6","El juego es sudoku 6x6, es decir, el tablero tiene 6 filas y 6 columnas, y las cuadrículas son de 2x3" +
                "El objetivo del juego es llenar la cuadrícula con números del 1 al 6.");
    }
    public void handleAyudaButton(){

    }
}
