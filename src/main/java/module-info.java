module eu.telecomnancy.labfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens eu.telecomnancy.labfx to javafx.fxml;
    exports eu.telecomnancy.labfx to javafx.graphics;
}
