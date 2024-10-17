module io.github.jeangiraldoo.docs {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.github.jeangiraldoo.docs to javafx.fxml;
    exports io.github.jeangiraldoo.docs;
}