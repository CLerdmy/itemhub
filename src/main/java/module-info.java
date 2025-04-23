module dev.clerdmy.itemhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires org.slf4j;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;


    opens dev.clerdmy.itemhub to javafx.fxml;
    exports dev.clerdmy.itemhub;

    opens dev.clerdmy.itemhub.ui.controllers to javafx.fxml;
    exports dev.clerdmy.itemhub.ui.controllers;
}