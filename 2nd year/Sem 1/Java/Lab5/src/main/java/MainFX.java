import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import main.ctrl.ControllerCar;
import main.ctrl.ControllerCarReservation;
import main.model.CarReservation;
import main.repo.CarRepo;
import main.repo.CarReservationRepo;
import main.service.CarReservationService;
import main.service.CarService;

import java.io.IOException;


public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("example.fxml"));
            root = loader.load();
            ControllerCar ctrl = loader.getController();
            ctrl.setService(getService());
            /*ControllerCarReservation ctrl2=loader.getController();
            ctrl2.setService(getService2());*/
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Cars");
            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    static CarService getService(){
        CarRepo mrepo=new CarRepo();
        CarService mserv=new CarService(mrepo);
        return mserv;
    }
    static CarReservationService getService2(){
        CarReservationRepo mrepo=new CarReservationRepo();
        CarReservationService mserv=new CarReservationService(mrepo);
        return mserv;
    }
}
