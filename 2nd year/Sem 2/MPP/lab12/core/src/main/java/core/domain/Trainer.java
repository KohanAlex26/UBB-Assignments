package core.domain;

import org.apache.poi.ss.formula.functions.T;

import javax.persistence.*;
import java.util.*;

/**
 * Created by radu.
 */
@Entity
public class Trainer extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private int age;

    @Embedded
//    @JoinColumn(name = "awardid")
    private Award award;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Trainer(Long id,String firstName,String lastName,int age, Award award)
    {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.award=award;
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

    public Set<SportiveTrainer> getSportiveTrainers() {
        sportiveTrainers = sportiveTrainers == null ? new HashSet<>() : sportiveTrainers;
        return Collections.unmodifiableSet(sportiveTrainers); }

    public void addSportiveTrainer(Sportive sportive, int cost, String trainingType) {
        SportiveTrainer sportiveTrainer = new SportiveTrainer(new SportiveTrainerPrimaryKey(sportive.getId(), this.getId()), sportive, this, cost, trainingType);
        sportiveTrainers.add(sportiveTrainer);
    }

    public void setSportiveTrainers(Set<SportiveTrainer> sportiveTrainers) {
        this.sportiveTrainers = sportiveTrainers;
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
        return super.toString() +
                "Trainer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Trainer && this.getId().equals(((Trainer) obj).getId());
    }
}
