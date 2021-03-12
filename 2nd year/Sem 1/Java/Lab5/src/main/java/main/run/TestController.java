//package main.run;
//
//import main.Controller.ControllerCar;
//import main.Controller.ControllerCarReservatioRepository;
//import main.Repository.CarRepository;
//import main.Repository.CarReservationRepository;
//
//public class TestController {
//    public static void main(String[] args) {
//        CarRepository cr=new CarRepository();
//        ControllerCar cc=new ControllerCar(cr);
//        cc.controllerBrandOfCars();
//
//
//        CarReservationRepository crr=new CarReservationRepository();
//        ControllerCarReservatioRepository controller=new ControllerCarReservatioRepository(crr);
//        controller.add(1,"a","a","a","a","b","a","a",1,1,false);
//        controller.update(1,1,"b","b","b","b","b","b","b",1,1,false);
//        controller.delete(1,"b","b","b","b","b","b","b",1,1,false);
//        controller.controllerOwnerNameMap();
//    }
//}
