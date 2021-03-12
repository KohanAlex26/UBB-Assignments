package repo;

import model.MarathonRace;
import model.marathonRaceStatus;

import java.util.List;
import java.util.stream.Collectors;

public class MarathonRaceInMemoryRepository extends AbstractRepository<Integer, MarathonRace> implements MarathonRaceRepository {
   public MarathonRaceInMemoryRepository(){}
    @Override
    public List<MarathonRace> findAllMarathons() {
        return getAll().stream().collect(Collectors.toList());
    }

}
