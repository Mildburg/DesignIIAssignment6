module alexgessner.designiiassignment6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens alexgessner.designiiassignment6 to javafx.fxml;
    exports alexgessner.designiiassignment6;
}