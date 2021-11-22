package controller.customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Concert_Info_Controller_1 implements Initializable {

	@FXML
	private AnchorPane anchorpane_main_page;

	@FXML
	private AnchorPane image_anchorpane1;

	@FXML
	private ImageView imageview1;

	@FXML
	private Label lbl_artist;

	@FXML
	private Label lbl_date;

	@FXML
	private Label lbl_title;

	ArrayList<String> concert_date_list = new ArrayList<String>();

	@FXML
	private ImageView imageview;

	@FXML
	private Label lbl_id;

	@FXML
	private Label lbl_date_1;

	@FXML
	private Label lbl_date_2;

	@FXML
	private Label lbl_date_3;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			// 첫번째 콘서트 정보를 불러온다.

			// 로그인 아이디를 불러온다.
			String login_id = Login_Controller.getInstance().get_login_id();

			// ConcertDao 에서 id 를 이용해서 콘서트 객체를 빼온다.
			Concert concert = ConcertDao.getConcertDao().get_concert_instance(1);

			// 날짜 정보도 불러옵니다. 3개이므로 리스트에 저장합니다.

			concert_date_list = ConcertDao.getConcertDao().get_concert_date_list(1);

			lbl_artist.setText(concert.getC_artist());
			lbl_title.setText(concert.getC_title());

			lbl_date_1.setText(concert_date_list.get(0));
			lbl_date_2.setText(concert_date_list.get(1));
			lbl_date_3.setText(concert_date_list.get(2));

			lbl_id.setText(login_id);
			lbl_id.setStyle("-fx-font-size: 18px");
			lbl_id.setStyle("-fx-text-fill: red ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
