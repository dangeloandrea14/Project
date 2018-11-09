/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.User;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomepageController implements Initializable {

    @FXML
    private TextField searchBar;
    @FXML
    private Button search;
    @FXML
    private Button profile;
    @FXML
    private Button listWorks;
    @FXML
    private Button upload;
    @FXML
    private RadioButton searchByAuthor;
    @FXML
    private RadioButton searchByName;
    @FXML
    private Button manage;
    @FXML
    private ListView listView;
    @FXML
    private Label description;
    @FXML
    private Button back;
    @FXML
    private Button gestioneassegnazione;
    @FXML
    private Button revupload;
    @FXML
    private Button selectImage;
    @FXML
    private TextField workName;
    @FXML
    private TextField numPage;
    @FXML
    private Label uploadlabel;
    @FXML
    private Label uploadlabel1;
    @FXML
    private Label uploadlabel2;
    @FXML
    private Label labelcandidatura;
    @FXML 
	private Button interfacciacandidatura;
    
    private String url;

    private static String searchBarText;

    private static int manoscrittoID;
    
    //static Stage stagereturn=null;

    @FXML
    private void search(ActionEvent e) throws Exception {
	if (searchByName.isSelected() && !searchByAuthor.isSelected()) {
	    ResultSet searchByName = DBConnection.query("select scanpath from page p, manoscritto m where p.manoscritto= m.ID and m.titolo =" + "'" + searchBar.getText() + "'");
	    if (searchByName.first()) {
		searchBarText = searchBar.getText();
		Stage stage = (Stage) search.getScene().getWindow(); //Source Stage!!
		Parent resultSearch = FXMLLoader.load(getClass().getResource("/Interface/SearchByName.fxml"));

		Scene scene = new Scene(resultSearch);
		stage.setTitle("Risultati della ricerca");
		stage.setScene(scene);
		stage.show();

	    } else {
		description.setText("L'opera non è presente nella lista o "
				+ "non ha abbastanza pagine per esser pubblicata");
	    }

	}
	//works
	if (searchByAuthor.isSelected() && !searchByName.isSelected()) {
	    ResultSet searchByAuthor = DBConnection.query("select titolo from manoscritto where autore =" + "'" + searchBar.getText() + "'");

	    if (searchByAuthor.first()) {
		searchBarText = searchBar.getText();
		Stage stage = (Stage) search.getScene().getWindow(); //Source Stage!!
		Parent resultSearch = FXMLLoader.load(getClass().getResource("/Interface/SearchByAuthor.fxml"));

		Scene scene = new Scene(resultSearch);
		stage.setTitle("Risultati della ricerca");
		stage.setScene(scene);
		stage.show();
	    } else {
		description.setText("L'autore che stai cercando non è presente");
	    }

	}
    
    }
    @FXML
    private void selectImage(ActionEvent e) throws SQLException, MalformedURLException {
	

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Seleziona l'immagine");

    // save the selected file

    File selectedFile = fileChooser.showOpenDialog(appEntry.getStage());
    
    //linux and maybe mac
    //url2 = selectedFile.toURI().toURL().toString().substring(5);
    
    
    //windows url
    url = selectedFile.toURI().toURL().toExternalForm();
    String newUrl = "/" + url.substring(5);

    if (selectedFile != null) {

      ResultSet idWork = DBConnection.query("select ID from Manoscritto WHERE Titolo = '" + workName.getText() + "'");
      
      if (idWork.next()) {
      // mettiamo .next() per poter evitare la riga con tutti i null
    	  DBConnection.uploadPage(Integer.parseInt(numPage.getText()), idWork.getInt(1), newUrl);
      } else {
        System.out.println("The ResultSet is empty");
      }

    }

  }// end method
   
    
    @FXML
    public void profile(ActionEvent e) throws Exception {
    	
	Stage stage = (Stage) profile.getScene().getWindow(); //Source Stage!!
	/*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Interface/Profile.fxml")); 
	Parent root1 = (Parent) fxmlLoader.load();
	Stage stage = new Stage(); 
	stage.setScene(new Scene(root1)); 
	stage.initModality(Modality.APPLICATION_MODAL);
	stage.showAndWait();
	*/
	
	Parent profile = FXMLLoader.load(getClass().getResource("/Interface/Profile.fxml"));
    
	Scene scene = new Scene(profile);
	stage.setTitle("Il tuo profilo");
	stage.setScene(scene);
	stage.show();
    }

    @FXML
    private void loginpage(ActionEvent e) throws Exception {
	Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent login = FXMLLoader.load(getClass().getResource("/Interface/Login.fxml"));
    
	Scene scene = new Scene(login);
	stage.setTitle("Login");
	stage.setScene(scene);
	stage.show();
    }
   
    @FXML
    private void listWorks(ActionEvent e)throws Exception {
	Stage stage = (Stage) listWorks.getScene().getWindow(); //Source Stage!!
	Parent listaOpere = FXMLLoader.load(getClass().getResource("/Interface/ListaOpere.fxml"));

	Scene scene = new Scene(listaOpere);
    stage.setTitle("Lista Opere");
	stage.setScene(scene);
	stage.show();
    }
    @FXML
    private void upload(ActionEvent e)throws Exception{

	Stage stage = (Stage) upload.getScene().getWindow(); //Source Stage!!
	Parent upload = FXMLLoader.load(getClass().getResource("/Interface/Upload.fxml"));

	Scene scene = new Scene(upload);
	stage.setTitle("Upload");
	stage.setScene(scene);
	stage.show();

    }

  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	ResultSet workName = DBConnection.query("select titolo, ID from manoscritto");
	ObservableList<String> listWorks = FXCollections.observableArrayList();
	try {
	    while (workName.next()) {
		listWorks.add(workName.getString(1));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
	}

	listView.setItems(listWorks);
	listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		ResultSet id = DBConnection.query("select id from manoscritto where titolo =" + "'" + observable.getValue() + "'");
		try {
		    id.next();
		    manoscrittoID = id.getInt(1);
		} catch (SQLException ex) {
		    Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
		}
		Stage stage = (Stage) listView.getScene().getWindow(); //Source Stage!!
		Parent profile = null;
		try {
		    profile = FXMLLoader.load(getClass().getResource("/Interface/ListaOpere.fxml"));
		} catch (IOException ex) {
		    Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
		}

		Scene scene = new Scene(profile);
		stage.setTitle(observable.getValue());
		stage.setScene(scene);
		stage.show();

	    }

	} );
	
	String s=User.getRole();
    if(s.equals("6")|s.equals("7")) {
    	gestioneassegnazione.setVisible(true);
    }
    if(s.equals("2")|s.equals("3")|s.equals("7")) {
    	uploadlabel.setVisible(true);
    	uploadlabel1.setVisible(true);
    	uploadlabel2.setVisible(true);
    	this.workName.setVisible(true);
    	numPage.setVisible(true);
    	selectImage.setVisible(true);
    }
    if(s.equals("1")) {
    	labelcandidatura.setVisible(true);
    }
    if(s.equals("3")|s.equals("7")) {
    	revupload.setVisible(true);
    	}
    if(s.equals("5")) {
    	interfacciacandidatura.setVisible(true);
    	}
    
    }
    
 
    @FXML
    private void gestioneAssegnazione(ActionEvent e)throws Exception{

    	Stage stage = (Stage) gestioneassegnazione.getScene().getWindow(); //Source Stage!!
    	Parent assegnazione = FXMLLoader.load(getClass().getResource("/Interface/GestioneAssegnazioni.fxml"));

    	Scene scene = new Scene(assegnazione);
    	stage.setTitle("Gestione Assegnazioni");
    	stage.setScene(scene);
    	stage.show();

        }
    @FXML
    private void revisioneupload(ActionEvent e)throws Exception{

    	Stage stage = (Stage) revupload.getScene().getWindow(); 
    	Parent revisione = FXMLLoader.load(getClass().getResource("/Interface/InterfacciaRevisioneUpload.fxml"));

    	Scene scene = new Scene(revisione);
    	stage.setTitle("Revisione Upload");
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
    public static int getManoscrittoID() {
	return manoscrittoID;
    }

    public static String results() {
	return searchBarText;
    }
    
    
}
