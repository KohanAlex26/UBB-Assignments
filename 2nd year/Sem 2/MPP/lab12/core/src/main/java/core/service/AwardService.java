package core.service;

import core.domain.Award;

import java.util.List;

public interface AwardService {
    void addAward(String date);
    void deleteAward(Long id);
    void updateAward(Long id, String date);
    List<Award> getAwardsFromRepository();
    List<Award> getAwardsOnPage(int pageNo, int pageSize);
    Award getAwardById(long id);
    List<Award> getAllAwards();
}
