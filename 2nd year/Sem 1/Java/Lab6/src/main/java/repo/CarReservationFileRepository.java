package repo;

import model.Car;
import model.CarReservation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CarReservationFileRepository extends CarReservationInMemoryRepository{
    private String FileName;
    private CarRepository carRepository;
    private static int idGenerator=0;

    public CarReservationFileRepository(String s,CarRepository carRepository){
        this.FileName=s;
        this.carRepository=carRepository;
        readFromFile();
    }


    private void readFromFile()
    {
        try(BufferedReader br=new BufferedReader(new FileReader(FileName)))
        {
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while((line=br.readLine())!=null)
            {
                String[] el=line.split(";");
                if(el.length!=6) {
                    System.err.println("Line is not valid " + line);
                    continue;
                }
                try{
                    int id=Integer.parseInt(el[0]);
                    int carId=Integer.parseInt(el[1]);
                    Car car=carRepository.findbyId(carId);
                    CarReservation obj=new CarReservation(id,el[2],el[3],el[4],car,el[5]);
                    super.add(obj);
                }
                catch (NumberFormatException nr)
                {
                    System.err.println("Id not valid " + el[0]);
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
            pw.println(idGenerator);
            for(CarReservation obj:findall())
            {
                String str=obj.getId()+";"+obj.getCar().getID()+";"+obj.getOwnerName()+";"+obj.getOwnerAddress()+";"+obj.getPhoneNumber()+";"+obj.getDate();
                pw.println(str);
            }
        }
        catch (IOException e)
        {
            throw new RepositoryException("error"+e);
        }
    }

    @Override
    public CarReservation add(CarReservation el){
        el.setId(getNextId());
        super.add(el);
        writeToFile();
        return el;
    }


    @Override
    public void delete(Integer id)
    {
        super.delete(id);
        writeToFile();
    }

    @Override
    public void update(Integer integer,CarReservation el)
    {
        super.update(integer,el);
        writeToFile();
    }
    private static int getNextId(){
        return idGenerator++;
    }
}
