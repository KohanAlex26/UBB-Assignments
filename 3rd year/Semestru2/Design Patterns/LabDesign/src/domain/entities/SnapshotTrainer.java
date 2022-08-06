package domain.entities;

public class SnapshotTrainer {
    private Trainer trainer;
    private String firstName;
    private String lastName;
    private int age;

    public SnapshotTrainer(Trainer trainer, String firstName, String lastName, int age)
    {
        this.trainer=trainer;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
    }

    public void restore()
    {
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setAge(age);
    }

}
