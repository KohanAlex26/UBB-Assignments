package Repository;

import CarDomain.Car;
import model.CarReservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ReservationRepositoryFile extends AbstractRepository<CarReservation,Integer>{
    private String FileName;
    public ReservationRepositoryFile(String s){
        this.FileName=s;
        ReadFromFile();
    }
    private void ReadFromFile()
    {
        try(BufferedReader br=new BufferedReader(new FileReader(FileName)))
        {
            String line=null;
            while((line=br.readLine())!=null)
            {
                String[] el=line.split(";");
                if(el.length!=11) {
                    System.err.println("Line is not valid " + line);
                    continue;
                }
                try{
                    int id=Integer.parseInt(el[0]);
                    Car c=new Car(el[4],el[5],el[6],Integer.parseInt(el[7]),Integer.parseInt(el[8]));
                    CarReservation obj=new CarReservation(id,el[1],el[2],el[3],c,el[9],Boolean.parseBoolean(el[10]));
                    super.add(obj);
                }
                catch (NumberFormatException nr)
                {
                    System.err.println("Id not valid" + el[0]);
                }
            }

        }
        catch (IOException ex)
        {
            throw new RepositoryException("Error"+ ex);
        }

    }
    private void writeToFile()
    {
        try(PrintWriter pw=new PrintWriter(FileName))
        {
            for(CarReservation obj:findall())
            {
                String str=obj.getId()+";"+obj.getOwnerName()+";"+obj.getOwnerAddress()+";"+obj.getPhoneNumber()+";"+obj.getCar().getModel()+";"+obj.getCar().getColor()+";"+obj.getCar().getBrand()+";"+obj.getCar().getSeats()+";"+obj.getCar().getId()+";"+obj.getDate()+";"+obj.isReserved();
                pw.println(str);
            }
        }
        catch (IOException e)
        {
            throw new RepositoryException("error"+e);
        }
    }

    @Override
    public void add(CarReservation m){
        try{
            super.add(m);
            writeToFile();
        }
        catch(RuntimeException ex) {
            throw new RepositoryException(ex);
        }
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

