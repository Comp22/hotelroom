/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import com.sun.javaws.Main;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class JavaFXApplication4 extends Application {
    
   @Override
    public void start(Stage primaryStage) throws IOException{
        Button btn = new Button();
        
            
        Parent root = FXMLLoader.load(getClass().getResource("hotelmanager.fxml"));

        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static Connection getConnection(){
            try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/demo","root","TrueBestofme");
			System.out.println("established connection");
                
			return myConn;    
			    
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				System.out.println("table created");
				}
			return null;	
        }
    
    
    
}
