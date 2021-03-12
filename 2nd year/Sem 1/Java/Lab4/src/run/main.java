package run;

import Repository.*;
import model.CarReservation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class main {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("CarReservation.properties"));
            String requestFileName=properties.getProperty("RequestsFile");

            if (requestFileName==null){ //the property does not exist in the file
                requestFileName="test.txt";
                System.err.println("Requests file not found. Using default "+requestFileName);
            }
            Repository<Integer, CarReservation> crrRepo = new ReservationRepositoryFile(requestFileName);
            Iterable<CarReservation> result = crrRepo.findall(); //result returned from some method.
            for(CarReservation carReservation: result){
                System.out.println(carReservation);
            }
        }catch (IOException ex){
            System.err.println("Error reading the configuration file"+ex);
        }
    }

}
