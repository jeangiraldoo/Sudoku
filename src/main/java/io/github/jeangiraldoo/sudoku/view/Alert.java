package io.github.jeangiraldoo.sudoku.view;

/**
 * Clase que implementa la interfaz AlertBox para poder mostrar mensajes de informacion cuando se requiera
 */
public class Alert implements AlertBoxInterface{
    /**
     * Muestra una alerta informativa
     * @param title Titulo de la ventana
     * @param header Cabecera del mensaje
     * @param message Cuerpo del mensaje
     */
    @Override
    public void showAlert(String type, String title, String header, String message) {
        javafx.scene.control.Alert alert;
        if(type.equals("information")) {
            alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            System.out.println("info");
        }else{
            System.out.println("confir");
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
