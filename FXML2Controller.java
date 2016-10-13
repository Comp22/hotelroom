/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 *
 */
public class FXML2Controller implements Initializable {

    @FXML
    private DatePicker CheckinDate;
    @FXML
    private DatePicker CheckoutDate;
    @FXML
    private CheckBox SingleRoom;
    @FXML
    private CheckBox DoubleRoom;
    @FXML
    private Button Search;
    @FXML
    private TableView<SqlRoom> tb;
    @FXML
    private TableColumn<SqlRoom, String> ROOMNUMBER;
    @FXML
    private TableColumn<SqlRoom, String> TYPE;
    @FXML
    private TableColumn<SqlRoom, String> COST;
    @FXML
    private ListView<String> lv = new ListView<String>();
    @FXML
    private Button but;
    @FXML
    private Button delete;
    @FXML
    private TextArea ta;
    private ObservableList<SqlRoom> data;
    protected static ObservableList<String> d = FXCollections.observableArrayList();
    protected static ArrayList<Room> rooms = new ArrayList<Room>();
    protected static ArrayList<Room> staticrooms = new ArrayList<Room>();

    public static int total = 0;
    private ArrayList<String> roomnumber = new ArrayList<String>();

// Add some action (in Java 8 lambda syntax style).
// Add the DatePicker to the Stage.
    public void handle() {
        SearchRooms();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void addtolist() {

        try {
            ObservableList<SqlRoom> a = tb.getSelectionModel().getSelectedItems();
            for (int i = 0; i < a.size(); i++) {
                int cost = Integer.parseInt(a.get(i).getCost());
                long totalCost = totalcost(CheckinDate.getValue().toString(), CheckoutDate.getValue().toString(), cost);
                int TotalCost = (int)totalCost;
                Room room = new Room(a.get(i).getRoomnumber(), a.get(i).getCost(), Integer.toString((int) totalCost), a.get(i).getBedtype(), CheckinDate.getValue().toString(), CheckoutDate.getValue().toString(), 1);
                if (roomnumber.contains(room.getRoomnumber()) == false) {
                    total += TotalCost;

                    rooms.add(room);
                    roomnumber.add(room.getRoomnumber());
                    d.add(a.get(i).getRoomnumber() + "   " + room.getCheckindate().toString() + " " + room.getCheckoutdate().toString() + " " + room.getTotalcost());

                    ta.setText("Total cost: " + total);
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Room already there!");
                    alert.showAndWait();
                }

            }
            lv.setItems(d);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No item to add!");
            alert.showAndWait();
        }

    }

    public void handledelete() {
        try {
            String stringroom = lv.getSelectionModel().getSelectedItem();
            String[] parts = stringroom.split(" ");
            Iterator<Room> iter = rooms.iterator();

            while (iter.hasNext()) {
                Room str = iter.next();
                if (parts[0].equals(str.getRoomnumber())) {
                    iter.remove();
                }
                d.remove(stringroom);
            }
            Iterator<String> iter1 = roomnumber.iterator();
            while (iter1.hasNext()) {
                String rn = iter1.next();
                if (rn.equals(parts[0])) {
                    iter1.remove();
                }
            }
            total = total - Integer.parseInt(parts[5]);
            ta.setText("Total cost: " + total);

            lv.setItems(d);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("There is no item on the list to delete");
            alert.showAndWait();
        }

    }
   
    public long totalcost(String ci, String co, int cost) {
        Calendar myCalendar = new GregorianCalendar(Integer.parseInt(co.substring(0, 4)), Integer.parseInt(co.substring(5, 7)), Integer.parseInt(co.substring(8)));
        Calendar myCalendar2 = new GregorianCalendar(Integer.parseInt(ci.substring(0, 4)), Integer.parseInt(ci.substring(5, 7)), Integer.parseInt(ci.substring(8)));

        Date myDate = myCalendar.getTime();
        System.out.println(myDate.toString());
        Date myDate2 = myCalendar2.getTime();
        System.out.println(myDate2.toString());

        long totalcost = (long) cost * (getDifferenceDays(myDate2, myDate));
        return totalcost;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void checkout(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
        Scene scene = new Scene(root);
        Stage appstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appstage.setScene(scene);
        appstage.show();
    }

    public void SearchRooms() {

        try {

            String q = "";
            if (SingleRoom.isSelected() == true && DoubleRoom.isSelected() == false) {
                q = "SELECT DISTINCT ROOMNUMBER,COST,BEDTYPE FROM HOTEL WHERE ((CHECKINDATE >" + "'" + CheckinDate.getValue().toString() + "'" + " AND CHECKINDATE >" + "'" + CheckoutDate.getValue().toString() + "')" + "OR" + "('" + CheckinDate.getValue().toString() + "'" + ">CHECKOUTDATE AND " + "'" + CheckoutDate.getValue().toString() + "'>CHECKOUTDATE) OR (CHECKINDATE = 'NULL' AND CHECKOUTDATE = 'NULL')) AND (BEDTYPE = 'SINGLEBED' )";
            } else if (SingleRoom.isSelected() == false && DoubleRoom.isSelected() == true) {
                q = "SELECT DISTINCT ROOMNUMBER,COST,BEDTYPE FROM HOTEL WHERE ((CHECKINDATE >" + "'" + CheckinDate.getValue().toString() + "'" + " AND CHECKINDATE >" + "'" + CheckoutDate.getValue().toString() + "')" + "OR" + "('" + CheckinDate.getValue().toString() + "'" + ">CHECKOUTDATE AND " + "'" + CheckoutDate.getValue().toString() + "'>CHECKOUTDATE) OR (CHECKINDATE = 'NULL' AND CHECKOUTDATE = 'NULL')) AND (BEDTYPE = 'DOUBLEBED' )";

            } else if (SingleRoom.isSelected() == false && DoubleRoom.isSelected() == false) {
                q = "SELECT DISTINCT ROOMNUMBER,COST,BEDTYPE FROM HOTEL WHERE ((CHECKINDATE >" + "'" + CheckinDate.getValue().toString() + "'" + " AND CHECKINDATE >" + "'" + CheckoutDate.getValue().toString() + "')" + "OR" + "('" + CheckinDate.getValue().toString() + "'" + ">CHECKOUTDATE AND " + "'" + CheckoutDate.getValue().toString() + "'>CHECKOUTDATE) OR (CHECKINDATE = 'NULL' AND CHECKOUTDATE = 'NULL')) AND (BEDTYPE = 'DOUBLEBED' OR BEDTYPE = 'SINGLEBED') ";

            } else if (SingleRoom.isSelected() == true && DoubleRoom.isSelected() == true) {
                q = "SELECT DISTINCT ROOMNUMBER,COST,BEDTYPE FROM HOTEL WHERE ((CHECKINDATE >" + "'" + CheckinDate.getValue().toString() + "'" + " AND CHECKINDATE >" + "'" + CheckoutDate.getValue().toString() + "')" + "OR" + "('" + CheckinDate.getValue().toString() + "'" + ">CHECKOUTDATE AND " + "'" + CheckoutDate.getValue().toString() + "'>CHECKOUTDATE) OR (CHECKINDATE = 'NULL' AND CHECKOUTDATE = 'NULL')) AND (BEDTYPE = 'DOUBLEBED' OR BEDTYPE = 'SINGLEBED');";

            }
            PreparedStatement mystm = JavaFXApplication4.getConnection().prepareStatement(q);
            System.out.println(CheckinDate.getValue().toString());
            ResultSet myrs = mystm.executeQuery();

            data = FXCollections.observableArrayList();

            while (myrs.next()) {
                data.add(new SqlRoom(myrs.getString(1), myrs.getString(2), myrs.getString(3)));
            }
            ROOMNUMBER.setCellValueFactory(new PropertyValueFactory<>("roomnumber"));
            TYPE.setCellValueFactory(new PropertyValueFactory<>("bedtype"));
            COST.setCellValueFactory(new PropertyValueFactory<>("cost"));

            tb.setItems(null);
            tb.setItems(data);

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please pick a date");
            alert.showAndWait();
        }

    }

    
}
