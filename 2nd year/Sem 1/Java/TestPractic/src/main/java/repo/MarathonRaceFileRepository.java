package repo;

import model.MarathonRace;
import model.marathonRaceStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MarathonRaceFileRepository extends MarathonRaceInMemoryRepository {
private String FileName;
private static int idGenerator=0;

public MarathonRaceFileRepository(String s){
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
            if(el.length!=5) {
                System.err.println("Line is not valid " + line);
                continue;
            }
            try{
                int id=Integer.parseInt(el[0]);
//                marathonRaceStatus status= marathonRaceStatus.valueOf(marathonRaceStatus.class,el[5]);
                MarathonRace c=new MarathonRace(id,el[1],el[2],el[3],Integer.parseInt(el[4]));
//                c.setStatus(status);
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
        for(MarathonRace obj:findall())
        {
            pw.println(obj.getId()+";"+obj.getName()+";"+obj.getType()+";"+obj.getDate()+";"+obj.getDistance());
        }
    }
    catch (IOException e)
    {
        throw new RepositoryException("error"+e);
    }
}

@Override
public MarathonRace add(MarathonRace el){
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
public void update(Integer integer, MarathonRace el)
{
    super.update(integer,el);
    writeToFile();
}

private static int getNextId(){
    return idGenerator++;
}
}
