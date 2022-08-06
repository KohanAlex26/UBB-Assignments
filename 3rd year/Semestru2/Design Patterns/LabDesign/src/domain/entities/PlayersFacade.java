package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class PlayersFacade {
    private List<Sportive> sportives=new ArrayList<Sportive>();
    private Goalkeeper goalkeeper;

    public PlayersFacade()
    {
//        Sportive s1 = new Sportive("a","a",1,1);
//        s1.setId(1L);
//        Sportive s2 = new Sportive("b","b",2,2);
//        s2.setId(2L);
//        Sportive s3 = new Sportive("c","c",22,2);
//        s3.setId(3L);
//        Sportive s4 = new Sportive("d","d",5,1);
//        s4.setId(4L);
//        Sportive s5 = new Sportive("j","k",40,3);
//        s5.setId(5L);
//        sportives.add(s1);
//        sportives.add(s2);
//        sportives.add(s3);
//        sportives.add(s4);
//        sportives.add(s5);
//        goalkeeper=new Goalkeeper("z","z",35,1);
//        goalkeeper.setId(6L);
    }

    public void addToSportiveList(Sportive s)
    {
        sportives.add(s);
    }

    public void createNewGoalKeeper(Goalkeeper g)
    {
        goalkeeper=g;
    }

    public void presentSportives(){
        for(Sportive s:sportives)
            s.description();
    }

    public void presentGoalKeeper(){
        try {
            goalkeeper.description();
        }
        catch(NullPointerException e)
        {
            System.out.println("no goalkeeper yet");
        }
    }
}
