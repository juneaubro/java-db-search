package juneaugawat_sec006_ex01;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StudentInfoController implements Initializable{
	
	private Connection con;
	
	private Statement st;
	
	private ResultSet rs;
	
	private ObservableList<String> allInfo = FXCollections.observableArrayList();

    @FXML
    private Label infoLabel;

    @FXML
    private Button displayButton;

    @FXML
    private TextField userCityInputTextArea;

    @FXML
    private ListView<String> studentListView;

    @FXML
    void onDisplayButtonClick(ActionEvent event) {
		try {
			studentListView.getItems().clear();
			st = con.createStatement();
			rs = st.executeQuery("select * from students where city='"+userCityInputTextArea.getText()+"'");
			System.out.println("Get request sent to DB.");
			while(rs.next()) {
				allInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\n");
			}
			System.out.println("Get request recieved.");
			System.out.println("Data Displayed.\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	studentListView.setItems(allInfo);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@ 199.212.26.208:1521:SQLD"," COMP228_M20_001_8", "password");
			System.out.println("Connected to database.");
			st = con.createStatement();
			rs = st.executeQuery("select * from students");
			System.out.println("Get request sent to DB.");
			while(rs.next()) {
				allInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\n");
			}
			System.out.println("Get request recieved.");
			System.out.println("Data Displayed.\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentListView.setItems(allInfo);
	}
}
