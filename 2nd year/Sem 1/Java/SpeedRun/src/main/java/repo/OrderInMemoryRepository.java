package repo;

import model.Tractore;
import model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderInMemoryRepository extends AbstractRepository<Integer, Order> implements OrderRepository {

    @Override
    public List<Order> filterByDate(String date) {
        return getAll().stream().filter(x->x.getDate().toLowerCase().equals(date.toLowerCase())).collect(Collectors.toList());
    }
    @Override
    public List<Order> filterByCarpet(int id) {
        return getAll().stream().filter(x->x.getCarpet().getID()==id).collect(Collectors.toList());
    }
    @Override
    public List<Tractore> filterByDateCarpets(String date) {
        return getAll().stream().filter(x->x.getDate().toLowerCase().equals(date.toLowerCase())).map(Order::getCarpet).distinct().collect(Collectors.toList());
    }
}
