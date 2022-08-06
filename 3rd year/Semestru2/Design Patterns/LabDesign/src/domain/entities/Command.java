package domain.entities;

public class Command {
    private SnapshotTrainer backup;
    private Trainer trainer;

    public Command(Trainer trainer) {
        this.trainer = trainer;
    }

    public void makeBackup(){
        backup = trainer.createSnapshot();
    }

    public void undo(){
        if(backup != null){
            backup.restore();
        }
    }

    @Override
    public String toString() {
        return "Command{" +
                "backup=" + backup +
                ", trainer=" + trainer +
                '}';
    }
}
