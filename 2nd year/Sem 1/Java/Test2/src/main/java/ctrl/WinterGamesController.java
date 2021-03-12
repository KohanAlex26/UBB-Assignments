package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.Enrolment;
import model.WinterGame;
import service.WinterGamesServices;
import service.ServicesException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class WinterGamesController {

    //add WinterGame

    @FXML
    private TextField name,type,minAge,maxAge;
    @FXML
    private DatePicker gameDate;
    @FXML
    private Text carpetID;





    //add Enrolment
    @FXML
    private TextField personName,parentName,age;


    //all Games
    @FXML
    private TableView<WinterGame> games;




    //displaying all  enrollments  to a certain winter game;
    @FXML
    private TextField gameIdSearch;
    @FXML
    private TableView<Enrolment> enrolmentsToACertainGame;


    //displaying all winter games to which a child has enrolled
    @FXML
    private TextField childName;
    @FXML
    private TableView<WinterGame> winterGamesByChild;






    //connection to services
    private WinterGamesServices winterGamesServices;

    public WinterGamesController(){
    }

    public void setService(WinterGamesServices service){
        this.winterGamesServices =service;
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

        gameDate.setConverter(converter);
        gameDate.setValue(LocalDate.now());
        gameDate.setEditable(false);


    }

    @FXML
    public void addWinterGameHandler(ActionEvent actionEvent) {

        String name1=name.getText();
        String type1=type.getText();
        String minAge1=minAge.getText();
        String maxAge1=maxAge.getText();
        String date= gameDate.getValue().format(dateFormatter);
        if (checkString(name1)&&checkString(type1)&&checkString(minAge1)&&checkString(maxAge1)){
            try {
                int minAge2=Integer.parseInt(minAge1);
                int maxAge2=Integer.parseInt(maxAge1);
                int id = winterGamesServices.addGame(name1,type1,date,minAge2,maxAge2);
                carpetID.setText("WinterGame identification number is " + id);
                showNotification("WinterGame successfully added! ", Alert.AlertType.INFORMATION);
            }catch(ServicesException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }


    @FXML
    public void addEnrolmentHandler(ActionEvent actionEvent) {
        //System.out.println("Add repaired button pressed ..."+repairDate.getValue().format(dateFormatter));
        int selectedGame= games.getSelectionModel().getSelectedIndex();
        if (selectedGame<0){
            showNotification("You must select a game first! ", Alert.AlertType.ERROR);
            return;
        }
        String personName1=personName.getText();
        String parentName1=parentName.getText();
        String age1= age.getText();
        if (checkString(personName1)&&checkString(parentName1)&&checkString(age1)){
            try{
                int age2=Integer.parseInt(age1);
                WinterGame winterGame = games.getItems().get(selectedGame);
                if(age2>winterGame.getMinAge()&&age2<winterGame.getMaxAge())
                {
                    winterGamesServices.addEnrolment(personName1, parentName1, age2, winterGame);
//                carpets.getItems().remove(selectedGame);
                    clearEnrolmentFields();
                    showNotification("Enrolment successfully added! ", Alert.AlertType.INFORMATION);
                }
                else
                {
                    showNotification("You must select a valid year! ", Alert.AlertType.ERROR);
                    return;
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
    public void searchAllGamesHandler(ActionEvent actionEvent) {
        List<WinterGame> results= winterGamesServices.getGames();
        games.getItems().clear();
        games.getItems().addAll(results);
    }
    @FXML
    public void searchEnrolmentsToAGame(ActionEvent actionEvent) {
        String gameId= gameIdSearch.getText();
        if (!checkString(gameId)) {
            showNotification("You must introduce a game id! ", Alert.AlertType.ERROR);
            return;
        }
        int gameId1=Integer.parseInt(gameId);
        List<Enrolment> results= winterGamesServices.getEnrolmentsByGame(gameId1);
        enrolmentsToACertainGame.getItems().clear();
        enrolmentsToACertainGame.getItems().addAll(results);
    }
    @FXML
    public void searchGamesByChild(ActionEvent actionEvent) {
        String childName1= childName.getText();
        if (!checkString(childName1)) {
            showNotification("You must introduce a child name! ", Alert.AlertType.ERROR);
            return;
        }
        List<WinterGame> results= winterGamesServices.getGamesByChild(childName1);
        winterGamesByChild.getItems().clear();
        winterGamesByChild.getItems().addAll(results);
    }


    private void clearEnrolmentFields() {
        personName.setText("");
        parentName.setText("");
        age.setText("");
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        name.setText("");
        type.setText("");
        minAge.setText("");
        maxAge.setText("");
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

}
