package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import dao.MemberDao;
import dao.ReservationDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class ChartView_Controller_Age implements Initializable {

	// 1. reservation 테이블에서 정보를 불러옵니다.
	// 2. 구체적인 정보가 아닌 존재 하는지 여부만 확인하면 됩니다.

	@FXML
	private LineChart<String, Integer> linechart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);

		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
		int age_10 = ReservationDao.get_reservationDao().get_reservation_by_age_10(c_no);
		int age_20 = ReservationDao.get_reservationDao().get_reservation_by_age_20(c_no);
		int age_30 = ReservationDao.get_reservationDao().get_reservation_by_age_30(c_no);
		int age_40 = ReservationDao.get_reservationDao().get_reservation_by_age_40(c_no);

		series.getData().add(new XYChart.Data<String, Integer>("10대", age_10));
		series.getData().add(new XYChart.Data<String, Integer>("20대", age_20));
		series.getData().add(new XYChart.Data<String, Integer>("30대", age_30));
		series.getData().add(new XYChart.Data<String, Integer>("40대", age_40));

		series.setName("by Age");

		linechart.getData().add(series);
	}

}
