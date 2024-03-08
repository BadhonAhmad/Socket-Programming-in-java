module com.example.chat_by_socket {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.chat_by_socket to javafx.fxml;
    exports com.example.chat_by_socket;
}