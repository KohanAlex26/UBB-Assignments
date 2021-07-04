package domain.entities;


import javax.persistence.Entity;

/**
 * Sportive entity
 *
 * @author alex.
 *
 */

@Entity
public class Sportive extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private int age;
    private int tid;

    /**
     * Constructs with default values
     */
    public Sportive() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.tid = -1;
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
     * @param tid
     *              must be a positive number
     */
    public Sportive(String firstName, String lastName, int age, int tid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.tid = tid;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int teamId) {
        this.tid = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sportive sportive = (Sportive) o;

        if (tid != sportive.tid) return false;
        if (!lastName.equals(sportive.lastName)) return false;
        return age==sportive.age;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age;
        result = 31 * result + tid;
        return result;
    }

    @Override
    public String toString() {
        return "Sportive{" +
                "id='" + this.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", teamId=" + tid +
                '}';
    }
}
