package Repository;

public interface Repository<Id,T>{

    void add(T newt);
    boolean delete(T t);
    void update(Id id,T newt);
    T findbyId (Id id);
    Iterable<T> findall();

        /*Iterator<Car> carIterator();*/
     /*   public void seeT();*/
}
