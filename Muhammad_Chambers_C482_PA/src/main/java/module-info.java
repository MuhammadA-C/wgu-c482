module com.example.muhammad_chambers_c482_pa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.muhammad.chambers.c482.pa to javafx.fxml;

    opens com.example.muhammad.chambers.c482.pa.model to javafx.base; //had to add this to make tableview work

    exports com.example.muhammad.chambers.c482.pa;
    exports com.example.muhammad.chambers.c482.pa.controller;
    opens com.example.muhammad.chambers.c482.pa.controller to javafx.fxml;
}