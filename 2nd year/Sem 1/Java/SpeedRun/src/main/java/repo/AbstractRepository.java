package repo;

import model.Identifiable;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractRepository<Id,T extends Identifiable<Id>> implements Repository<Id,T> {

    protected Map<Id,T> map;
    public AbstractRepository() {
        map=new TreeMap<>();
    }

    public T add(T newt)
    {
        if(map.containsKey(newt.getId()))
            throw new RuntimeException("The item is already in the list");
        else
            map.put(newt.getId(), newt);
        return newt;
    }

    public void delete(Id id)
    {
        if(map.containsKey(id))
        {
            map.remove(id);
        }
    }
    @Override
    public void update(Id id,T newt)
    {
        if(map.containsKey(id))
        {
            map.put(id, newt);
        }
        else
        {
            throw new RuntimeException("The item is not in the repository");
        }
    }
    @Override
    public T findbyId(Id id)
    {
        if(map.containsKey(id))
            return map.get(id);
        else
            throw new RepositoryException("Element doesn't exist"+id);
    }
    @Override
    public Iterable<T> findall()
    {
        return map.values();
    }

    @Override
    public Collection<T> getAll() {
        return map.values();
    }


   /* @Override
    public Iterator<Tractore> carIterator()
    {
        return cars.iterator();
    }
    @Override
    public void seeCars()
    {
        for(int i=0;i<cars.size();i++)
            System.out.println(cars.get(i));
    }*/

}
