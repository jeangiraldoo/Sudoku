package io.github.jeangiraldoo.sudoku.view;

import io.github.jeangiraldoo.sudoku.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.Optional;

/**
 *
 * Controlador que interactúa con view.fxml y el Modelo en la pantalla de ingreso de la palabra secreta
 *
 */
public class Controller {
    @FXML
    private VBox rootNode;
    private double screenHeight;
    private double screenWidth;
    //private Modelo Modelo = new Modelo();

    /**
     * Se ejecuta automáticamente luego de cargar el .fxml, inicializa atributos del controlador
     */
    public void initialize(){
        Rectangle2D dimensions = Screen.getPrimary().getBounds();
        screenHeight = dimensions.getHeight();
        screenWidth = dimensions.getWidth();
        //Modelo.setScreenWidth(screenWidth);
        //Modelo.setScreenHeight(screenHeight);
    }

    /**
     * Loads a given FXML file
     * @param fxmlFile URL del archio fxml
     * @throws IOException en caso de que el archivo fxml no se encuentre en la URL especificada
     */
    private void changeView(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent newView = loader.load();

        rootNode.getChildren().setAll(newView);
    }

    /**
     * Handles the JugarButton event. Calls changeView in order to load the game view.
     */
    public void handleJugarButton() throws IOException {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tutorial");
        alert.setHeaderText("Empezar juego");
        alert.setContentText("El juego es sudoku 6x6, es decir, el tablero tiene 6 filas y 6 columnas, y las cuadrículas son de 2x3" +
                "El objetivo del juego es llenar la cuadrícula con números del 1 al 6.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                changeView("/io/github/jeangiraldoo/sudoku/view/game.fxml");
            } else if (result.get() == ButtonType.CANCEL) {

            }
        }

    }

    /**
     * Event handler del botón de información, crea una instancia de la clase Alert, la cual implementa la interfaz AlertBox y la inicizaliza con valores
     */
    public void handleInformacionButton(){
        /**
         * Shows information about the game when the información button is pressed.
         */
        io.github.jeangiraldoo.sudoku.view.Alert alert = new io.github.jeangiraldoo.sudoku.view.Alert();
        alert.showAlert("information", "Tutorial", "Tutorial de Sudoku 6x6","El juego es sudoku 6x6, es decir, el tablero tiene 6 filas y 6 columnas, y las cuadrículas son de 2x3" +
                "El objetivo del juego es llenar la cuadrícula con números del 1 al 6.");
    }
}