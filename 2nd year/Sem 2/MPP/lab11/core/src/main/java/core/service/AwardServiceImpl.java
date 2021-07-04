package core.service;

import core.domain.Team;
import core.exceptions.SportException;
import core.repository.AwardRepository;
import core.domain.Award;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.max;

@Service
public class AwardServiceImpl implements AwardService {
    public static final Logger logger = LoggerFactory.getLogger(AwardServiceImpl.class);

    @Autowired
    private AwardRepository awardsRepository;

    @Override
    public void addAward(String date) {
        long id = 0;
        for (Award award : this.awardsRepository.findAll())
            id = max(id, award.getId() + 1);
        Award award = new Award(id, date);
        awardsRepository.save(award);
    }

    @Override
    public Award getAwardById(long id) {
        logger.trace("getAwardById - method entered={}", id);
        return awardsRepository.findById(id).get();
    }

    @Override
    public List<Award> getAwardsFromRepository() {
        return awardsRepository.findAll();
    }

    @Override
    public List<Award> getAllAwards() {
        logger.trace("getAllAwards - method entered");
        return awardsRepository.findAll();
    }
    @Override
    @Transactional
    public void deleteAward(Long id) {
        awardsRepository.findById(id)
                .ifPresentOrElse((award) -> {
                            awardsRepository.deleteById(award.getId());
                        },
                        () -> {
                            throw new SportException("Award does not exist");
                        }
                );
    }

    @Override
    @Transactional
    public void updateAward(Long id, String date) {
        awardsRepository.findById(id).ifPresentOrElse((award -> {
            award.setDate(date);
        }), ()->{
            throw new SportException("Nonexistent award");
        });
    }

    @Override
    public List<Award> getAwardsOnPage(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Award> pagedResult = awardsRepository.findAll(page);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
