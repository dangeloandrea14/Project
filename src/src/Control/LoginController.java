package Control;

import Model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class LoginController implements Initializable {

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;
    
    @FXML
    private Label nameerror;
    
    @FXML
    private Label passerror;
    
    @FXML
    private Label errorelogin;
    
    @FXML
    private TextField passtext;
    
    @FXML
    private Button chiudi;
    
    @FXML
    private CheckBox checkpassword;
    
    
    
    static boolean closeanswer;

    @FXML
    private void login() throws Exception {
    	nameerror.setText("");
        passerror.setText(""); 
	
	ResultSet userData = DBConnection.query("select email,nome,titolodistudio,password,professione,ruolo,ID, canDownload from utente");
	Stage stage = (Stage) login.getScene().getWindow();
	
	 while (userData.next()) {
		
	    if ((userData.getString("email").equals(email.getText()) | userData.getString("nome").equals(email.getText()) )&& userData.getString("password").equals(password.getText())) {
	    	
		User.setName(userData.getString(2));
		User.setEmail(userData.getString(1));
		User.setProfession(userData.getString(5));
		User.setQualification(userData.getString(3));
		User.SetRole(userData.getString(6));
		User.SetPassword(userData.getString(4));
		User.SetID(userData.getInt(7));
		User.setDonwload(userData.getInt(8));
		
		    if (userData.getString("ruolo").equals("7")) {
	    	
			Parent adminpage = FXMLLoader.load(getClass().getResource("/Interface/AdminInterface1.fxml"));
			
			Scene scene = new Scene(adminpage);
			stage.setTitle("Admin Interface");
			stage.setScene(scene);
			stage.show();
			nameerror.setText("");
            passerror.setText(""); 
            errorelogin.setText("");
		    }
		            else {
		
		               Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));
        
		               Scene scene = new Scene(homepage);
		               stage.setTitle("Homepage");
		               stage.setScene(scene);
		               stage.show();
		               nameerror.setText("");
		               passerror.setText(""); 
		               errorelogin.setText("");
	                  } // else
		               
	    }
	 }
	 errorelogin.setText("Nome utente o password errati");
	 if (email.getText().equals("")) {
	 nameerror.setText("Nome Utente mancante");
	 errorelogin.setText("");
    } if (password.getText().equals("")) {
     passerror.setText("Password mancante");
    errorelogin.setText("");
     
    }
    }

    @FXML
    private void register(ActionEvent e) throws Exception {
	Stage stage = (Stage) register.getScene().getWindow(); //Source Stage!!
	Parent registrationPage = FXMLLoader.load(getClass().getResource("/Interface/Registration.fxml"));

	Scene scene = new Scene(registrationPage);
	stage.setTitle("Registrati");
	stage.setScene(scene);
	stage.show();
    }

    @FXML
    private void loginEnter(KeyEvent e) throws Exception {
    	
		
    	if(e.getCode() == KeyCode.ENTER) {
    		login();
    	}
    }
    @FXML
    private void check(ActionEvent e) throws Exception {
    	passtext.managedProperty().bind(((CheckBox)(e.getSource())).selectedProperty());
        passtext.visibleProperty().bind(((CheckBox)(e.getSource())).selectedProperty());
        
        password.managedProperty().bind(((CheckBox)(e.getSource())).selectedProperty().not());
        password.visibleProperty().bind(((CheckBox)(e.getSource())).selectedProperty().not());
        passtext.textProperty().bindBidirectional(password.textProperty());
        }
	@FXML 
	private void chiudi(ActionEvent e) throws Exception {
		
		
		Stage stage = (Stage) login.getScene().getWindow();
		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.showAndWait();
		System.out.println(alert.getResult());
		if(alert.getResult().getText().equals("OK"))
			stage.close();
		}
		

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }

}
