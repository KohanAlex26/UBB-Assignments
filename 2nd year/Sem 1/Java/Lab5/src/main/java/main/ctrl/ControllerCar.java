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
import main.service.CarService;

import java.util.List;

public class ControllerCar {
    @FXML
    private TableView<Car> table;
    @FXML
    private TextField brandField, modelField,seatsField;
    @FXML
    private Text idLabel;

    private CarService service;
    private ObservableList<Car> masiniList = FXCollections.observableArrayList();
    public ControllerCar(){
    }
    @FXML
    public void initialize() {
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldItem, newItem) -> showMasina(newItem));
        table.setItems(masiniList);
    }


   private void showMasina(Car newItem){
   if (newItem==null)
   clearFields();
   else{
    idLabel.setText(""+newItem.getId());
    brandField.setText(newItem.getBrand());
    modelField.setText(newItem.getModel());
    seatsField.setText(""+newItem.getSeats());
    }
   }

    private void clearFields() {
        idLabel.setText("");
        brandField.setText("");
        modelField.setText("");
        seatsField.setText("");
       /* firmaSField.setText("");
        modelSField.setText("");
        anSField.setText("");*/
    }


    public void setService(CarService serv){
        this.service=serv;
        List<Car> lm=serv.getAll();
        masiniList.addAll(lm);
    }

    @FXML
    private void add(ActionEvent e){
        String brand=brandField.getText();
        String model=modelField.getText();
        String seats=seatsField.getText();
        if ("".equals(brand)||"".equals(model)||"".equals(seats)){
            showErrorMessage("Trebuie sa completati toate  campurile!");
            return;
        }
        try{
            int an=Integer.parseInt(seats);
            Car m=new Car(brand,model,an);
            m=service.adauga(m);
            masiniList.add(m);
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
            showErrorMessage("Trebuie sa selectati o masina din tabel!");
            return;
        }else{
            masiniList.remove(selectedIndex);
        }
    }
    void showErrorMessage(String text){
        Alert message=new Alert(Alert.AlertType.ERROR);
        message.setTitle("Mesaj eroare");
        message.setContentText(text);
        message.showAndWait();
    }



    /*public ControllerCar(CarRepository cr){
        this.cr=cr;
    }
    public void controllerBrandOfCars()
    {
        cr.brandOfCars();
    }
    public void controllerModelOfCertainBrand(){
        cr.modelOfCertainBrand();
    }
    public void controllerModelWithAGivenNumber()
    {
        cr.modelWithAGivenNumber();
    }
    public void controllerLessThanAGivenNumber(){
        cr.lessThanAGivenNumber();
    }
    public void controllerColorOfACertainCar()
    {
        cr.colorOfACertainCar();
    }
    public void controllerBrandOfACertainModel()
    {
        cr.brandOfACertainModel();
    }
    public void controllerModelBuild(){
        cr.modelBuild();
    }*/
}
