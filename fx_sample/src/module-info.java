module fx_sample {
	
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires mail;
	requires activation;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller.customer to javafx.graphics, javafx.fxml, mail;
	opens controller.manager to javafx.graphics, javafx.fxml;
	opens domain to javafx.graphics, javafx.fxml, javafx.base;
	opens dao to java.sql, mail; 
	//	opens fxml to javafx.fxml;
}