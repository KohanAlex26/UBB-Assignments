package domain.entities;

/**
 * Link-entity between Sportive and Trainer entities
 *
 * @author dzen.
 *
 */

public class SportiveTrainer extends BaseEntity<Long> {
    private Long sportiveID, trainerID;
    private int cost;
    private String trainingType;

    /**
     * Constructs with default values
     */
    public SportiveTrainer(){
        this.sportiveID = 0L;
        this.trainerID = 0L;
        this.trainingType = "";
        this.cost = 0;
    }

    /**
     * Constructs with given arguments
     *
     * @param sportiveID
     *              must be positive
     * @param trainerID
     *              must be positive
     * @param trainingType
     *              must exist between training types
     * @param cost
     *              must be positive
     */
    public SportiveTrainer(Long sportiveID, Long trainerID, String trainingType, int cost){
        this.sportiveID = sportiveID;
        this.trainerID = trainerID;
        this.trainingType = trainingType;
        this.cost = cost;
    }
    /**
     * Constructs with given arguments
     *
     * @param ID
     *              must be positive
     * @param sportiveID
     *              must be positive
     * @param trainerID
     *              must be positive
     * @param trainingType
     *              must exist between training types
     * @param cost
     *              must be positive
     */
    public SportiveTrainer(Long ID, Long sportiveID, Long trainerID, String trainingType, int cost){
        this.setId(ID);
        this.sportiveID = sportiveID;
        this.trainerID = trainerID;
        this.trainingType = trainingType;
        this.cost = cost;
    }


    public Long getSportiveID() {
        return sportiveID;
    }

    public void setSportiveID(Long sportiveID) {
        this.sportiveID = sportiveID;
    }

    public Long getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Long trainerID) {
        this.trainerID = trainerID;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "SportiveTrainer{" +
                "id=" + this.getId() +
                ", sportiveID=" + sportiveID +
                ", trainerID=" + trainerID +
                ", cost=" + cost +
                ", trainingType='" + trainingType + '\'' +
                '}';
    }
}
