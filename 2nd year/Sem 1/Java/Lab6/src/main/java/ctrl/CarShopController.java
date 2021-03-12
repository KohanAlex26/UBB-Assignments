package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.Car;
import model.carStatus;
import service.CarServices;
import service.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CarShopController {

    //for Car
    @FXML
    private TextField model, color,brand,seats;
    @FXML
    private Text carID;
    @FXML
    private TextField carBrand;
    @FXML
    private TextField carColor;
    @FXML
    private TextField carSeats;

    //update Car
    @FXML
    private TextField modelUpdate, colorUpdate,brandUpdate,seatsUpdate;

    //for CarReservation
    @FXML
    private TextField carModel,ownerName,ownerAddress,phoneNumber;


    @FXML
    private DatePicker reservedDate;


    @FXML
    private TableView<Car> carsWithSeats;
    @FXML
    private TableView<Car> carsWithColor;

    @FXML
    private TableView<Car> carsWithBrand;

    @FXML
    private TableView<Car> carNotReserved;
    @FXML
    private TableView<Car> cars;

    @FXML
    private TableView<Car> carReserved;

    @FXML
    private ChoiceBox<carStatus> carStat;

    //connection to services
    private CarServices carServices;

    public CarShopController(){
    }

    public void setService(CarServices service){
        this.carServices=service;
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

/*        requestDate.setConverter(converter);
        requestDate.setValue(LocalDate.now());
        requestDate.setEditable(false);*/

        reservedDate.setConverter(converter);
        reservedDate.setValue(LocalDate.now());
        reservedDate.setEditable(false);

        carStat.getItems().add(carStatus.New);
        carStat.getItems().add(carStatus.Reserved);
        carStat.getItems().add(carStatus.Cancelled);
        //you may use the method values on an enum to obtain all the possible values
        //requestStatus.getItems().addAll(RequestStatus.values());
        carStat.getSelectionModel().selectFirst();

    }

    @FXML
    public void addCarHandler(ActionEvent actionEvent) {
        String model1=model.getText();
        String color1=color.getText();
        String brand1=brand.getText();
        String seats1=seats.getText();
        if (checkString(model1)&&checkString(color1)&&checkString(brand1)&&checkString(seats1)){
            try {
                int seats2=Integer.parseInt(seats1);
                int id = carServices.addCar(model1,color1,brand1,seats2);
                carID.setText("Car identification number is " + id);
                showNotification("Car successfully added! ", Alert.AlertType.INFORMATION);
            }catch(ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void deleteCarHandler(ActionEvent actionEvent) {
        int selectedCar=cars.getSelectionModel().getSelectedIndex();
        if (selectedCar<0){
            showNotification("You must select a car first! ", Alert.AlertType.ERROR);
            return;
        }
            try {
                Car car=cars.getItems().get(selectedCar);
                carServices.deleteCar(car.getID());
                cars.getItems().remove(selectedCar);
                showNotification("Car successfully deleted! ", Alert.AlertType.INFORMATION);
            }
            catch(ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
    }

    @FXML
    public void updateCarHandler(ActionEvent actionEvent) {
        int selectedCar=cars.getSelectionModel().getSelectedIndex();
        if (selectedCar<0){
            showNotification("You must select a car first! ", Alert.AlertType.ERROR);
            return;
        }
        String model1=modelUpdate.getText();
        String color1=colorUpdate.getText();
        String brand1=brandUpdate.getText();
        String seats1=seatsUpdate.getText();
        if (checkString(model1)&&checkString(color1)&&checkString(brand1)&&checkString(seats1)){
            try {
                Car car=cars.getItems().get(selectedCar);
                int seats2=Integer.parseInt(seats1);
                carServices.updateCar(car.getID(),model1,color1,brand1,seats2);
                showNotification("Car successfully updated! ", Alert.AlertType.INFORMATION);
            }catch(ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void addCarReservationHandler(ActionEvent actionEvent) {
        //System.out.println("Add repaired button pressed ..."+repairDate.getValue().format(dateFormatter));
        int selectedCar=carNotReserved.getSelectionModel().getSelectedIndex();
        if (selectedCar<0){
            showNotification("You must select a car first! ", Alert.AlertType.ERROR);
            return;
        }
        String name=ownerName.getText();
        String address=ownerAddress.getText();
        String phone=phoneNumber.getText();
        String date=reservedDate.getValue().format(dateFormatter);
        if (checkString(name)&&checkString(address)&&checkString(phone)&& checkString(date)){
            try{
                Car car=carNotReserved.getItems().get(selectedCar);
                carServices.addCarReservation(car,address,phone,date,name);
                carNotReserved.getItems().remove(selectedCar);
                clearCarReservationFields();
                showNotification("CarReservation successfully added! ", Alert.AlertType.INFORMATION);
            }catch (NumberFormatException ex){
                showNotification("The price must be a number! ", Alert.AlertType.ERROR);
                return;
            }catch (ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }

    }

    @FXML
    public void searchAllCarsHandler(ActionEvent actionEvent) {
        List<Car> results=carServices.getCarByStatus(carStatus.New);
        cars.getItems().clear();
        cars.getItems().addAll(results);
    }
    @FXML
    public void searchModelCarHandler(ActionEvent actionEvent) {
        String searchModel=carModel.getText();
        if (!checkString(searchModel)) {
            showNotification("You must introduce a model! ", Alert.AlertType.ERROR);
            return;
        }
        List<Car> results=carServices.getCarsNotReservedByModel(searchModel);
        carNotReserved.getItems().clear();
        carNotReserved.getItems().addAll(results);

    }

    @FXML
    public void searchBrandCarHandler(ActionEvent actionEvent) {
        String searchBrand=carBrand.getText();
        if (!checkString(searchBrand)) {
            showNotification("You must introduce a brand! ", Alert.AlertType.ERROR);
            return;
        }
        List<Car> results=carServices.getCarsWithCertainBrand(searchBrand);
        carsWithBrand.getItems().clear();
        carsWithBrand.getItems().addAll(results);

    }
    @FXML
    public void searchColorCarHandler(ActionEvent actionEvent) {
        String searchColor=carColor.getText();
        if (!checkString(searchColor)) {
            showNotification("You must introduce a color! ", Alert.AlertType.ERROR);
            return;
        }
        List<Car> results=carServices.getCarsWithCertainColor(searchColor);
        carsWithColor.getItems().clear();
        carsWithColor.getItems().addAll(results);

    }
    @FXML
    public void searchSeatsCarHandler(ActionEvent actionEvent) {
        int searchSeats=Integer.parseInt(carSeats.getText());

        List<Car> results=carServices.getCarsWithCertainSeats(searchSeats);
        carsWithSeats.getItems().clear();
        carsWithSeats.getItems().addAll(results);

    }
    private void clearCarReservationFields() {
        ownerName.setText("");
        ownerAddress.setText("");
        phoneNumber.setText("");
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        model.setText("");
        color.setText("");
        brand.setText("");
        seats.setText("");
        carID.setText("");
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
    public void carReservedHandler(ActionEvent actionEvent) {
        carStatus selected= carStat.getSelectionModel().getSelectedItem();
        List<Car> results=carServices.getCarByStatus(selected);
        carReserved.getItems().clear();
        carReserved.getItems().addAll(results);

    }
}
