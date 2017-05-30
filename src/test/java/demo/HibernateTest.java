package demo;

import com.ballinapp.data.Team;
import java.util.ArrayList;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {
        Team team = new Team();
        team.setId(12L);
        team.setName("test team");
        team.setState("nemacka");
        team.setCity("frankfurt");
        team.setOpen(true);
        team.setAppearance_plus(11);
        team.setAppearance_minus(3);
        
        Team team2 = new Team();
        team2.setId(34L);
        team2.setName("manzane");
        team2.setState("francuska");
        team2.setCity("paris");
        team2.setOpen(true);
        team2.setAppearance_plus(34);
        team2.setAppearance_minus(23);
        
        List<Team> teamsList = new ArrayList<>();
        teamsList.add(team);
        teamsList.add(team2);
        
        Team[] teamsArray = new Team[teamsList.size()];
        teamsArray = teamsList.toArray(teamsArray);
        
        for(Team t : teamsArray) {
            System.out.println(t);
        }
        
        
    }
    
}
