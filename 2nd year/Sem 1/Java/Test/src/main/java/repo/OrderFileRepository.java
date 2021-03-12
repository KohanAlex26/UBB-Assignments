package repo;

import model.Carpet;
import model.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderFileRepository extends OrderInMemoryRepository {
    private String FileName;
    private CarpetRepository carpetRepository;
    private static int idGenerator=0;

    public OrderFileRepository(String s, CarpetRepository carpetRepository){
        this.FileName=s;
        this.carpetRepository = carpetRepository;
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
                if(el.length!=5) {
                    System.err.println("Line is not valid " + line);
                    continue;
                }
                try{
                    int orderId=Integer.parseInt(el[0]);
                    int carpetId=Integer.parseInt(el[1]);
                    Carpet carpet = carpetRepository.findbyId(carpetId);
                    Order obj=new Order(orderId,el[2],el[3],el[4],carpet);
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
            for(Order obj:findall())
            {
                String str=obj.getId()+";"+obj.getCarpet().getID()+";"+obj.getPersonName()+";"+obj.getPersonAddress()+";"+obj.getDate();
                pw.println(str);
            }
        }
        catch (IOException e)
        {
            throw new RepositoryException("error"+e);
        }
    }

    @Override
    public Order add(Order el){
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
    public void update(Integer integer, Order el)
    {
        super.update(integer,el);
        writeToFile();
    }
    private static int getNextId(){
        return idGenerator++;
    }
}
