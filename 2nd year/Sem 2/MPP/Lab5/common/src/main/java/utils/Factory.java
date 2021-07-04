package utils;
import domain.entities.Sportive;
import domain.entities.SportiveTrainer;
import domain.entities.Team;
import domain.entities.Trainer;

public class Factory {
    public static String sportiveToString(Sportive sportive){
        return sportive.getId() + "," + sportive.getFirstName() + "," + sportive.getLastName() + "," + sportive.getAge() +"," + sportive.getTeamId();
    }

    public static String sportiveToStringId(Long id){
        return String.valueOf(id);
    }

    public static String trainerToString(Trainer trainer){
        return trainer.getId() + "," + trainer.getFirstName() + "," + trainer.getLastName() + "," + trainer.getAge();
    }

    public static String sportiveTrainerToString(SportiveTrainer st){
        return st.getId() + "," + st.getSportiveID() + "," + st.getTrainerID() + "," + st.getTrainingType() +"," + st.getCost();
    }

    public static Sportive messageToSportive(String message){
        String[] tokens = message.split(",");
        Sportive s= new Sportive(tokens[1], tokens[2], Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]));
        s.setId(Long.parseLong(tokens[0]));
        return s;
    }

    public static Long messageToSportiveId(String message){
        String[] tokens = message.split(",");
        return Long.parseLong(tokens[0]);
    }

    public static Trainer messageToTrainer(String message){
        String[] tokens = message.split(",");
        Trainer t= new Trainer(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
        t.setId(Long.parseLong(tokens[0]));
        return t;
    }

    public static SportiveTrainer messageToSportiveTrainer(String message){
        String[] tokens = message.split(",");
        SportiveTrainer st = new SportiveTrainer(Long.parseLong(tokens[1]), Long.parseLong(tokens[2]), tokens[3],Integer.parseInt(tokens[4]));
        st.setId(Long.parseLong(tokens[0]));
        return st;
    }


    public static String teamToString(Team team){
        return team.getId() + "," + team.getTeamName();
    }
    public static String teamToStringId(Long id){
        return String.valueOf(id);
    }

    public static Team messageToTeam(String message){
        String[] tokens = message.split(",");
        Team t= new Team(tokens[1]);
        t.setId(Long.parseLong(tokens[0]));
        return t;
    }
    public static Long messageToTeamId(String message){
        String[] tokens = message.split(",");
        return Long.parseLong(tokens[0]);
    }
}
