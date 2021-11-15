package controller.manager;

import java.io.File;

import dao.ConcertDao;
import domain.Concert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;

public class Manager_Register_Controller {

	@FXML
	private Button btn_concert_info;

	@FXML
	private Button btn_concert_register;

	@FXML
	private ImageView img_concert_info;

	@FXML
	private Label lbl_concert_info_path;

	@FXML
	private TextField txt_concert_date;

	@FXML
	private TextField txt_concert_time;

	@FXML
	private TextField txt_concert_aritist;

	@FXML
	private TextField txt_concert_name;

	@FXML
	private TextField txt_d_seat_no;

	@FXML
	private TextField txt_d_seat_price;

	@FXML
	private TextField txt_e_seat_no;

	@FXML
	private TextField txt_e_seat_price;

	@FXML
	private TextField txt_r_seat_no;

	@FXML
	private TextField txt_r_seat_price;

	@FXML
	private TextField txt_s_seat_no;

	@FXML
	private TextField txt_s_seat_price;



	@FXML
	void btn_concert_register(ActionEvent event) {
		// 유효성 검사 생략
		String c_title = txt_concert_name.getText();
		String c_artist = txt_concert_aritist.getText();
		String c_date = txt_concert_date.getText();
		int c_time = Integer.parseInt(txt_concert_time.getText()+"");
		int c_R_no = Integer.parseInt(txt_r_seat_no.getText()+"");
		int c_S_no = Integer.parseInt(txt_s_seat_no.getText()+"");
		int c_D_no = Integer.parseInt(txt_d_seat_no.getText()+"");
		int c_E_no = Integer.parseInt(txt_e_seat_no.getText()+"");
		int c_R_price = Integer.parseInt(txt_r_seat_price.getText()+"");
		int c_S_price = Integer.parseInt(txt_s_seat_price.getText()+"");
		int c_D_price = Integer.parseInt(txt_d_seat_price.getText()+"");
		int c_E_price = Integer.parseInt(txt_e_seat_price.getText()+"");
		// 객체화
		Concert concert = new Concert(c_title, c_artist, concert_info_img, c_date, c_time, c_R_no, c_S_no, c_D_no, c_E_no,
				c_R_price, c_S_price, c_D_price, c_E_price);
		// DB처리
		boolean result = ConcertDao.getConcertDao().register(concert);
		if (result) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("콘서트가 등록되었습니다");
			alert.showAndWait();
			Manager_Main_Controller.getInstance().loadpage("manager_main_home_page");
		} else {
			System.out.println("DB처리 실패");
		}
	}
	// 파일 경로 추가
	private String concert_info_img;
	private Stage stage;

	@FXML
	void btn_concert_info(ActionEvent event) {
		// 1. 파일 선택 클래스
		FileChooser fileChooser = new FileChooser();
		// 2. 파일 스테이지 설정 getExtensionFilters : 선택할 파일 필터
		fileChooser.getExtensionFilters().add(new ExtensionFilter("그림파일 : Image File", "*png", "*jpg"));
		// 3. 스테이지 실행
		File file = fileChooser.showOpenDialog(stage);
		// 선택한 파일을 파일 클래스에 저장
		lbl_concert_info_path.setText("파일경로 : " + file.getPath());
		concert_info_img = file.toURI().toString(); // 파일의 실제[real] 경로
		Image image = new Image(concert_info_img);
		img_concert_info.setImage(image);
	}

}
