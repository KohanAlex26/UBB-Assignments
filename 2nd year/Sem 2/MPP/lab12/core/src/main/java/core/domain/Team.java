package core.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@NamedEntityGraphs({
        @NamedEntityGraph(name = "teamsWithSportives", attributeNodes = @NamedAttributeNode(value = "sportives"))
})
@Entity
public class Team extends BaseEntity<Long> {
    private String teamName;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sportive> sportives;

    public Team() {
    }

    public Team(Long id, String teamName) {
        this.setId(id);
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Sportive> getSportives() {
        return sportives;
    }

    public void setSportives(List<Sportive> sportives) {
        this.sportives = sportives;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Team{" +
                "teamName='" + teamName + '\'' +
                '}';
    }


}
