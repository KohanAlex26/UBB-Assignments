package repo;

import model.Enrolment;
import model.WinterGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrolmentFileRepository extends EnrolmentInMemoryRepository {
    private String FileName;
    private GameRepository gameRepository;
    private static int idGenerator=0;

    public EnrolmentFileRepository(String s, GameRepository gameRepository){
        this.FileName=s;
        this.gameRepository = gameRepository;
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
                    int enrolmentId=Integer.parseInt(el[0]);
                    int gameId=Integer.parseInt(el[1]);
                    WinterGame winterGame = gameRepository.findbyId(gameId);
                    Enrolment obj=new Enrolment(enrolmentId,el[2],el[3],Integer.parseInt(el[4]), winterGame);
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
            for(Enrolment obj:findall())
            {
                String str=obj.getId()+";"+obj.getWinterGame().getID()+";"+obj.getPersonName()+";"+obj.getParentName()+";"+obj.getAge();
                pw.println(str);
            }
        }
        catch (IOException e)
        {
            throw new RepositoryException("error"+e);
        }
    }

    @Override
    public Enrolment add(Enrolment el){
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
    public void update(Integer integer, Enrolment el)
    {
        super.update(integer,el);
        writeToFile();
    }
    private static int getNextId(){
        return idGenerator++;
    }
}
