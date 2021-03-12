package repo;

import model.WinterGame;
import model.gameStatus;

import java.util.List;

public interface GameRepository extends Repository<Integer, WinterGame>{
    List<WinterGame> findAllGames();
}