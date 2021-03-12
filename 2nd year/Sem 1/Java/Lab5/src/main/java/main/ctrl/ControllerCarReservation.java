package main.ctrl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.model.Car;
import main.repo.CarReservationRepository;
import main.model.CarReservation;
import main.service.CarReservationService;
import main.service.CarService;

import java.util.List;


public class ControllerCarReservation {

    @FXML
    private TableView<CarReservation> table;
    @FXML
    private TextField ownerField,phoneField,dateField;
    @FXML
    private Text idLabel;

    private CarReservationService service;
    private ObservableList<CarReservation> CarReservationList = FXCollections.observableArrayList();
    public ControllerCarReservation(){
    }
    @FXML
    public void initialize() {
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> showCarReservations(newItem));
        table.setItems(CarReservationList);
    }


    private void showCarReservations(CarReservation newItem){
        if (newItem==null)
            clearFields();
        else{
            idLabel.setText(""+newItem.getId());
            ownerField.setText(newItem.getOwnerName());
            phoneField.setText(newItem.getPhoneNumber());
            dateField.setText(""+newItem.getDate());
        }
    }

    private void clearFields() {
        idLabel.setText("");
        ownerField.setText("");
        phoneField.setText("");
        dateField.setText("");
       /* firmaSField.setText("");
        modelSField.setText("");
        anSField.setText("");*/
    }


    public void setService(CarReservationService serv){
        this.service=serv;
        List<CarReservation> lm=serv.getAll();
        CarReservationList.addAll(lm);
    }

    @FXML
    private void add(ActionEvent e){
        String owner=ownerField.getText();
        String phone=phoneField.getText();
        String date=dateField.getText();
        if ("".equals(owner)||"".equals(phone)||"".equals(date)){
            showErrorMessage("Trebuie sa completati toate  campurile!");
            return;
        }
        try{
            CarReservation m=new CarReservation(owner,phone,date);
            m=service.adauga(m);
            CarReservationList.add(m);
        }catch (NumberFormatException ex){
            showErrorMessage("Eroare la introducerea datelor "+ex);
        }
    }

    @FXML
    private void clear(ActionEvent e){
        clearFields();
        table.getSelectionModel().clearSelection();
    }

    @FXML
    private void delete(ActionEvent e){
        int selectedIndex=table.getSelectionModel().getSelectedIndex();
        if (selectedIndex<0) {
            showErrorMessage("Trebuie sa selectati o rezervare din tabel!");
            return;
        }else{
            CarReservationList.remove(selectedIndex);
        }
    }
    void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }
    /*private CarReservationRepository crr;
    public ControllerCarReservatioRepository(){
    }
    public ControllerCarReservatioRepository(CarReservationRepository crr){
        this.crr=crr;
    }
    public void add(int ID,String ownerName,String ownerAddress,String phoneNumber,String date, String model,String color,String brand,int seats,int ID1,boolean isValid)
    {
        CarReservation cr=new CarReservation(ID,ownerName,ownerAddress,phoneNumber,new Car(model,color,brand,seats,ID1),date,isValid);
        crr.add(cr);
    }
    public boolean delete(int ID,String ownerName,String ownerAddress,String phoneNumber,String date, String model,String color,String brand,int seats,int ID1,boolean isValid)
    {
        CarReservation cr=new CarReservation(ID,ownerName,ownerAddress,phoneNumber,new Car(model,color,brand,seats,ID1),date,isValid);
        crr.delete(cr);
        return true;
    }
    public void update(int updateId,int ID,String ownerName,String ownerAddress,String phoneNumber,String date, String model,String color,String brand,int seats,int ID1,boolean isValid)
    {
        CarReservation cr=new CarReservation(ID,ownerName,ownerAddress,phoneNumber,new Car(model,color,brand,seats,ID1),date,isValid);
        crr.update(updateId,cr);
    }
    public CarReservation findById(int Id)
    {
        return crr.findbyId(Id);
    }
    public Iterable<CarReservation> findAll()
    {
        return crr.findall();
    }
    public void controllerOwnerNameMap()
    {
        crr.ownerNameMap();
    }
    public void controllerCertainOwnerAdress(){
        crr.certainOwnerAdress();
    }
    public void controllerHigherThanAGivenNumber()
    {
        crr.higherThanAGivenNumber();
    }
    public void controllerLessThanAGivenNumber(){
        crr.lessThanAGivenNumber();
    }
    public void controllerDateOfACertainCarr()
    {
     crr.dateOfACertainCar();
    }
    public void controllerOwnerNameOfACertainId()
    {
        crr.ownerNameOfACertainId();
    }
    public void controllerNumberOfAddress(){
     crr.numberOfAddress();
    }*/
}

