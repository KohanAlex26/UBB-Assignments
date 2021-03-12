package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.Tractore;
import model.Order;
import service.CarpetServices;
import service.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CarpetShopController {

    //add Tractore
    @FXML
    private TextField name,dimension,color,price;
    @FXML
    private Text carpetID;




    //all Carpets
    @FXML
    private TableView<Tractore> carpets;

    //add Order
    @FXML
    private TextField personName,personAddress;

    @FXML
    private DatePicker orderedDate;




    //display orders of a certain carpet
    @FXML
    private TextField carpetIdSearch;
    @FXML
    private TableView<Order> ordersOfACertainCarpet;



    //all carpets on a certain date
    @FXML
    private DatePicker searchDate;
    @FXML
    private TableView<Order> ordersOfDate;




    //all carpets on a certain date2
    @FXML
    private DatePicker searchDate2;
    @FXML
    private TableView<Tractore> ordersOfDate2;



    //connection to services
    private CarpetServices carpetServices;

    public CarpetShopController(){
    }

    public void setService(CarpetServices service){
        this.carpetServices =service;
    }

    @FXML
    public void initialize(){
        StringConverter<LocalDate> converter=new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        };

        orderedDate.setConverter(converter);
        orderedDate.setValue(LocalDate.now());
        orderedDate.setEditable(false);

        searchDate.setConverter(converter);
        searchDate.setValue(LocalDate.now());
        searchDate.setEditable(false);

    }

    @FXML
    public void addCarpetHandler(ActionEvent actionEvent) {
        String name1=name.getText();
        String dimension1=dimension.getText();
        String color1=color.getText();
        String price1=price.getText();
        if (checkString(name1)&&checkString(dimension1)&&checkString(color1)&&checkString(price1)){
            try {
                int dimension2=Integer.parseInt(dimension1);
                int price2=Integer.parseInt(price1);
                int id = carpetServices.addCarpet(name1,dimension2,color1,price2);
                carpetID.setText("Tractore identification number is " + id);
                showNotification("Tractore successfully added! ", Alert.AlertType.INFORMATION);
            }catch(ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void addOrdersHandler(ActionEvent actionEvent) {
        //System.out.println("Add repaired button pressed ..."+repairDate.getValue().format(dateFormatter));
        int selectedCarpet= carpets.getSelectionModel().getSelectedIndex();
        if (selectedCarpet<0){
            showNotification("You must select a car first! ", Alert.AlertType.ERROR);
            return;
        }
        String name=personName.getText();
        String address=personAddress.getText();
        String date= orderedDate.getValue().format(dateFormatter);
        if (checkString(name)&&checkString(address)&&checkString(date)){
            try{
                Tractore tractore = carpets.getItems().get(selectedCarpet);
                carpetServices.addOrder(tractore,name,address,date);
//                carpets.getItems().remove(selectedCarpet);
                clearCarReservationFields();
                showNotification("Order successfully added! ", Alert.AlertType.INFORMATION);
            }catch (NumberFormatException ex){
                showNotification("The price must be a number! ", Alert.AlertType.ERROR);
                return;
            }catch (ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }

    }

    @FXML
    public void searchAllCarpetsHandler(ActionEvent actionEvent) {
        List<Tractore> results= carpetServices.getCarpets();
        carpets.getItems().clear();
        carpets.getItems().addAll(results);
    }
    @FXML
    public void searchOrdersByCarpet(ActionEvent actionEvent) {
        String carpetid= carpetIdSearch.getText();
        if (!checkString(carpetid)) {
            showNotification("You must introduce a carpet id! ", Alert.AlertType.ERROR);
            return;
        }
        int carpetId1=Integer.parseInt(carpetid);
        List<Order> results= carpetServices.getOrdersByCarpet(carpetId1);
        ordersOfACertainCarpet.getItems().clear();
        ordersOfACertainCarpet.getItems().addAll(results);
    }
    @FXML
    public void searchOrdersByDate(ActionEvent actionEvent) {
        String date1= searchDate.getValue().format(dateFormatter);
        if (!checkString(date1)) {
            showNotification("You must introduce a carpet id! ", Alert.AlertType.ERROR);
            return;
        }
        List<Order> results= carpetServices.getOrdersByDate(date1);
        ordersOfDate.getItems().clear();
        ordersOfDate.getItems().addAll(results);
    }

    private void clearCarReservationFields() {
        personName.setText("");
        personAddress.setText("");
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        name.setText("");
        dimension.setText("");
        color.setText("");
        price.setText("");
        carpetID.setText("");
    }
    private void showNotification(String message, Alert.AlertType type){
        Alert alert=new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");



    private boolean checkString(String s){
        return s==null || s.isEmpty()? false:true;
    }


    @FXML
    public void searchOrdersByDate2(ActionEvent actionEvent) {
        String date1= searchDate2.getValue().format(dateFormatter);
        if (!checkString(date1)) {
            showNotification("You must introduce a carpet id! ", Alert.AlertType.ERROR);
            return;
        }
        List<Tractore> results= carpetServices.getCarpetsByDate(date1);
        ordersOfDate2.getItems().clear();
        ordersOfDate2.getItems().addAll(results);
    }
}
