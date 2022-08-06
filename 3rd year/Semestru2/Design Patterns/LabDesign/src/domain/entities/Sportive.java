package domain.entities;


/**
 * Sportive entity
 *
 * @author alex.
 *
 */


public class Sportive extends BaseEntity<Long> implements SportiveInterface {
    private String firstName;
    private String lastName;
    private int age;
    private int teamId;

    /**
     * Constructs with default values
     */
    public Sportive() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.teamId = -1;
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
     * @param teamId
     *              must be a positive number
     */
    public Sportive(String firstName, String lastName, int age, int teamId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.teamId = teamId;
    }

    public Sportive(SportiveBuilder builder)
    {
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.age=builder.age;
        this.teamId=builder.teamId;
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
        return "Sportive{" +
                "id='" + this.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", teamId=" + teamId +
                '}';
    }


    ///BUILDER
    ///
    ///
    ///
    public static class SportiveBuilder
    {
        private String firstName;
        private String lastName;
        private int age;
        private int teamId;

        public SportiveBuilder(String firstName,String lastName)
        {
            this.firstName=firstName;
            this.lastName=lastName;
        }

        public SportiveBuilder age(int age)
        {
            this.age=age;
            return this;
        }

        public SportiveBuilder teamId(int teamId)
        {
            this.teamId=teamId;
            return this;
        }

        public Sportive build()
        {
            Sportive sportive=new Sportive(this);
            return sportive;
        }
    }


    //OBSERVER
    ///
    ///
    ///
    public void update()
    {
        System.out.println(this.toString()+" "+ "verify your email adress!");
    }


    ///FACADE
    ///
    ///
    ///
    @Override
    public void description()
    {
        System.out.println(this.toString()+" "+"I will pass, dribble and score");
    }
}
