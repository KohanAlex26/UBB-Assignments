package main.repo;

import main.model.CarReservation;

import java.io.*;
import java.util.Map;


public class RepositorySerialization extends AbstractRepository<CarReservation, Integer>
{	//make Map<T,Id> elem from AbstractRepository  protected
        private String filename;

        public RepositorySerialization(String filename) {
                this.filename = filename;
        }

        public void writeToFile(){
                try(ObjectOutputStream e=new ObjectOutputStream(new FileOutputStream(filename))){
                e.writeObject(map);
                 }
                catch(IOException r){
                        throw new RepositoryException("message"  + r);
                }
        }

        public void readFromFile (){
                try(ObjectInputStream in= new ObjectInputStream(new FileInputStream(filename))){
                map = (Map<Integer,CarReservation>) in.readObject();
                }
                catch(IOException|ClassNotFoundException err){
                throw new RepositoryException("Error reading from file: "+ err);
                }

        }
        @Override
        public CarReservation add(CarReservation m){
                try{
                        super.add(m);
                        writeToFile();
                }
                catch(RuntimeException ex) {
                        throw new RepositoryException(ex);
                }
                return m;
        }


        @Override
        public boolean delete(CarReservation m)
        {
                try {
                        super.delete(m);
                        writeToFile();
                        return true;
                }
                catch(RuntimeException ex){
                        throw new RepositoryException(ex);
                }
        }

        @Override
        public void update(Integer id,CarReservation m)
        {
                try{
                        super.update(id,m);
                        writeToFile();
                }
                catch(RuntimeException ex) {
                        throw new RepositoryException(ex);
                }
        }
}
