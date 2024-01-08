module eu.telecomnancy.labfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    opens eu.telecomnancy.labfx to javafx.fxml;
    exports eu.telecomnancy.labfx;
}