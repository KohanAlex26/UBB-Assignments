package domain.entities;

import java.util.HashSet;
import java.util.Set;

public class Subject {
    private Set<Sportive> observers=new HashSet<Sportive>();
    private int state;

    public void addObserver(Sportive sportive)
    {
        observers.add(sportive);
    }
    public void removeObserver(Sportive sportive)
    {
        observers.remove(sportive);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void notifyAllObservers()
    {
        for(Sportive sportive: observers)
        {
            sportive.update();
        }
    }
}
