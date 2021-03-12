package repo;

import model.Carpet;
import model.carpetStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CarpetFileRepository extends CarpetInMemoryRepository {
private String FileName;
private static int idGenerator=0;

public CarpetFileRepository(String s){
    this.FileName=s;
    readFromFile();
}
private void readFromFile() {
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
                carpetStatus status= carpetStatus.valueOf(carpetStatus.class,el[5]);
                Carpet c=new Carpet(id,el[1],Integer.parseInt(el[2]),el[3],Integer.parseInt(el[4]));
                c.setStatus(status);
                super.add(c);
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
        for(Carpet obj:findall())
        {
            pw.println(obj.getId()+";"+obj.getName()+";"+obj.getDimension()+";"+obj.getColor()+";"+obj.getPrice()+";"+obj.getStatus());
        }
    }
    catch (IOException e)
    {
        throw new RepositoryException("error"+e);
    }
}

@Override
public Carpet add(Carpet el){
    el.setID(getNextId());
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
public void update(Integer integer, Carpet el)
{
    super.update(integer,el);
    writeToFile();
}

private static int getNextId(){
    return idGenerator++;
}
}
