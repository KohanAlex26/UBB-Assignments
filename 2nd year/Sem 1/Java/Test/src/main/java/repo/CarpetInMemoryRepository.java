package repo;

import model.Carpet;
import model.carpetStatus;

import java.util.List;
import java.util.stream.Collectors;

public class CarpetInMemoryRepository extends AbstractRepository<Integer, Carpet> implements CarpetRepository {
   public CarpetInMemoryRepository(){}


    @Override
    public List<Carpet> findByName(String name) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

   @Override
    public List<Carpet> findByDimension(int dimension) {
       return getAll().stream().filter(x->x.getDimension()==dimension).collect(Collectors.toList());
    }

    @Override
    public List<Carpet> findByColor(String color) {
        return getAll().stream().filter(x->x.getColor().toLowerCase().contains(color.toLowerCase())).collect(Collectors.toList());
    }
    @Override
    public List<Carpet> findAllCarpets() {
        return getAll().stream().collect(Collectors.toList());
    }

    @Override
    public List<Carpet> findByPrice(int price) {
        return getAll().stream().filter(x->x.getPrice()== price).collect(Collectors.toList());
    }

    @Override
    public List<Carpet> filterdByStatus(carpetStatus status) {
        return getAll().stream().filter(x->x.getStatus()==status).collect(Collectors.toList());
    }

    @Override
    public List<Carpet> filterByNameAndStatus(String name, carpetStatus status) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase()) && x.getStatus()==status).collect(Collectors.toList());
    }
}
