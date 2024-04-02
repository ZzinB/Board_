module com.example.traveling {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.traveling to javafx.fxml;
    exports com.example.traveling;
}