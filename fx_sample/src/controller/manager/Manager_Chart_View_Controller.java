package controller.manager;


import java.net.URL;
import java.util.ResourceBundle;

import dao.ConcertDao;
import domain.Concert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseButton;

public class Manager_Chart_View_Controller implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Concert> titles = ConcertDao.getConcertDao().titlelist();
		combo_concert_name.setPromptText("콘서트명 선택");
		combo_concert_date.setPromptText("콘서트날짜 선택");
		combo_concert_time.setPromptText("콘서트시간 선택");
		combo_concert_name.setItems(titles);
		combo_concert_name.setOnMouseClicked(e -> {
			if ( e.getButton().equals( MouseButton.PRIMARY)) {
				String title = combo_concert_name.getSelectionModel().getSelectedItem().getC_title();
				ObservableList<Concert> dates = ConcertDao.getConcertDao().datelist(title);
				combo_concert_date.setItems(dates);
				combo_concert_date.setOnMouseClicked( e2 -> {
					if(e2.getButton().equals(MouseButton.PRIMARY)) {
						String date = combo_concert_date.getSelectionModel().getSelectedItem().toString();
						ObservableList<Concert> times = ConcertDao.getConcertDao().timelist(title, date);
						combo_concert_time.setItems(times);
						combo_concert_time.setOnMouseClicked(e3->{
						if(e3.getButton().equals(MouseButton.PRIMARY)) {
							String time = combo_concert_time.getSelectionModel().getSelectedItem().toString();
							// pie 차트 S석
							ObservableList<Concert> s_seat = ConcertDao.getConcertDao().s_seatlist(title, date, time);
							ObservableList<PieChart.Data> observableList1 = FXCollections.observableArrayList();
							for(Concert concert : s_seat) {
								observableList1.add(new PieChart.Data("전체좌석"+concert.getC_S_no()+"석", concert.getC_S_no()));
							}
							// pie 차트 R석
							ObservableList<Concert> r_seat = ConcertDao.getConcertDao().r_seatlist(title, date, time);
							ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
							for(Concert concert : r_seat) {
								observableList.add(new PieChart.Data("전체좌석"+concert.getC_R_no()+"석", concert.getC_R_no()));
							}
							// pie 차트 E석
							ObservableList<Concert> e_seat = ConcertDao.getConcertDao().e_seatlist(title, date, time);
							ObservableList<PieChart.Data> observableList2 = FXCollections.observableArrayList();
							for(Concert concert : e_seat) {
								observableList2.add(new PieChart.Data("전체좌석"+concert.getC_E_no()+"석", concert.getC_E_no()));
							}
							// pie 차트 D석
							ObservableList<Concert> d_seat = ConcertDao.getConcertDao().d_seatlist(title, date, time);
							ObservableList<PieChart.Data> observableList3 = FXCollections.observableArrayList();
							for(Concert concert : d_seat) {
								observableList3.add(new PieChart.Data("전체좌석"+concert.getC_D_no()+"석", concert.getC_D_no()));
							}
							piechart_s_seat.setData(observableList1);
							piechart_e_seat.setData(observableList2);
							piechart_d_seat.setData(observableList3);
							piechart_r_seat.setData(observableList);
							}
						
						
						});
						
					}
				});
			}
		});
		
		
		
	}
    @FXML
    private ComboBox<Concert> combo_concert_date;
    
    
    @FXML
    private ComboBox<Concert> combo_concert_name;
 

    @FXML
    private ComboBox<Concert> combo_concert_time;

    @FXML
    private PieChart piechart_d_seat;

    @FXML
    private PieChart piechart_e_seat;

    @FXML
    private PieChart piechart_r_seat;

    @FXML
    private PieChart piechart_s_seat;
    
}