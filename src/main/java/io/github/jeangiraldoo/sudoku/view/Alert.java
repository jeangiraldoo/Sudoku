package io.github.jeangiraldoo.sudoku.view;

/**
 * Clase que implementa la interfaz AlertBox para poder mostrar mensajes de informacion cuando se requiera
 */
public class Alert implements AlertBoxInterface{
    /**
     * Muestra una alerta informativa
     * @param title Window title
     * @param header Header of the message
     * @param message Body of the message
     */
    @Override
    public void showAlert(String type, String title, String header, String message) {
        javafx.scene.control.Alert alert;
        if(type.equals("information")) {
            alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        }else if(type.equals("wrongInput")){
            alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        } else{
            alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        }

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        if (type.equals("information")) {
            alert.show();
        }else{
            alert.showAndWait();
        }
        alert.show();
    }
}
