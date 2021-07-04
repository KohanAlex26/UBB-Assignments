package core.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedEntityGraphs(
        @NamedEntityGraph(name="sportivesWithTeams", attributeNodes = @NamedAttributeNode(value = "team"))
)
@Entity
public class Sportive extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "sportive", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SportiveTrainer> sportiveTrainers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Sportive(){
    }

    public Sportive(Long id,String firstName,String lastName,int age)
    {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }

    public Sportive(Long id,String firstName,String lastName,int age,Team team)
    {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.team=team;
    }

    public void addSportiveTrainer(Trainer trainer, int cost, String trainingType)
    {
        SportiveTrainer sportiveTrainer = new SportiveTrainer(new SportiveTrainerPrimaryKey(this.getId(), trainer.getId()), this, trainer, cost, trainingType);
        sportiveTrainers.add(sportiveTrainer);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<SportiveTrainer> getSportiveTrainer() {
        return sportiveTrainers;
    }

    public void setSportiveTrainer(Set<SportiveTrainer> sportiveTrainer) {
        this.sportiveTrainers = sportiveTrainer;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Sportive{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}

