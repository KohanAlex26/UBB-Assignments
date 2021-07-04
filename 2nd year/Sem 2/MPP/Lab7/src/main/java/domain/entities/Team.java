package domain.entities;

import javax.persistence.Entity;

/**
 * Team entity
 *
 * @author Sebi.
 *
 */
@Entity
public class Team extends BaseEntity<Long>{
    String teamName;

    /**
     * Default constructor. Constructs with default values.
     */
    public Team(){
        this.teamName = "";
    }

    /**
     * Constructor with given arguments.
     * @param teamName
     *      must have a length of at least 1 charater
     */
    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=\'" + this.getId() + "\'" +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
