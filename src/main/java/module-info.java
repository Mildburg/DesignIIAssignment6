module alexgessner.designiiassignment6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens alexgessner.designiiassignment6 to javafx.fxml;
    exports alexgessner.designiiassignment6;
    exports alexgessner.designiiassignment6.Controllers;
    opens alexgessner.designiiassignment6.Controllers to javafx.fxml;
    exports alexgessner.designiiassignment6.Model;
    opens alexgessner.designiiassignment6.Model to javafx.fxml;
}