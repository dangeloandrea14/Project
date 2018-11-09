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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

import Model.User;

/**
 * FXML Controller class
 *
 * @author Stas
 */
public class SearchByNameController implements Initializable {

    @FXML
    private ListView listView;
    @FXML
    private ImageView selectedImage;
    @FXML
    private Button back;
    @FXML
    private Button download;
    
    @FXML
    private void download(ActionEvent e){
	FileChooser fileChooser = new FileChooser();
	
	fileChooser.setTitle("Scegli dove salvare");
	
	File file = fileChooser.showSaveDialog(appEntry.getStage());
	if(file != null){
	    try{
		ImageIO.write(SwingFXUtils.fromFXImage(selectedImage.getImage(),
                        null), "png", file);
	    }catch(IOException ex){
		System.out.println(ex.getMessage());
	    }
	}
    }

    @FXML
    private void back(ActionEvent e) throws Exception {
	Stage stage = (Stage) back.getScene().getWindow(); //Source Stage!!
	Parent homepage = FXMLLoader.load(getClass().getResource("/Interface/Homepage.fxml"));

	Scene scene = new Scene(homepage);
	stage.setTitle("Homepage");
	stage.setScene(scene);
	stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	ResultSet pagesFromWork = DBConnection.query("select scanpath from page p, manoscritto m where p.manoscritto= m.ID and m.titolo =" + "'" + HomepageController.results() + "'");
	ObservableList<ImageView> listImage = FXCollections.observableArrayList();

	//add imageview to listImage	
	try {
	    while (pagesFromWork.next()) {
		listImage.add(generateImage(pagesFromWork.getString(1)));

	    }
	} catch (SQLException ex) {
	    System.out.println("Error: ->" + ex.getMessage());
	} catch (MalformedURLException ex) {
	    Logger.getLogger(SearchByNameController.class.getName()).log(Level.SEVERE, null, ex);
	}

	listView.setItems(listImage);
	listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {
	    @Override
	    public void changed(ObservableValue<? extends ImageView> observable, ImageView oldValue, ImageView newValue) {
//		borderPane.setCenter(newValue);
		selectedImage.setImage(newValue.getImage());
	    }

	});
	int s= User.getDownload();
	if(s==1| User.getRole().equals("7")) {
		download.setVisible(true);
	}
    }

    public ImageView generateImage(String pathImage) throws MalformedURLException {

	File file = new File(pathImage);
	ImageView container = new ImageView(new Image(file.toURI().toURL().toExternalForm()));
	container.setFitHeight(120);
	container.setFitWidth(120);
	return container;
    }

}
