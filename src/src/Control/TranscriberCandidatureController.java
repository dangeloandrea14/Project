package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
public class TranscriberCandidatureController implements Initializable {
	@FXML
    private Button accept;
	@FXML
    private Button decline;
    @FXML
	private TableView dbtable;
	@FXML
	private Button back;
	@FXML
	private Button homepage;
	@FXML 
	private TextField utente;

	

	private ObservableList<ObservableList> data;
	
	@FXML
	public void buildData(String SQL)  {
	        
	        data = FXCollections.observableArrayList();
	        try {
	         ResultSet rs = DBConnection.query(SQL);
	 
	         
	            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
	                //We are using non property style for making dynamic table
	                final int j = i;
	                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
	                col.setPrefWidth(250);
	                col.setResizable(false);
	                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
	                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
	                        return new SimpleStringProperty(param.getValue().get(j).toString());
	                       
	                    }
	                });
	                
	                dbtable.getColumns().addAll(col);
	                
	            }
	 
	          
	            while (rs.next()) {
	                //Iterate Row
	                ObservableList<String> row = FXCollections.observableArrayList();
	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
	                    //Iterate Column
	                    row.add(rs.getString(i));
	                }
	                 
	                data.add(row);
	 
	            }
	 
	           
	            dbtable.setItems(data);
	        } catch (Exception exx) {
	            exx.printStackTrace();
	            System.out.println("Error on Building Data");
	        }
	        
	    }

	@FXML
    private void back(ActionEvent e) throws Exception {

	Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent profilePage = FXMLLoader.load(getClass().getResource("/Interface/AdminInterface1.fxml"));

	Scene scene = new Scene(profilePage);
	stage.setTitle("Admin Interface");
	stage.setScene(scene);
	stage.show();
    }

    
   @FXML
    private void candidature(ActionEvent e) throws Exception {
	   String str0=utente.getText(), str;
	   String bottone=((Button)e.getSource()).getText();
	   
	   if(bottone.equals("Accetta Candidatura")) {
    		str= "update utente set ruolo="+4+", RichiestaTrascrittore="+0+" where nome =\""+str0+"\"";
    		System.out.println(str);
       }  else {
    		str="update utente set RichiestaTrascrittore="+0+" where nome =\""+str0+"\"";
    	  }
	   Class.forName("com.mysql.jdbc.Driver");
 	   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
       Statement stm = con.createStatement();
       stm.executeUpdate(str);
       
       
       
       
       dbtable.getColumns().clear();
       
       String Sql="select Nome from utente where RichiestaTrascrittore="+1+"";
 		buildData(Sql);
    	
    }

   @FXML
   private void homepage(ActionEvent e) throws Exception {
   Stage stage = (Stage) homepage.getScene().getWindow(); //Source Stage!!
	Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));

	Scene scene = new Scene(homepage);
	stage.setTitle("Homepage");
	stage.setScene(scene);
	stage.show();
   }
   	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String Sql="select  Nome from utente where RichiestaTrascrittore="+1+"";
		buildData(Sql);
		
		if(User.getRole().equals("7")) {
			back.setVisible(true);
		}
}

	
}