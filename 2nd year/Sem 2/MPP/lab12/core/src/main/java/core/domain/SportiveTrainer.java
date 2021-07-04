package core.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by radu.
 */
@Entity
//@IdClass(SportiveTrainerPrimaryKey.class)
public class SportiveTrainer extends BaseEntity<SportiveTrainerPrimaryKey> {


//    @Id
@SuppressWarnings("JpaModelReferenceInspection")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sportiveId")
    @JoinColumn(name = "sportiveId")
    Sportive sportive;

//    @Id
@SuppressWarnings("JpaModelReferenceInspection")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("trainerId")
    @JoinColumn(name = "trainerId")
    Trainer trainer;

    private int cost;
    private String trainingType;

    public SportiveTrainer()
    {
    }

    public SportiveTrainer(SportiveTrainerPrimaryKey sportiveTrainerPrimaryKey,Sportive sportive,Trainer trainer, int cost,String trainingType)
    {
        this.setId(sportiveTrainerPrimaryKey);
        this.sportive=sportive;
        this.trainer=trainer;
        this.cost=cost;
        this.trainingType=trainingType;
    }

    public Sportive getSportive() {
        return sportive;
    }

    public void setSportive(Sportive sportive) {
        this.sportive = sportive;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    @Override
    public String toString() {
        return "SportiveTrainer{" +
                "sportiveId="+ sportive.getId()+
                ", sportive=" + sportive +
                ", trainer=" + trainer +
                ", cost=" + cost +
                ", trainingType='" + trainingType + '\'' +
                '}';
    }
}

