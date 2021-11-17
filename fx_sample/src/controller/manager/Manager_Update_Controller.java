package controller.manager;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Manager_Update_Controller implements Initializable{
	
	Concert concert = Manager_Update_Delete_Controller.concert; // 1. 테이블뷰에서 클릭된 객체
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_concert_name.setText(concert.getC_title());
		txt_concert_aritist.setText(concert.getC_artist());
		txt_concert_date.setText(concert.getC_date());
		Image image = new Image(concert.getC_info());
		img_concert_info.setImage(image);
		lbl_concert_info_path.setText(concert.getC_info());
		concert_info_img = concert.getC_info();
		txt_concert_time.setText(concert.getC_time()+"");
		txt_r_seat_no.setText(concert.getC_R_no()+"");
		txt_s_seat_no.setText(concert.getC_S_no()+"");
		txt_d_seat_no.setText(concert.getC_D_no()+"");
		txt_e_seat_no.setText(concert.getC_E_no()+"");
		txt_r_seat_price.setText(concert.getC_R_price()+"");
		txt_s_seat_price.setText(concert.getC_S_price()+"");
		txt_d_seat_price.setText(concert.getC_D_price()+"");
		txt_e_seat_price.setText(concert.getC_E_price()+"");
		txt_concert_unique_no.setText(concert.getC_unique_no()+"");
	}

	private String concert_info_img;
	private Stage stage;

    @FXML
    private TextField txt_concert_unique_no;

    @FXML
    private Button btn_concert_info;

    @FXML
    private Button btn_concert_update;

    @FXML
    private ImageView img_concert_info;
    
	@FXML
	private TextField txt_concert_time;

	@FXML
	private TextField txt_concert_date;
	
    @FXML
    private Label lbl_concert_info_path;

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

    @FXML
    void btn_concert_update(ActionEvent event) {
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
		int c_unique_no = Integer.parseInt(txt_concert_unique_no.getText()+"");
		// 객체화
		Concert concert2 = new Concert(concert.getC_no(), c_title, c_artist, concert_info_img , c_date, c_time, c_R_no, c_S_no, c_D_no, c_E_no, c_R_price, c_S_price, c_D_price, c_E_price,c_unique_no);
		System.out.println("DB가기전");
		// DB 넣기
    	boolean result = ConcertDao.getConcertDao().update(concert2);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setHeaderText("콘서트 수정 완료");
	    	alert.showAndWait();
	    	Manager_Main_Controller.getInstance().loadpage("manager_update_delete_page");;
    	}else {System.out.println("실패");}
    	
    }



}