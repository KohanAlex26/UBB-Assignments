package repo;

import model.WinterGame;
import model.gameStatus;

import java.util.List;
import java.util.stream.Collectors;

public class GameInMemoryRepository extends AbstractRepository<Integer, WinterGame> implements GameRepository {
   public GameInMemoryRepository(){}


    @Override
    public List<WinterGame> findAllGames() {
        return getAll().stream().collect(Collectors.toList());
    }


}
