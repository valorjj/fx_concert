package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/login_page.fxml"));
			Scene scene = new Scene(parent);

			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		int n = 50; // 관리자가 입력한 숫자
		for (int i = 0; i < n; i++) {

			Button button1 = new Button();

			button1.setId("포문에서 들어갈 숫자");

			button1.getId();

		}

		int s = n + 1; //
		int m = s + 1;

		launch(args);
	}
}
