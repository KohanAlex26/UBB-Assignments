import ctrl.MarathonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repo.MarathonRaceFileRepository;
import repo.MarathonRaceRepository;
import repo.RegistrationFileRepository;
import service.MarathonServices;
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
            MarathonController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("MarathonRace Shop");
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

    static MarathonServices getService() throws ServicesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("Race.properties"));
            String requestFileName=properties.getProperty("RaceFile");
            if (requestFileName==null){
                requestFileName="Race.txt";
                System.err.println("Requests file not found. Using default "+requestFileName);
            }
            String formsFileName=properties.getProperty("RegistrationFile");
            if (formsFileName==null){
                formsFileName="Registration.txt";
                System.err.println("CarReservations file not found. Using default "+formsFileName);
            }
            MarathonRaceRepository crrRepo = new MarathonRaceFileRepository(requestFileName);
            RegistrationFileRepository crfRepo = new RegistrationFileRepository(formsFileName, crrRepo);
            return new MarathonServices(crrRepo, crfRepo);
        }catch (IOException ex){
            throw new ServicesException("Error starting app "+ex);
        }
    }
}
