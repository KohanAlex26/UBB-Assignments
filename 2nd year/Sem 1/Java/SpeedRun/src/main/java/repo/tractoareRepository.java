package repo;

import model.Tractore;
import model.carpetStatus;

import java.util.List;

public interface tractoareRepository extends Repository<Integer, Tractore>{
    List<Tractore> findByName(String name);
    List<Tractore> findByDimension(int dimension);
    List<Tractore> findByColor(String color);
    List<Tractore> findAllCarpets();
    List<Tractore> findByPrice(int price);
    List<Tractore> filterdByStatus(carpetStatus status);
    List<Tractore> filterByNameAndStatus(String name, carpetStatus status);
}