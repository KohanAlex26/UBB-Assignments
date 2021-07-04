package core.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

//@Entity
@Embeddable
public class Award{
    String date;

    public Award(Long id,String date)
    {
//        this.setId(id);
        this.date=date;
    }
    public Award(){}

    public Award(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Award{" +
                "date='" + date + '\'' +
                '}';
    }
}
