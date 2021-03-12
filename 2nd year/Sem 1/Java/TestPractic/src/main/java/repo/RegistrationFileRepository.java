package repo;

import model.MarathonRace;
import model.Registration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationFileRepository extends RegistrationInMemoryRepository {
    private String FileName;
    private MarathonRaceRepository marathonRaceRepository;
    private static int idGenerator=0;

    public RegistrationFileRepository(String s, MarathonRaceRepository marathonRaceRepository){
        this.FileName=s;
        this.marathonRaceRepository = marathonRaceRepository;
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
                    int registrationId=Integer.parseInt(el[0]);
                    int raceId=Integer.parseInt(el[1]);
                    MarathonRace marathonRace = marathonRaceRepository.findbyId(raceId);
                    Registration obj=new Registration(registrationId,el[2],el[3],el[4],Integer.parseInt(el[5]), marathonRace);
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
            for(Registration obj:findall())
            {
                String str=obj.getId()+";"+obj.getRace().getID()+";"+obj.getPersonName()+";"+obj.getPhoneNumber()+";"+obj.getAddress()+";"+obj.getAge();
                pw.println(str);
            }
        }
        catch (IOException e)
        {
            throw new RepositoryException("error"+e);
        }
    }

    @Override
    public Registration add(Registration el){
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
    public void update(Integer integer, Registration el)
    {
        super.update(integer,el);
        writeToFile();
    }
    private static int getNextId(){
        return idGenerator++;
    }
}
