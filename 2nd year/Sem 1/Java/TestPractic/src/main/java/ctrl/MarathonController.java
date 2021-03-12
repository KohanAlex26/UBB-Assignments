package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.MarathonRace;
import model.Registration;
import service.MarathonServices;
import service.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class MarathonController {


    //add MarathonRace
    @FXML
    private TextField name, type, distance;
    @FXML
    private DatePicker searchDate;
    @FXML
    private Text raceID;



    //all marathon Races
    @FXML
    private TableView<MarathonRace> marathons;



//    int ID, String personName, String phoneNumber,String address,int age, MarathonRace marathonRace
    //add registration
    @FXML
    private TextField personName, phoneNumber,address,age;





    //display registration By race
    @FXML
    private TextField raceIdSearch;
    @FXML
    private TableView<Registration> registrationByRace;



    //registration by age
    @FXML
    private TextField lowAgeSearch,highAgeSearch;
    @FXML
    private TableView<Registration> registrationByAge;





    //connection to services
    private MarathonServices marathonServices;

    public MarathonController(){
    }

    public void setService(MarathonServices service){
        this.marathonServices =service;
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


        searchDate.setConverter(converter);
        searchDate.setValue(LocalDate.now());
        searchDate.setEditable(false);

    }


    //String name, String type,String date,int distance
    @FXML
    public void addRaceHandler(ActionEvent actionEvent) {
        String name1=name.getText();
        String type1= type.getText();
        String date1= searchDate.getValue().format(dateFormatter);
        String distance1=distance.getText();
        if (checkString(name1)&&checkString(type1)&&checkString(date1)&&checkString(distance1)){
            try {
                int id = marathonServices.addRace(name1,type1,date1,Integer.parseInt(distance1));
                raceID.setText("MarathonRace identification number is " + id);
                showNotification("MarathonRace successfully added! ", Alert.AlertType.INFORMATION);
            }catch(ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }



    //    int ID, String personName, String phoneNumber,String address,int age, MarathonRace marathonRace
    //                carpets.getItems().remove(selectedRace);
    @FXML
    public void addRegistrationHandler(ActionEvent actionEvent) {
        //System.out.println("Add repaired button pressed ..."+repairDate.getValue().format(dateFormatter));
        int selectedRace= marathons.getSelectionModel().getSelectedIndex();
        if (selectedRace<0){
            showNotification("You must select a race first! ", Alert.AlertType.ERROR);
            return;
        }
        String personName1=personName.getText();
        String phoneNumber1= phoneNumber.getText();
        String address1= address.getText();
        String age1=age.getText();
        if (checkString(personName1)&&checkString(phoneNumber1)&&checkString(address1)&&checkString(age1)){
            try{
                int age2=Integer.parseInt(age1);
                MarathonRace marathonRace = marathons.getItems().get(selectedRace);

                if(marathonRace.getType().equals("kids"))
                {
                    if(age2>=6 && age2<=11)
                    {
                        marathonServices.addOrder(marathonRace,personName1,phoneNumber1,address1,Integer.parseInt(age1));
                        clearCarReservationFields();
                        showNotification("Registration successfully added! ", Alert.AlertType.INFORMATION);
                    }
                else
                {
                    showNotification("You must select a valid age first! ", Alert.AlertType.ERROR);
                    return;
                }

                }


                if(marathonRace.getType().equals("teens"))
                {
                    if(age2>=12 && age2<=17)
                    {marathonServices.addOrder(marathonRace,personName1,phoneNumber1,address1,Integer.parseInt(age1));
                    clearCarReservationFields();
                    showNotification("Registration successfully added! ", Alert.AlertType.INFORMATION);
                    }
                    else
                    {
                        showNotification("You must select a valid age first! ", Alert.AlertType.ERROR);
                        return;
                    }
                }


                if(marathonRace.getType().equals("adults"))
                {
                    if(age2>=18 && age2<=60) {
                        marathonServices.addOrder(marathonRace, personName1, phoneNumber1, address1, Integer.parseInt(age1));
                        clearCarReservationFields();
                        showNotification("Registration successfully added! ", Alert.AlertType.INFORMATION);
                    }
                    else
                    {
                        showNotification("You must select a valid age first! ", Alert.AlertType.ERROR);
                        return;
                    }
                }


                if(marathonRace.getType().equals("seniors"))
                {
                    if(age2>=61) {
                        marathonServices.addOrder(marathonRace, personName1, phoneNumber1, address1, Integer.parseInt(age1));
                        clearCarReservationFields();
                        showNotification("Registration successfully added! ", Alert.AlertType.INFORMATION);
                    }
                    else
                    {
                        showNotification("You must select a valid age first! ", Alert.AlertType.ERROR);
                        return;
                    }
                }


            }catch (NumberFormatException ex){
                showNotification("The price must be a number! ", Alert.AlertType.ERROR);
                return;
            }catch (ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }

    }
    @FXML
    public void searchAllRaces(ActionEvent actionEvent) {
        List<MarathonRace> results= marathonServices.getRaces();
        marathons.getItems().clear();
        marathons.getItems().addAll(results);
    }
    @FXML
    public void searchRegistrationByRace(ActionEvent actionEvent) {
        String registrationid= raceIdSearch.getText();
        if (!checkString(registrationid)) {
            showNotification("You must introduce a registration id! ", Alert.AlertType.ERROR);
            return;
        }
        int registrationId1=Integer.parseInt(registrationid);
        List<Registration> results= marathonServices.getRegistrationByRace(registrationId1);
        registrationByRace.getItems().clear();
        registrationByRace.getItems().addAll(results);
    }

    @FXML
    public void searchRegistrationByAge(ActionEvent actionEvent) {
        String lowAgeSearch1= lowAgeSearch.getText();
        String highAgeSearch1=highAgeSearch.getText();
        if (!checkString(lowAgeSearch1) || !checkString(highAgeSearch1)) {
            showNotification("You must introduce a registration id! ", Alert.AlertType.ERROR);
            return;
        }
        int lowAgeSearch2=Integer.parseInt(lowAgeSearch1);
        int highAgeSearch2=Integer.parseInt(highAgeSearch1);
        List<Registration> results= marathonServices.getRegistrationsByAge(lowAgeSearch2,highAgeSearch2);
        registrationByAge.getItems().clear();
        registrationByAge.getItems().addAll(results);
    }


    private void clearCarReservationFields() {
        personName.setText("");
        phoneNumber.setText("");
        address.setText("");
        age.setText("");
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        name.setText("");
        type.setText("");
        distance.setText("");
        raceID.setText("");
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

}
