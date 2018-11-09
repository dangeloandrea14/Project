package Control;

import Model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TranscriberController implements Initializable {

	static int idManoscritto;
	static int idPagina;
	@FXML
	private Button back;
	@FXML
	private Button submit;
	@FXML
	private TextArea textArea;
	@FXML
	private ListView manoscritto;
	@FXML
	private ListView pagina;
	@FXML
	private ImageView img;

	@FXML
	private void back(ActionEvent e) throws Exception {

		Stage stage = (Stage) back.getScene().getWindow(); // Source Stage!!
		Parent profilePage = FXMLLoader.load(getClass().getResource("/Interface/Profile.fxml"));

		Scene scene = new Scene(profilePage);
		stage.setTitle("Profile");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void submit(ActionEvent e) throws Exception {

		
		System.out.println(textArea.getText());
		//DBConnection.query("update page set Trascrizione ="+ textArea.getText() + " where id ="+idPagina);
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/libreriamanoscritti?useSSL=false", "root", "root");
	    Statement stm = con.createStatement();
	    stm.executeUpdate("update page set Trascrizione ="+ "'" + textArea.getText()+"'" + " where id ="+idPagina);
	    con.close();
		

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
		
		manoscritto.setItems(work);
		manoscritto.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		manoscritto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				//Divina commedia...
				
				System.out.println(arg0.getValue()+"ciao");
				ObservableList<Integer> idWorks = FXCollections.observableArrayList();
				ObservableList<Integer> idPages = FXCollections.observableArrayList();
				ResultSet sd = DBConnection.query("select ID from manoscritto where Titolo ="+"'"+arg0.getValue()+ "'");
				try {
					sd.next();
					idManoscritto = sd.getInt("ID");
					System.out.println(idManoscritto);
					System.out.println(User.getID());
					ResultSet pagineAssegnate = DBConnection.query("select p.numero from page p, assegnazione a where p.manoscritto =" + sd.getInt("ID") +
					" and p.Accettato = 0 and p.ID=a.IDPage and a.IDUtente = "+User.getID());
				
			
					while(pagineAssegnate.next()) {
						idPages.add(pagineAssegnate.getInt("numero"));
						
					}
					pagina.setItems(idPages);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
					//	System.out.println(getImage.getString("scanpath"));
						idPagina = getImage.getInt("ID");
						//System.out.println(idPagina);
						img.setImage(new Image(new FileInputStream(getImage.getString("scanPath"))));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		
		
	}//end init
	
	

	
}//end class


