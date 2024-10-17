package io.github.jeangiraldoo.sudoku.view;

/**
 * Interfaz que puede ser usada para crear alertas
 */
public interface AlertBoxInterface{
    public void showAlert(String type, String title, String header, String message);
}
