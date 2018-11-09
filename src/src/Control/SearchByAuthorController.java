/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
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
import javafx.stage.Stage;


public class SearchByAuthorController implements Initializable {

    @FXML
    private ListView resultList;
    @FXML
    private Button back;
    
    private static String selectedWork;

    @FXML
    private void back(ActionEvent e) throws Exception {

	Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent root = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));
	Scene scene = new Scene(root);
	stage.setTitle("Homepage");
	stage.setScene(scene);
	stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

	ObservableList<String> list = FXCollections.observableArrayList();
	ResultSet searchByAuthor = DBConnection.query("select titolo from manoscritto where autore =" + "'" + HomepageController.results() + "'");

	try {
	    while (searchByAuthor.next()) {
		list.add(searchByAuthor.getString(1));
	    }

	    resultList.setItems(list);

	    resultList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	    resultList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    //open selected work
		    selectedWork = observable.getValue();
		    
		    Stage stage = (Stage) resultList.getScene().getWindow(); //Source Stage!!
		    Parent profile = null;
		    try {
			profile = FXMLLoader.load(getClass().getResource("/Interface/resultsFromSearch.fxml"));
		    } catch (IOException ex) {
			Logger.getLogger(SearchByAuthorController.class.getName()).log(Level.SEVERE, null, ex);
		    }

		    Scene scene = new Scene(profile);
		    stage.setTitle("Risultati della ricerca");
		    stage.setScene(scene);
		    stage.show();
		}

	    });
	} catch (SQLException ex) {
	    Logger.getLogger(SearchByAuthorController.class.getName()).log(Level.SEVERE, null, ex);
	}

    }//end init
    
    public static String getSelectedWork(){
	return selectedWork;
    }

}
