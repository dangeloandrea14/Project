package Control;

import Model.User;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button register;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private TextField qualification;
    @FXML
    private TextField profession;
    @FXML
    private CheckBox checkpassword;
    @FXML
    private TextField passtext;
    @FXML
    private TextField confirmtext;
    @FXML
    private PasswordField passconfirm;
    
    
    @FXML
    private void register(ActionEvent e) throws Exception {
	
	if (!(email.getText().equals("") && password.getText().equals(""))) {

	    DBConnection.update( email.getText(), password.getText(), name.getText(), qualification.getText(), profession.getText());
	    
	    User.setEmail(email.getText());
	    User.setName(name.getText());
	    User.setProfession(profession.getText());
	    User.setQualification(qualification.getText());
	    User.SetRole("1");
	    
	    Stage stage = (Stage) register.getScene().getWindow(); //Source Stage!!
	    Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Login.fxml"));

	    Scene scene = new Scene(homepage);
	    stage.setTitle("Login");
	    stage.setScene(scene);
	    stage.show();
	}
    }

    @FXML
    private void back(ActionEvent e) throws Exception {

	Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent loginPage = FXMLLoader.load(getClass().getResource("/Interface/Login.fxml"));

	Scene scene = new Scene(loginPage);
	stage.setTitle("Login");
	stage.setScene(scene);
	stage.show();
    }
    @FXML
	private  void checkpassword(ActionEvent e) {
    	passtext.managedProperty().bind(((CheckBox)(e.getSource())).selectedProperty());
        passtext.visibleProperty().bind(((CheckBox)(e.getSource())).selectedProperty());
        
        password.managedProperty().bind(((CheckBox)(e.getSource())).selectedProperty().not());
        password.visibleProperty().bind(((CheckBox)(e.getSource())).selectedProperty().not());
        passtext.textProperty().bindBidirectional(password.textProperty());
        //mostra conferma password
        //confirmtext.managedProperty().bind(checkpassword.selectedProperty());
        //confirmtext.visibleProperty().bind(checkpassword.selectedProperty());
        //passconfirm.managedProperty().bind(checkpassword.selectedProperty().not());
        //passconfirm.visibleProperty().bind(checkpassword.selectedProperty().not());

        
        confirmtext.textProperty().bindBidirectional(password.textProperty());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
