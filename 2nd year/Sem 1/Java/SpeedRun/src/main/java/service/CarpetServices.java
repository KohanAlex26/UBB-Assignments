package service;

import model.Tractore;
import model.Order;
import model.carpetStatus;
import repo.tractoareRepository;
import repo.OrderRepository;
import repo.RepositoryException;

import java.util.List;

public class CarpetServices {

    private OrderRepository orederRepository;

    private tractoareRepository tractoareRepository;

    public CarpetServices(tractoareRepository tractoareRepository, OrderRepository orederRepository) {
        this.orederRepository = orederRepository;
        this.tractoareRepository = tractoareRepository;
    }

    public int addCarpet(String name, int dimension, String color, int price) throws ServicesException{
        try {
            Tractore tractore = new Tractore(name,dimension,color,price);
            Tractore newTractore = tractoareRepository.add(tractore);
            return newTractore.getID();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding car"+ex);
        }
    }

    public void deleteCar(Integer id) throws ServicesException{
        try {
            tractoareRepository.delete(id);
        }catch (RepositoryException ex){
            throw new ServicesException("Error deleting car"+ex);
        }
    }

    public void updateCar(Integer id,String name,int dimension,String color,int price) throws ServicesException{
        try {
            Tractore tractore = new Tractore(name,dimension,color,price);
            tractore.setID(id);
            tractoareRepository.update(id, tractore);
        }catch (RepositoryException ex){
            throw new ServicesException("Error updating car"+ex);
        }
    }
    public List<Tractore> getCarpetsNotReservedByModel(String model){
        return tractoareRepository.filterByNameAndStatus(model, carpetStatus.New);

    }

    public List<Tractore> getCarpetsWithCertainName(String name)
    {
        return tractoareRepository.findByName(name);
    }

    public List<Tractore> getCarpetsWithCertainColor(String color)
    {
        return tractoareRepository.findByColor(color);
    }
    public List<Tractore> getCarpetsWithCertainPrice(int price)
    {
        return tractoareRepository.findByPrice(price);
    }
    public List<Tractore> getCarpets()
    {
        return tractoareRepository.findAllCarpets();
    }

    public void addOrder(Tractore tractore, String orderName, String orderAddress, String date) throws ServicesException{
        try {
            tractore.setStatus(carpetStatus.Reserved);
            Order order = new Order(orderName,orderAddress,date, tractore);
            tractoareRepository.update(tractore.getId(), tractore);
            orederRepository.add(order);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding tractore reservation"+er);
        }

    }
    public List<Tractore> getReservedCarpets(){
        return tractoareRepository.filterdByStatus(carpetStatus.Reserved);

    }

    public List<Tractore> getCarpetByStatus(carpetStatus status){
        return tractoareRepository.filterdByStatus(status);

    }
    public List<Order> getOrdersByCarpet(int id){
        return orederRepository.filterByCarpet(id);
    }
    public List<Order> getOrdersByDate(String date){
        return orederRepository.filterByDate(date);
    }
    public List<Tractore> getCarpetsByDate(String date){
        return orederRepository.filterByDateCarpets(date);
    }
}
