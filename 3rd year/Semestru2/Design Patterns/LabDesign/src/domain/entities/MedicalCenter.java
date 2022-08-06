package domain.entities;

public class MedicalCenter extends TeamDecorator {
    private String name;
    private String street;
    private int numberOfDoctors;

    public MedicalCenter(TeamInterface t, String name, String street, int numberOfDoctors) {
        super(t);
        this.name = name;
        this.street = street;
        this.numberOfDoctors = numberOfDoctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumberOfDoctors() {
        return numberOfDoctors;
    }

    public void setNumberOfDoctors(int numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    @Override
    public String toString() {
        return "MedicalCenter{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", numberOfDoctors=" + numberOfDoctors +
                ", teamInterface=" + teamInterface +
                '}';
    }
}
