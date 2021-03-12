package repo;

import model.MarathonRace;
import model.marathonRaceStatus;

import java.util.List;

public interface MarathonRaceRepository extends Repository<Integer, MarathonRace>{

    List<MarathonRace> findAllMarathons();
}