package Control;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GestioneAssegnazioniController implements Initializable {
	@FXML
    private Button homepage;
	@FXML
    private Button confirm;
	@FXML
    private Button profile;
	@FXML
	private TableView dbtable;
	@FXML
	private TableView dbtable1;
	@FXML
	private Button back;
	@FXML 
	private TextField utente;
	@FXML 
	private TextField IDpage;
	
	private ObservableList<ObservableList> data;
	
	
	public void buildData(String SQL, TableView dbtable)  {
	        
	        data = FXCollections.observableArrayList();
	        try {
	         ResultSet rs = DBConnection.query(SQL);
	 
	         
	            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
	                //We are using non property style for making dynamic table
	                final int j = i;
	                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
	                col.setPrefWidth(158);
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
    private void homepage(ActionEvent e) throws Exception {
    Stage stage = (Stage) homepage.getScene().getWindow(); //Source Stage!!
	Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));

	Scene scene = new Scene(homepage);
	stage.setTitle("Homepage");
	stage.setScene(scene);
	stage.show();
    }
    
   @FXML
    private void confirm(ActionEvent e) throws Exception {
	   String str1=utente.getText();
	   if( Integer.parseInt(str1)<8 && Integer.parseInt(str1)>0 ) {
    		String str0= IDpage.getText();
    		
    		String str= "insert into assegnazione values ("+str1+"" + " ,"+str0+") ";
    		
          Class.forName("com.mysql.jdbc.Driver");
    	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
          Statement stm = con.createStatement();
          stm.executeUpdate(str);
      
          Alert alert= new Alert(AlertType.INFORMATION);
          alert.setContentText("Assegnazione aggiunta con successo!!");
          alert.showAndWait();
        		  }
    		
    	
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
    
			
	
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String Sql="SELECT p.id, p.numero, m.titolo FROM page p, manoscritto m WHERE p.Trascrizione=\"Trascrizione non disponibile\" and p.accettato=0 and p.Manoscritto=m.ID";
		buildData(Sql, dbtable);
		Sql="Select nome, ID from utente where ruolo="+4+"";
		buildData(Sql,dbtable1);
		
		
		
		
	}
	


}
