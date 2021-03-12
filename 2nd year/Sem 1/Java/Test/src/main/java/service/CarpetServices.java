package service;

import model.Carpet;
import model.Order;
import model.carpetStatus;
import repo.CarpetRepository;
import repo.OrderRepository;
import repo.RepositoryException;

import java.util.List;

public class CarpetServices {

    private OrderRepository orederRepository;

    private CarpetRepository carpetRepository;

    public CarpetServices(CarpetRepository carpetRepository, OrderRepository orederRepository) {
        this.orederRepository = orederRepository;
        this.carpetRepository = carpetRepository;
    }

    public int addCarpet(String name, int dimension, String color, int price) throws ServicesException{
        try {
            Carpet carpet = new Carpet(name,dimension,color,price);
            Carpet newCarpet = carpetRepository.add(carpet);
            return newCarpet.getID();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding car"+ex);
        }
    }

    public void deleteCar(Integer id) throws ServicesException{
        try {
            carpetRepository.delete(id);
        }catch (RepositoryException ex){
            throw new ServicesException("Error deleting car"+ex);
        }
    }

    public void updateCar(Integer id,String name,int dimension,String color,int price) throws ServicesException{
        try {
            Carpet carpet = new Carpet(name,dimension,color,price);
            carpet.setID(id);
            carpetRepository.update(id, carpet);
        }catch (RepositoryException ex){
            throw new ServicesException("Error updating car"+ex);
        }
    }
    public List<Carpet> getCarpetsNotReservedByModel(String model){
        return carpetRepository.filterByNameAndStatus(model, carpetStatus.New);

    }

    public List<Carpet> getCarpetsWithCertainName(String name)
    {
        return carpetRepository.findByName(name);
    }

    public List<Carpet> getCarpetsWithCertainColor(String color)
    {
        return carpetRepository.findByColor(color);
    }
    public List<Carpet> getCarpetsWithCertainPrice(int price)
    {
        return carpetRepository.findByPrice(price);
    }
    public List<Carpet> getCarpets()
    {
        return carpetRepository.findAllCarpets();
    }

    public void addOrder(Carpet carpet, String orderName, String orderAddress,String date) throws ServicesException{
        try {
            carpet.setStatus(carpetStatus.Reserved);
            Order order = new Order(orderName,orderAddress,date, carpet);
            carpetRepository.update(carpet.getId(), carpet);
            orederRepository.add(order);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding carpet reservation"+er);
        }

    }
    public List<Carpet> getReservedCarpets(){
        return carpetRepository.filterdByStatus(carpetStatus.Reserved);

    }

    public List<Carpet> getCarpetByStatus(carpetStatus status){
        return carpetRepository.filterdByStatus(status);

    }
    public List<Order> getOrdersByCarpet(int id){
        return orederRepository.filterByCarpet(id);
    }
    public List<Order> getOrdersByDate(String date){
        return orederRepository.filterByDate(date);
    }
    public List<Carpet> getCarpetsByDate(String date){
        return orederRepository.filterByDateCarpets(date);
    }
}
