package controller.manager;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.ConcertDao;
import dao.ConcertDate;
import dao.SeatDao;
import domain.Concert;
import domain.Seat;
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
		
		try {
			ObservableList<Concert> titles = ConcertDao.getConcertDao().titlelist();
			combo_concert_name.setItems(titles);
			combo_concert_name.setOnMouseClicked(e -> {
				if (e.getButton().equals(MouseButton.PRIMARY)) {
					date = combo_concert_name.getSelectionModel().getSelectedItem().getC_title();
					if (date != null) {
						ObservableList<Concert> dates = ConcertDao.getConcertDao().datelist(date);
						combo_concert_date.setItems(dates);
						combo_concert_date.setOnMouseClicked(e2 -> {
							if(e2.getButton().equals(MouseButton.PRIMARY)) {
								time = combo_concert_date.getSelectionModel().getSelectedItem().getC_date();
								if (time != null) {
									ObservableList<Concert> times = ConcertDao.getConcertDao().timelist(date, time);
									combo_concert_time.setItems(times);
								}
							}
						});
					}
				}
			});
		} catch (Exception e3) {System.out.println(e3);}

//		combo_concert_time.setItems( times );
		//pic chart
//		ObservableList<Seat> seats = SeatDao.getSeatDao().se
		
		
	}
	public static String time;
	public static String date;
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

    @FXML
    void combo_concert_date(ActionEvent event) {
    	
    }
    
    
    @FXML
    void combo_concert_name(ActionEvent event) {
    	
    	
    }

    @FXML
    void combo_concert_time(ActionEvent event) {

    }
    
}