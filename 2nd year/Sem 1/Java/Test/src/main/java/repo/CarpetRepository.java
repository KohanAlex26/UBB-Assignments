package repo;

import model.Carpet;
import model.carpetStatus;

import java.util.List;

public interface CarpetRepository extends Repository<Integer, Carpet>{
    List<Carpet> findByName(String name);
    List<Carpet> findByDimension(int dimension);
    List<Carpet> findByColor(String color);
    List<Carpet> findAllCarpets();
    List<Carpet> findByPrice(int price);
    List<Carpet> filterdByStatus(carpetStatus status);
    List<Carpet> filterByNameAndStatus(String name, carpetStatus status);
}