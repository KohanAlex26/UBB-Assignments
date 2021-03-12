import ctrl.CarpetShopController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repo.tractoareFileRepository;
import repo.tractoareRepository;
import repo.OrderFileRepository;
import service.CarpetServices;
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
            CarpetShopController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tractore Shop");
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

    static CarpetServices getService() throws ServicesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("CarpetShop.properties"));
            String requestFileName=properties.getProperty("CarpetFile");
            if (requestFileName==null){
                requestFileName="Tractore.txt";
                System.err.println("Requests file not found. Using default "+requestFileName);
            }
            String formsFileName=properties.getProperty("OrderFile");
            if (formsFileName==null){
                formsFileName="Order.txt";
                System.err.println("CarReservations file not found. Using default "+formsFileName);
            }
            tractoareRepository crrRepo = new tractoareFileRepository(requestFileName);
            OrderFileRepository crfRepo = new OrderFileRepository(formsFileName, crrRepo);
            return new CarpetServices(crrRepo, crfRepo);
        }catch (IOException ex){
            throw new ServicesException("Error starting app "+ex);
        }
    }
}
