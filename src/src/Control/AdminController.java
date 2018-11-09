package Control;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
public class AdminController implements Initializable {
	@FXML
    private Button homepage;
	@FXML
    private Button changerole;
	@FXML
    private Button profile;
	@FXML
    private Button changetranscriber;
	@FXML
	private TableView dbtable;
	@FXML
	private Button back;
	@FXML 
	private TextField utente;
	@FXML 
	private TextField ruolo;
	@FXML 
	private TextField lvt;
	@FXML
	private Button ruolo1;
	@FXML
	private Button ruolo2;
	@FXML
	private Button ruolo3;
	@FXML
	private Button ruolo4;
	@FXML
	private Button ruolo5;
	@FXML
	private Button ruolo6;
	@FXML
	private Button ruolo7;
	@FXML
	private Button cd0;
	@FXML
	private Button cd1;
	@FXML 
	private Button interfacciacandidatura;
	

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
	                col.setPrefWidth(145.25);
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
	Parent profilePage = FXMLLoader.load(getClass().getResource("/Interface/Login.fxml"));

	Scene scene = new Scene(profilePage);
	stage.setTitle("Login");
	stage.setScene(scene);
	stage.show();
    }
    @FXML
    private void homepage(ActionEvent e) throws Exception {
    Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));

	Scene scene = new Scene(homepage);
	stage.setTitle("Homepage");
	stage.setScene(scene);
	stage.show();
    }
    
   @FXML
    private void changerole(ActionEvent e) throws Exception {
	   String str1=ruolo.getText();
	   if( Integer.parseInt(str1)<8 && Integer.parseInt(str1)>0 ) {
    		String str0= utente.getText();
    		
    		String str= "update utente set ruolo="+str1+" where nome =\""+str0+"\"";
          Class.forName("com.mysql.jdbc.Driver");
    	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
          Statement stm = con.createStatement();
          stm.executeUpdate(str);
          dbtable.getColumns().clear();
          
          String Sql="select Nome , Ruolo as 'Ruolo Utente', LivelloTrascrittore as 'Livello Trascrittore', CanDownload as 'Permesso Download' from utente";
    		buildData(Sql);
    		
    	}
    		
    	
    }
    @FXML
   private void changelvtranscriber(ActionEvent e) throws Exception{
    	String str1=lvt.getText();
    	
    	if( Integer.parseInt(str1)<4 && Integer.parseInt(str1)>=0 ) {
    		String str0= utente.getText();
    	
    	  String str= "update utente set LivelloTrascrittore="+str1+" where nome =\""+str0+"\"";
    		
          Class.forName("com.mysql.jdbc.Driver");
    	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
          Statement stm = con.createStatement();
          stm.executeUpdate(str);
         dbtable.getColumns().clear();
          String Sql="select Nome , Ruolo as 'Ruolo Utente', LivelloTrascrittore as 'Livello Trascrittore', CanDownload as 'Permesso Download' from utente";
    		buildData(Sql);
    	}
    }
    
    
    @FXML
    private void elencoruoli(ActionEvent e) throws Exception {
    	Button b = (Button)e.getSource();
    	String testo= String.valueOf((b.getText()).charAt((b.getText()).length()-1));

    	ruolo.setText(testo);
    	
    }
    @FXML
    private void canDownload(ActionEvent e) throws Exception{
    	Button b = (Button)e.getSource();
    	String testo= String.valueOf((b.getText()).charAt((b.getText()).length()-1));
        
    	String str= "update utente set CanDownload="+testo+" where nome =\""+utente.getText()+"\"";
     		
           Class.forName("com.mysql.jdbc.Driver");
     	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
           Statement stm = con.createStatement();
           stm.executeUpdate(str);
          dbtable.getColumns().clear();
           String Sql="select Nome , Ruolo as 'Ruolo Utente', LivelloTrascrittore as 'Livello Trascrittore', CanDownload as 'Permesso Download' from utente";
     		buildData(Sql);
     	}
    
    
    @FXML
    private void profile(ActionEvent e) throws Exception {
	Stage stage = (Stage) profile.getScene().getWindow(); //Source Stage!!
	Parent profile = FXMLLoader.load(getClass().getResource("/Interface/Profile.fxml"));
    
	Scene scene = new Scene(profile);
	stage.setTitle("Il tuo profilo");
	stage.setScene(scene);
	stage.show();
    }
    @FXML
    private void interfacciacandidatura(ActionEvent e) throws Exception {
    	String Sql="select  Nome from utente where RichiestaTrascrittore="+1+"";
		ResultSet rs=DBConnection.query(Sql);
		
			
				if(!(rs.next())) {
					Alert alert = new Alert(AlertType.INFORMATION, "NESSUN UTENTE HA FATTO RICHIESTA DI CANDIDATURA");
					alert.showAndWait();
					
				
				}else {
					Stage stage = (Stage) interfacciacandidatura.getScene().getWindow(); //Source Stage!!
					Parent candidature= FXMLLoader.load(getClass().getResource("/Interface/TranscriberCandidatureInterface.fxml"));
				    
					Scene scene = new Scene(candidature);
					stage.setTitle("Interfaccia Candidature");
					stage.setScene(scene);
					stage.show();}
		}
			
			
	
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String Sql="select Nome , Ruolo as 'Ruolo Utente', LivelloTrascrittore as 'Livello Trascrittore', CanDownload as 'Permesso Download' from utente";
		buildData(Sql);
		
		
		
		
		
	}
	
}