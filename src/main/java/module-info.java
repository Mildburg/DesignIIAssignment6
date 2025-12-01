module alexgessner.designiiassignment6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens alexgessner.designiiassignment6 to javafx.fxml;
    exports alexgessner.designiiassignment6;
}