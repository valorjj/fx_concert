package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			// Font.loadFont(getClass().getResourceAsStream("/fonts/NanumBarunGothicBold.ttf"),
			// 10);

			Parent parent = FXMLLoader.load(getClass().getResource("/fxml/reservation_page_date_select.fxml"));
			Scene scene = new Scene(parent);

			// scene.getStylesheets().add(getClass().getResource("login_page_style.css").toExternalForm());
			// css 적용

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
