module dev.clerdmy.itemhub {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.clerdmy.itemhub to javafx.fxml;
    exports dev.clerdmy.itemhub;
}