import ctrl.CarShopController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repo.CarFileRepository;
import repo.CarRepository;
import repo.CarReservationFileRepository;
import service.CarServices;
import service.ServicesException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("example.fxml"));
            Parent root = loader.load();
            CarShopController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Car Shop");
            primaryStage.show();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static CarServices getService() throws ServicesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("CarShop.properties"));
            String requestFileName=properties.getProperty("CarFile");
            if (requestFileName==null){
                requestFileName="Car.txt";
                System.err.println("Requests file not found. Using default "+requestFileName);
            }
            String formsFileName=properties.getProperty("ReservationFile");
            if (formsFileName==null){
                formsFileName="CarReservation.txt";
                System.err.println("CarReservations file not found. Using default "+formsFileName);
            }
            CarRepository crrRepo = new CarFileRepository(requestFileName);
            CarReservationFileRepository crfRepo = new CarReservationFileRepository(formsFileName, crrRepo);
            return new CarServices(crrRepo, crfRepo);
        }catch (IOException ex){
            throw new ServicesException("Error starting app "+ex);
        }
    }
}
