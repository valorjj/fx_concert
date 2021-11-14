module fx_sample {

	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires mysql.connector.java;

	opens application to javafx.graphics, javafx.fxml;
	opens controller.customer to javafx.graphics, javafx.fxml;
	opens domain to javafx.graphics, javafx.fxml;
	opens dao to javafx.fxml, java.sql;
}
