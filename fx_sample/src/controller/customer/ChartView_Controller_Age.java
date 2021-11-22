package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

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
	private LineChart<Integer, Integer> linechart;
	XYChart.Series<Integer, Integer> series = null;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		XYChart.Series<Integer, Integer> series = new XYChart.Series<Integer, Integer>();
		int age_10 = ReservationDao.get_reservationDao().get_reservation_by_age_10();
		int age_20 = ReservationDao.get_reservationDao().get_reservation_by_age_20();
		int age_30 = ReservationDao.get_reservationDao().get_reservation_by_age_30();
		int age_40 = ReservationDao.get_reservationDao().get_reservation_by_age_40();

		series.getData().add(new XYChart.Data<Integer, Integer>(10, age_10));
		series.getData().add(new XYChart.Data<Integer, Integer>(20, age_20));
		series.getData().add(new XYChart.Data<Integer, Integer>(30, age_30));
		series.getData().add(new XYChart.Data<Integer, Integer>(40, age_40));

		series.setName("by Age");

		linechart.getData().add(series);
	}

}
