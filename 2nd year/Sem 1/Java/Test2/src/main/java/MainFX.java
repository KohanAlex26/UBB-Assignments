import ctrl.WinterGamesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repo.GameFileRepository;
import repo.GameRepository;
import repo.EnrolmentFileRepository;
import service.WinterGamesServices;
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
            WinterGamesController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WinterGame Shop");
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

    static WinterGamesServices getService() throws ServicesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("WinterGames.properties"));
            String requestFileName=properties.getProperty("GameFile");
            if (requestFileName==null){
                requestFileName="Games.txt";
                System.err.println("Requests file not found. Using default "+requestFileName);
            }
            String formsFileName=properties.getProperty("EnrolmentFile");
            if (formsFileName==null){
                formsFileName="Enrolments.txt";
                System.err.println("CarReservations file not found. Using default "+formsFileName);
            }
            GameRepository crrRepo = new GameFileRepository(requestFileName);
            EnrolmentFileRepository crfRepo = new EnrolmentFileRepository(formsFileName, crrRepo);
            return new WinterGamesServices(crrRepo, crfRepo);
        }catch (IOException ex){
            throw new ServicesException("Error starting app "+ex);
        }
    }
}
