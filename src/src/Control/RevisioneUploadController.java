package Control;


import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RevisioneUploadController implements Initializable {
	static int idManoscritto;
	static int idPagina;
	@FXML
    private Button homepage;
	@FXML
    private Button accetta;
	@FXML
    private Button rifiuta;
	@FXML
    private Button profile;
	@FXML
	private TableView dbtable;
	@FXML
	private Button back;
	@FXML
	private ListView titolo;
	@FXML
	private ListView pagina;
	@FXML
	private ImageView img;
	
	
	
	
	
	
	
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
    private void decisione(ActionEvent e) throws Exception {
	  String decisione=((Button)(e.getSource())).getText();
	  Alert alert= new Alert(AlertType.INFORMATION);
	  if(decisione.equals("Accetta")) {
		  System.out.println(idManoscritto+","+idPagina );
		  
		  String str= "update page set accettato =1 where ID="+idPagina+" and Manoscritto ="+idManoscritto;
  		
          Class.forName("com.mysql.jdbc.Driver");
  	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
          Statement stm = con.createStatement();
          stm.executeUpdate(str);
		  alert.setContentText("Accettazione avvenuta con successo!!");
		  
		 
		  
	  }else {
		  String str= "delete from page where ID="+idPagina+" and Manoscritto ="+idManoscritto;
		  Class.forName("com.mysql.jdbc.Driver");
  	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
          Statement stm = con.createStatement();
          stm.executeUpdate(str);
		  alert.setContentText("Rifuto avvenuto con successo!!");
	  }
      
	  initialize(null, null);
          
          alert.showAndWait();
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
    
    
    
    @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ResultSet works = DBConnection.query("select ID, titolo from manoscritto");
		
		
		ObservableList<String> work = FXCollections.observableArrayList();
		ObservableList<Integer> pag = FXCollections.observableArrayList();
		
		try {
			while (works.next()) {
				work.add(works.getString("titolo"));
			
			}
		} catch (SQLException ex) {
			Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		titolo.setItems(work);
		titolo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		titolo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				System.out.println(arg0.getValue());
				ObservableList<Integer> idWorks = FXCollections.observableArrayList();
				ObservableList<Integer> idPages = FXCollections.observableArrayList();
				ResultSet sd = DBConnection.query("select ID from manoscritto where Titolo ="+"'"+arg0.getValue()+ "'");
				try {
					sd.next();
					idManoscritto = sd.getInt("ID");
					System.out.println("select p.numero from page p, assegnazione a where p.manoscritto= "+sd.getInt("ID")+" and p.Accettato = 0 and p.ID=a.IDPage");
					
					
					ResultSet pagine= DBConnection.query("select p.numero from page p, assegnazione a where p.manoscritto= "+sd.getInt("ID")+" and p.Accettato = 0");
					while(pagine.next()) {
						idPages.add(pagine.getInt("numero"));
						
					}
					pagina.setItems(idPages);
				
				
                      } catch (SQLException e) {
					
					e.printStackTrace();
				}				
			}


		} );//end
		pagina.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		pagina.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

		
		@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				ResultSet getImage = DBConnection.query(""
						+ "select ID,scanpath, manoscritto from page where numero ="+ arg0.getValue()+ " and manoscritto = "+idManoscritto);
				
				try {
					while(getImage.next()) {
					
						idPagina = getImage.getInt("ID");
						System.out.println(idPagina);
						img.setImage(new Image(new FileInputStream(getImage.getString("scanPath"))));
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}

		});
		
		
	}
		
		
	}
	



