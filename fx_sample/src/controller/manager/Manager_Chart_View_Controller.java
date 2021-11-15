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

public class Manager_Chart_View_Controller implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combo_concert_date.setItems(date);
		combo_concert_name.setItems(title);
		combo_concert_time.setItems(time);

		
	}

		
	
    @FXML
    private ComboBox combo_concert_date;

    int i=0;
    private ObservableList<String> date = FXCollections.observableArrayList(ConcertDao.getConcertDao().concertlist().get(i).getC_date()+"");

    @FXML
    private ComboBox combo_concert_name;
    private ObservableList<String> title = FXCollections.observableArrayList("apple", "banana", "lemon", "grape");


    @FXML
    private ComboBox combo_concert_time;
    private ObservableList<String> time = FXCollections.observableArrayList("apple", "banana", "lemon", "grape");


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
