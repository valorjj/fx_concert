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
	// 3.

	@FXML
	private AnchorPane barchart_by_sex;

	@FXML
	private LineChart<String, Integer> linechart;
//	XYChart.Series<String, Integer> series = null;

	int m_no = MemberDao.getMemberDao().get_m_no_member(Login_Controller.getInstance().get_login_id());

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();


		// ObservableList<XYChart.Series<String, Number>> list =
		// FXCollections.observableArrayList();


		linechart.getXAxis().setAutoRanging(false);

		linechart.getData().add(series);
	}

}
