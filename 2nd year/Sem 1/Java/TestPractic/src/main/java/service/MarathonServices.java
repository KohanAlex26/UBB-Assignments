package service;

import model.MarathonRace;
import model.Registration;
import model.marathonRaceStatus;
import repo.MarathonRaceRepository;
import repo.RegistrationRepository;
import repo.RepositoryException;

import java.util.List;

public class MarathonServices {

    private RegistrationRepository registrationRepository;

    private MarathonRaceRepository marathonRaceRepository;

    public MarathonServices(MarathonRaceRepository marathonRaceRepository, RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
        this.marathonRaceRepository = marathonRaceRepository;
    }


    public int addRace(String name, String type, String date, int distance) throws ServicesException{
        try {
            MarathonRace marathonRace = new MarathonRace(name,type,date,distance);
            MarathonRace newMarathonRace = marathonRaceRepository.add(marathonRace);
            return newMarathonRace.getID();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding car"+ex);
        }
    }

//    public void deleteCar(Integer id) throws ServicesException{
//        try {
//            marathonRaceRepository.delete(id);
//        }catch (RepositoryException ex){
//            throw new ServicesException("Error deleting car"+ex);
//        }
//    }
//
//    public void updateCar(Integer id,String name,int dimension,String color,int price) throws ServicesException{
//        try {
//            MarathonRace marathonRace = new MarathonRace(name,dimension,color,price);
//            marathonRace.setID(id);
//            marathonRaceRepository.update(id, marathonRace);
//        }catch (RepositoryException ex){
//            throw new ServicesException("Error updating car"+ex);
//        }
//    }
//    public List<MarathonRace> getCarpetsNotReservedByModel(String model){
//        return marathonRaceRepository.filterByNameAndStatus(model, marathonRaceStatus.New);
//
//    }
//
//    public List<MarathonRace> getCarpetsWithCertainName(String name)
//    {
//        return marathonRaceRepository.findByName(name);
//    }
//    public List<MarathonRace> getCarpetsWithCertainColor(String color)
//    {
//        return marathonRaceRepository.findByColor(color);
//    }
//    public List<MarathonRace> getCarpetsWithCertainPrice(int price)
//    {
//        return marathonRaceRepository.findByPrice(price);
//    }
//    public List<MarathonRace> getCarpets()
//    {
//        return marathonRaceRepository.findAllCarpets();
//    }


    public List<MarathonRace> getRaces()
    {
        return marathonRaceRepository.findAllMarathons();
    }

    public List<Registration> getRegistrationByRace(int id)
    {
        return registrationRepository.filterByRace(id);
    }

    public List<Registration> getRegistrationsByAge(int age1,int age2)
    {
        return registrationRepository.filterByAge(age1,age2);
    }

    public void addOrder(MarathonRace marathonRace, String personName, String phoneNumber, String address,int age) throws ServicesException{
        try {
//            marathonRace.setStatus(marathonRaceStatus.Reserved);
            Registration registration = new Registration(personName,phoneNumber,address,age,marathonRace);
            marathonRaceRepository.update(marathonRace.getId(), marathonRace);
            registrationRepository.add(registration);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding marathonRace reservation"+er);
        }

    }
//    public List<MarathonRace> getReservedCarpets(){
//        return marathonRaceRepository.filterdByStatus(marathonRaceStatus.Reserved);
//
//    }
//
//    public List<MarathonRace> getCarpetByStatus(marathonRaceStatus status){
//        return marathonRaceRepository.filterdByStatus(status);
//
//    }
//    public List<Registration> getOrdersByCarpet(int id){
//        return orederRepository.filterByCarpet(id);
//    }
//    public List<Registration> getOrdersByDate(String date){
//        return orederRepository.filterByDate(date);
//    }
//    public List<MarathonRace> getCarpetsByDate(String date){
//        return orederRepository.filterByDateCarpets(date);
//    }
}
