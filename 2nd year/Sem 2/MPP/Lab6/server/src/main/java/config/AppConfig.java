package config;

import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;
import domain.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import domain.entities.Sportive;
import repository.*;


@Configuration
public class AppConfig {

    //Sportive
    @Bean
    RepositoryInterface<Long, Sportive> sportiveRepository() {
        return new SportiveRepository();
    }

    @Bean
    Validator<Sportive> sportiveValidator() { return new SportiveValidator(); }

    //Trainer
    @Bean
    RepositoryInterface<Long, Trainer> trainerRepository() {
        return new TrainerRepository();
    }

    @Bean
    Validator<Trainer> trainerValidator() { return new TrainerValidator(); }


    //Sportive Trainer
    @Bean
    RepositoryInterface<Long, SportiveTrainer> sportiveTrainerRepository() {
        return new SportiveTrainerRepository();
    }

    @Bean
    Validator<SportiveTrainer> sportiveTrainerValidator() { return new SportiveTrainerValidator(); }


    //Team
    @Bean
    RepositoryInterface<Long, Team> teamRepository() { return new TeamRepository(); }

    @Bean
    Validator<Team> teamValidator() { return new TeamValidator(); }

}
