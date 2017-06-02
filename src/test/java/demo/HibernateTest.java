package demo;

import com.ballinapp.data.Player;
import com.ballinapp.data.Team;

public class HibernateTest {

    public static void main(String[] args) {
        Player player = new Player("dusan", 1994, "0693228822");
        System.out.println(validatePlayer(player));
    }
    
    public static boolean validatePlayer(Player player) {
        String nickname = player.getNickname();
        int birthyear = player.getBirthyear();
        String byString = String.valueOf(birthyear);
        String contact = player.getContact();
        
        boolean nick = false;
        boolean year = false;
        boolean con = false;
        
        
        if(!nickname.isEmpty() && nickname.length() < 20) {
            nick = true;
        }
        
        if(!byString.isEmpty() && byString.length() == 4) {
            int chr = 0;
            for(int i = 0; i < byString.length(); i++) {
                if(Character.isLetter(byString.charAt(i))) {
                    chr++;
                }
            }
            if(chr == 0) {
                year = true;
            }
        }
        
        if(!contact.isEmpty() && contact.length() < 35) {
            con = true;
        }
        
        return nick && year && con;
        
    }

    public static boolean validateTeam(Team team) {
        String name = team.getName();
        String state = team.getState();
        String city = team.getCity();
        String email = team.getEmail();

        boolean nameB = false;
        boolean stateB = false;
        boolean cityB = false;
        boolean emailB = false;

        if (!name.isEmpty()) {
            nameB = true;
        }

        if (state.length() < 25 && !state.isEmpty()) {
            int character = 0;
            for (int i = 0; i < state.length(); i++) {
                if (Character.isDigit(state.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                stateB = true;
            }
        }

        if (city.length() < 20 && !city.isEmpty()) {
            int character = 0;
            for (int i = 0; i < city.length(); i++) {
                if (Character.isDigit(city.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                cityB = true;
            }
        }
        
        if(!email.isEmpty()) {
            int at = 0;
            int dot = 0;
            for(int i = 0; i < email.length(); i++) {
                if(email.charAt(i) == '@') {
                    at++;
                }
                
                if(email.charAt(i) == '.') {
                    dot++;
                }
            }
            if(at == 1 && dot > 0) {
                emailB = true;
            }
        }

        return nameB && stateB && cityB && emailB;
    }

}
