/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafxapplication4.JavaFXApplication4;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HotelmanagerController1 implements Initializable {
    public JavaFXApplication4 main;
    private Button SearchRooms;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void handleevent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root);
        Stage appstage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(scene);
        appstage.show();
        
    
    }
    
   
   
    

    
}
