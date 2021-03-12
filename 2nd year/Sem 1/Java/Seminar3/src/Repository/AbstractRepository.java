package Repository;

import java.util.*;
import model.*;

public abstract class AbstractRepository <T extends Identifiable<Id>,Id> implements Repository<Id,T>
{
    private Map<Id,T> map;
    public AbstractRepository()
    {
        map=new TreeMap<>();
    }

    public void add(T newt)
    {
        if( map.containsKey(newt.getId())==true)
            throw new RuntimeException("The item is already in the list");
        else
            map.put(newt.getId(),newt);
    }
    public boolean delete(T newt)
    {
        if(map.containsKey(newt.getId()))
        {
            map.remove(newt.getId());
            return true;
        }
        else
            return false;

    }
    public void update(Id id, T newT)
    {
        if(map.containsKey(id))
        {
            map.put(id, newT);
        }
        else
        {
            throw new RuntimeException("The item is not in the repository");
        }
    }
    public T findbyId(Id id)
    {
        if(map.containsKey(id))
            return map.get(id);
        else
            return null;
    }

    public Iterable<T> findall()
    {
    return map.values();
    }
}
