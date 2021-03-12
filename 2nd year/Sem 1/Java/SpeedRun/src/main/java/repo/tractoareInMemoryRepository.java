package repo;

import model.Tractore;
import model.carpetStatus;

import java.util.List;
import java.util.stream.Collectors;

public class tractoareInMemoryRepository extends AbstractRepository<Integer, Tractore> implements tractoareRepository {
   public tractoareInMemoryRepository(){}


    @Override
    public List<Tractore> findByName(String name) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

   @Override
    public List<Tractore> findByDimension(int dimension) {
       return getAll().stream().filter(x->x.getDimension()==dimension).collect(Collectors.toList());
    }

    @Override
    public List<Tractore> findByColor(String color) {
        return getAll().stream().filter(x->x.getColor().toLowerCase().contains(color.toLowerCase())).collect(Collectors.toList());
    }
    @Override
    public List<Tractore> findAllCarpets() {
        return getAll().stream().collect(Collectors.toList());
    }

    @Override
    public List<Tractore> findByPrice(int price) {
        return getAll().stream().filter(x->x.getPrice()== price).collect(Collectors.toList());
    }

    @Override
    public List<Tractore> filterdByStatus(carpetStatus status) {
        return getAll().stream().filter(x->x.getStatus()==status).collect(Collectors.toList());
    }

    @Override
    public List<Tractore> filterByNameAndStatus(String name, carpetStatus status) {
        return getAll().stream().filter(x->x.getName().toLowerCase().contains(name.toLowerCase()) && x.getStatus()==status).collect(Collectors.toList());
    }
}
