package service;

import model.Enrolment;
import model.WinterGame;
import model.gameStatus;
import repo.GameRepository;
import repo.EnrolmentRepository;
import repo.RepositoryException;

import java.util.List;

public class WinterGamesServices {

    private EnrolmentRepository enrolmentRepository;

    private GameRepository gameRepository;

    public WinterGamesServices(GameRepository gameRepository, EnrolmentRepository enrolmentRepository) {
        this.enrolmentRepository = enrolmentRepository;
        this.gameRepository = gameRepository;
    }


    public int addGame(String name, String type,String date,int minAge, int maxAge) throws ServicesException{
        try {
            WinterGame winterGame = new WinterGame(name,type,date,minAge,maxAge);
            WinterGame newWinterGame = gameRepository.add(winterGame);
            return newWinterGame.getID();
        }catch (RepositoryException ex){
            throw new ServicesException("Error adding car"+ex);
        }
    }


    public void deleteGame(Integer id) throws ServicesException{
        try {
            gameRepository.delete(id);
        }catch (RepositoryException ex){
            throw new ServicesException("Error deleting car"+ex);
        }
    }


    public void updateGame(Integer id,String name, String type,String date,int minAge, int maxAge) throws ServicesException{
        try {
            WinterGame winterGame = new WinterGame(name,type,date,minAge,maxAge);
            winterGame.setID(id);
            gameRepository.update(id, winterGame);
        }catch (RepositoryException ex){
            throw new ServicesException("Error updating car"+ex);
        }
    }


    public void addEnrolment(String personName, String parentName,int age, WinterGame winterGame) throws ServicesException{
        try {
            winterGame.setStatus(gameStatus.Reserved);
            Enrolment enrolment = new Enrolment(personName,parentName,age,winterGame);
            gameRepository.update(winterGame.getId(), winterGame);
            enrolmentRepository.add(enrolment);
        }catch (RepositoryException er){
            throw  new ServicesException("Error adding winterGame reservation"+er);
        }

    }
    public List<WinterGame> getGames()
    {
        return gameRepository.findAllGames();
    }
    public List<Enrolment> getEnrolmentsByGame(int id)
    {
        return enrolmentRepository.filterByGame(id);
    }
    public List<WinterGame> getGamesByChild(String childName)
    {
        return enrolmentRepository.filterGamesByChild(childName);
    }
}
