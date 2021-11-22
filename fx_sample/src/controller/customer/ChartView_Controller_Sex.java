package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import dao.MemberDao;
import dao.ReservationDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class ChartView_Controller_Sex implements Initializable {

	@FXML
	private AnchorPane barchart_by_sex;

	@FXML
	private LineChart<String, Integer> linechart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		int c_no = ConcertDao.getConcertDao().get_concert_c_no(Reservation_Date_Select_Controller.user_selected_date,
				Reservation_Date_Select_Controller.user_selected_time);

		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();

		int men = ReservationDao.get_reservationDao().get_reservation1("M", c_no);
		int women = ReservationDao.get_reservationDao().get_reservation1("F", c_no);

		series.getData().add(new XYChart.Data<String, Integer>("male", men));
		series.getData().add(new XYChart.Data<String, Integer>("female", women));

		series.setName("by Sex");

		linechart.getData().add(series);

	}

}
