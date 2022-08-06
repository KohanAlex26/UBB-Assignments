package domain.entities;

public class TeamDecorator implements TeamInterface {
    public TeamInterface teamInterface;

    public TeamDecorator(TeamInterface t)
    {
        this.teamInterface=t;
    }

    @Override
    public void setTeamName(String teamName)
    {
        this.teamInterface.setTeamName(teamName);
    }

    @Override
    public String getTeamName()
    {
        return this.teamInterface.getTeamName();
    }
}
