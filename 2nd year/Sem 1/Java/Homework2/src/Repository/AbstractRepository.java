package Repository;

import model.Identifiable;

import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractRepository<T extends Identifiable<Id>,Id> implements Repository<Id,T> {

    private Map<Id,T> map;
    public AbstractRepository() {
        map=new TreeMap<>();
    }
    @Override
    public void add(T newt)
    {
        if(map.containsKey(newt.getId()))
            throw new RuntimeException("The item is already in the list");
        else
            map.put(newt.getId(), newt);
    }
    @Override
    public boolean delete(T t)
    {
        if(map.containsKey(t.getId()))
        {
            map.remove(t.getId());
            return true;
        }
            return false;
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
            return null;
    }
    @Override
    public Iterable<T> findall()
    {
        return map.values();
    }

   /* @Override
    public Iterator<Car> carIterator()
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
