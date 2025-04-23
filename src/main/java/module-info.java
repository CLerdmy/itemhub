module dev.clerdmy.itemhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens dev.clerdmy.itemhub to javafx.fxml;
    exports dev.clerdmy.itemhub;

    opens dev.clerdmy.itemhub.ui.controllers to javafx.fxml;
    exports dev.clerdmy.itemhub.ui.controllers;
}