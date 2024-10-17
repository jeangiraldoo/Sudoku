module io.github.jeangiraldoo.soleclipsado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens io.github.jeangiraldoo.sudoku to javafx.fxml;
    exports io.github.jeangiraldoo.sudoku;
    exports io.github.jeangiraldoo.sudoku.view;
    exports io.github.jeangiraldoo.sudoku.controller to javafx.fxml;
    opens io.github.jeangiraldoo.sudoku.controller to javafx.fxml;
    opens io.github.jeangiraldoo.sudoku.view to javafx.fxml;
}