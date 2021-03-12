package repo;

import model.Carpet;
import model.Order;

import java.util.List;

public interface OrderRepository extends Repository<Integer, Order>{
    List<Order> filterByDate(String date);
    List<Order> filterByCarpet(int id);
    List<Carpet> filterByDateCarpets(String date);
}
