package repo;

import java.util.Collection;

public interface Repository<Id,T>{

    T add(T newt);
    void delete(Id id);
    void update(Id id,T newt);
    T findbyId (Id id);
    Iterable<T> findall();
    Collection<T> getAll();

        /*Iterator<MarathonRace> carIterator();*/
     /*   public void seeT();*/
}
