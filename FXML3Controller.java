/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javafxapplication4.FXML2Controller.d;

/**
 * FXML Controller class
 *
 *
 * @author User
 */
public class FXML3Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    public ListView lv1;
    @FXML
    public TextArea ta1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lv1.setItems(d);
        ta1.setText("total cost: " + FXML2Controller.total);
    }

    public void addcustomer(ActionEvent event) throws Exception {
        try {

            ArrayList<Room> room = FXML2Controller.rooms;

            JavaFXApplication4.getConnection();
            for (int i = 0; i < room.size(); i++) {

                System.out.println(Integer.parseInt(room.get(i).getCost()));
                String q = "insert into CUSTOMERS (CUSTOMERNAME,ID,ROOMNUMBER,COST,TOTALCOST,BEDTYPE,CHECKINDATE,CHECKOUTDATE) values ('" + name.getText().toString() + "'," + Integer.parseInt(id.getText().toString()) + ",'" + room.get(i).getRoomnumber() + "'," + Integer.parseInt(room.get(i).getCost()) + "," + Integer.parseInt(room.get(i).getTotalcost()) + ",'" + room.get(i).getType() + "','" + room.get(i).getCheckindate() + "','" + room.get(i).getCheckoutdate() + "');";
                String r = "insert into HOTEL (ROOMNUMBER,COST,TOTALCOST,BEDTYPE,CHECKINDATE,CHECKOUTDATE, BOOKING) values ('" + room.get(i).getRoomnumber() + "'," + Integer.parseInt(room.get(i).getCost()) + "," + Integer.parseInt(room.get(i).getTotalcost()) + ",'" + room.get(i).getType() + "','" + room.get(i).getCheckindate() + "','" + room.get(i).getCheckoutdate() + "'," + room.get(i).getBooking() + ");";
                String s = "update HOTEL set BOOKING = BOOKING+1 where ROOMNUMBER = '" + room.get(i).getRoomnumber() + "' and CHECKINDATE is NULL and CHECKOUTDATE is NULL";
                PreparedStatement st = JavaFXApplication4.getConnection().prepareStatement(q);
                PreparedStatement st1 = JavaFXApplication4.getConnection().prepareStatement(r);
                PreparedStatement st2 = JavaFXApplication4.getConnection().prepareStatement(s);
                st.executeUpdate();
                st1.executeUpdate();
                st2.executeUpdate();

                Parent root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
                Scene scene = new Scene(root);
                Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appstage.setScene(scene);
                appstage.show();

            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please enter information");
            alert.showAndWait();
        }

    }

}
