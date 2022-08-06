package domain.entities;

public class Goalkeeper extends BaseEntity<Long> implements SportiveInterface {

    private String firstName;
    private String lastName;
    private int age;
    private int teamId;

    @Override
    public void description() {
        System.out.println(this.toString()+" "+"I will defend the gate");
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Goalkeeper{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", teamId=" + teamId +
                '}';
    }

    public Goalkeeper(String firstName, String lastName, int age, int teamId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.teamId = teamId;
    }
}
