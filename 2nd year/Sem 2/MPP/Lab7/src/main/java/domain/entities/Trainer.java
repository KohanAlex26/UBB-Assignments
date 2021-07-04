package domain.entities;

import javax.persistence.Entity;

/**
 * Sportive entity
 *
 * @author oprea.
 *
 */

@Entity
public class Trainer extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private int age;

    /**
     * Constructs with default values
     */
    public Trainer(){
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
    }

    /**
     * Constructs with given arguments
     *
     * @param firstName
     *              must have at least 1 character
     * @param lastName
     *              must have at least 1 character
     * @param age
     *              must be a positive number
     */
    public Trainer(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    @Override
    public String toString(){
        return "Trainer{" +
                "id='" + this.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
