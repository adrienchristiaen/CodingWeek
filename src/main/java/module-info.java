module eu.telecomnancy.labfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.json;


    opens eu.telecomnancy.labfx to javafx.fxml;
    exports eu.telecomnancy.labfx to javafx.graphics;
    exports eu.telecomnancy.labfx.controller to javafx.graphics;
    opens eu.telecomnancy.labfx.controller to javafx.fxml;


}
