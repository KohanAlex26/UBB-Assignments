package core.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by radu.
 */
@Entity
public class Trainer extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private int age;

    @Embedded
    @JoinColumn(name = "awardid")
    private Award award;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SportiveTrainer> sportiveTrainers;

    public Trainer()
    {
    }

    public Trainer(Long id,String firstName,String lastName,int age)
    {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }

//    public void addSportiveTrainer(Sportive sportive, int cost, String trainingType)
//    {
//        SportiveTrainer st=new SportiveTrainer(sportive,this,cost,trainingType);
//        sportiveTrainer.add(st);
//    }

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

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", award=" + award +
                ", sportiveTrainer=" + sportiveTrainers +
                '}';
    }
}
