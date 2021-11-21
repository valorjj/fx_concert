package controller.customer;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dao.ReservationDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartView_Controller_Sex implements Initializable {

	@FXML
	private AnchorPane barchart_by_sex;

	@FXML
	private LineChart<String, Integer> linechart;
	XYChart.Series<String, Integer> series = null;

	int m_no = MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id());

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		series = new XYChart.Series<String, Integer>();

		// ObservableList<XYChart.Series<String, Number>> list =
		// FXCollections.observableArrayList();

		int men = ReservationDao.get_reservationDao().get_reservation1("M");
		int women = ReservationDao.get_reservationDao().get_reservation1("F");

		series.getData().add(new XYChart.Data<String, Integer>("M", men));
		series.getData().add(new XYChart.Data<String, Integer>("F", women));

		// linechart.getXAxis().setAutoRanging(false);

		linechart.getData().add(series);
	}

}
