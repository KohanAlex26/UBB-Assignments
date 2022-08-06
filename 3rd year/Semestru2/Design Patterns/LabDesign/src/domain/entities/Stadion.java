package domain.entities;

public class Stadion extends TeamDecorator{
    private String name;
    private String dateOfConstruction;
    private int numberOfSeats;

    public Stadion(TeamInterface t,String name, String dateOfConstruction, int numberOfSeats) {
        super(t);
        this.name = name;
        this.dateOfConstruction = dateOfConstruction;
        this.numberOfSeats = numberOfSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfConstruction() {
        return dateOfConstruction;
    }

    public void setDateOfConstruction(String dateOfConstruction) {
        this.dateOfConstruction = dateOfConstruction;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Stadion{" +
                "name='" + name + '\'' +
                ", dateOfConstruction='" + dateOfConstruction + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", teamInterface=" + teamInterface +
                '}';
    }
}
