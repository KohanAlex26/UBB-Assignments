package core.domain;

import java.io.Serializable;
import java.util.Objects;

public class SportiveTrainerPrimaryKey implements Serializable {
    Long sportiveId, trainerId;

    public SportiveTrainerPrimaryKey() {}

    public SportiveTrainerPrimaryKey(Long sportiveId, Long trainerId){
        this.sportiveId = sportiveId;
        this.trainerId = trainerId;
    }

    public Long getSportiveId(){
        return sportiveId;
    }

    public Long getTrainerId(){
        return  trainerId;
    }

    public void setSportiveId(Long id){
        sportiveId = id;
    }

    public void setTrainerId(Long id){
        trainerId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportiveTrainerPrimaryKey that = (SportiveTrainerPrimaryKey) o;
        return trainerId.equals(that.trainerId) &&
                sportiveId.equals(that.sportiveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportiveId, trainerId);
    }

    @Override
    public String toString() {
        return "SportiveTrainerPrimaryKey{" +
                "trainerId=" + trainerId +
                ", sportiveId=" + sportiveId +
                '}';
    }
}
