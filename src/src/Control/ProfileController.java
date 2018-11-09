package Control;

import Model.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ProfileController implements Initializable {

    
    @FXML
    private Button back;    
    @FXML
    private Button transcriber;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private Label profession;
    @FXML
    private Label qualification;
    @FXML
    private Button adminUI;
    
    

    @FXML
    private void back(ActionEvent e) throws Exception {
	Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));

	Scene scene = new Scene(homepage);
	stage.setTitle("Homepage");
	stage.setScene(scene);
	stage.show();
    }

    @FXML
    private void transcriber(ActionEvent e) throws Exception {
	Stage stage = (Stage) transcriber.getScene().getWindow(); //Source Stage!!
	Parent transcriber = FXMLLoader.load(getClass().getResource("/Interface/Transcriber.fxml"));
  
	Scene scene = new Scene(transcriber);
	stage.setTitle("Pagina Trascrizioni");
	stage.setScene(scene);
	stage.show();
    }
    
    @FXML
    private void Adminpage(ActionEvent e) throws Exception {
	Stage stage = (Stage) adminUI.getScene().getWindow();//Source Stage!!
	
	if((adminUI.getText()).equals("Candidatura")){
		Alert alert= new Alert(AlertType.CONFIRMATION,"Sei sicuro di volerti candidare come trascrittore?");
		
		alert.showAndWait();
		System.out.println(alert.getResult());
		if(alert.getResult().getText().equals("OK"))
		 {
		String str0= User.getName();
		
		String str = "update utente set RichiestaTrascrittore="+1+" where nome =\""+str0+"\"";
      Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreriamanoscritti?useSSL=false", "root", "");
      Statement stm = con.createStatement();
      stm.executeUpdate(str);
	  }else if(alert.getResult().getText().equals("annulla")){
		
	   }
		}else {
	
	Parent Admin = FXMLLoader.load(getClass().getResource("/Interface/AdminInterface1.fxml"));
	Scene scene = new Scene(Admin);
	stage.setTitle("Admin Interface");
	stage.setScene(scene);
	stage.show();
	
    }
    }
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	name.setText(User.getName());
	email.setText(User.getEmail());
	profession.setText(User.getProfession());
	qualification.setText(User.getQualification());
	password.setText(User.getPassword());
	 String ruolo=User.getRole();
	    System.out.println(ruolo);
		if(ruolo.equals("7")) {
			adminUI.setVisible(true);
		}else if (ruolo.equals("1")) {
			adminUI.setText("Candidatura");
			adminUI.setVisible(true);
		}else if(ruolo.equals("4")|ruolo.equals("7")) {
			transcriber.setVisible(true);
			
		}
    }

}
