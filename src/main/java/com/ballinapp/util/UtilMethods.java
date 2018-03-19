package com.ballinapp.util;

import com.ballinapp.data.info.PlayerInfo;
import com.ballinapp.data.info.PublicGameInfo;
import com.ballinapp.data.info.RequestInfo;
import com.ballinapp.data.info.TeamInfo;
import com.ballinapp.data.model.GameRequestMain;

/**
 * User: dusan <br/> Date: 3/11/18
 */
public class UtilMethods {

    public static boolean validateGameOrRequestData(GameRequestMain object) {
        String message = object.getMessage();
        String contact = object.getContact();
        String state = object.getState();
        String city = object.getCity();
        String address = object.getAddress();
        String date = object.getDate();
        String time = object.getTime();

        boolean msg = false;
        boolean cnt = false;
        boolean sta = false;
        boolean cit = false;
        boolean add = false;
        boolean dat = false;
        boolean tim = false;

        if(!message.isEmpty()) {
            msg = true;
        }

        if(!contact.isEmpty() && contact.length() < 35) {
            cnt = true;
        }

        if (state.length() < 25 && !state.isEmpty()) {
            int character = 0;
            for (int i = 0; i < state.length(); i++) {
                if (Character.isDigit(state.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                sta = true;
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
                cit = true;
            }
        }

        if(!address.isEmpty() && address.length() < 35) {
            add = true;
        }

        if(!date.isEmpty() && date.length() < 20) {
            dat = true;
        }

        if (!time.isEmpty() && time.length() < 6) {
            int letter = 0;
            for(int i = 0; i < time.length(); i++) {
                if(Character.isLetter(time.charAt(i))) {
                    letter++;
                }
            }
            if(letter == 0) {
                tim = true;
            }
        }

        return msg && cnt && sta && cit && add & dat & tim;
    }

    public static boolean validateGameOrRequestData(RequestInfo object) {
        String message = object.getMessage();
        String contact = object.getContact();
        String state = object.getState();
        String city = object.getCity();
        String address = object.getAddress();
        String date = object.getDate();
        String time = object.getTime();

        boolean msg = false;
        boolean cnt = false;
        boolean sta = false;
        boolean cit = false;
        boolean add = false;
        boolean dat = false;
        boolean tim = false;

        if(!message.isEmpty()) {
            msg = true;
        }

        if(!contact.isEmpty() && contact.length() < 35) {
            cnt = true;
        }

        if (state.length() < 25 && !state.isEmpty()) {
            int character = 0;
            for (int i = 0; i < state.length(); i++) {
                if (Character.isDigit(state.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                sta = true;
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
                cit = true;
            }
        }

        if(!address.isEmpty() && address.length() < 35) {
            add = true;
        }

        if(!date.isEmpty() && date.length() < 20) {
            dat = true;
        }

        if (!time.isEmpty() && time.length() < 6) {
            int letter = 0;
            for(int i = 0; i < time.length(); i++) {
                if(Character.isLetter(time.charAt(i))) {
                    letter++;
                }
            }
            if(letter == 0) {
                tim = true;
            }
        }

        return msg && cnt && sta && cit && add & dat & tim;
    }

    public static boolean validateGameOrRequestData(PublicGameInfo object) {
        String message = object.getMessage();
        String contact = object.getContact();
        String state = object.getState();
        String city = object.getCity();
        String address = object.getAddress();
        String date = object.getDate();
        String time = object.getTime();

        boolean msg = false;
        boolean cnt = false;
        boolean sta = false;
        boolean cit = false;
        boolean add = false;
        boolean dat = false;
        boolean tim = false;

        if(!message.isEmpty()) {
            msg = true;
        }

        if(!contact.isEmpty() && contact.length() < 35) {
            cnt = true;
        }

        if (state.length() < 25 && !state.isEmpty()) {
            int character = 0;
            for (int i = 0; i < state.length(); i++) {
                if (Character.isDigit(state.charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                sta = true;
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
                cit = true;
            }
        }

        if(!address.isEmpty() && address.length() < 35) {
            add = true;
        }

        if(!date.isEmpty() && date.length() < 20) {
            dat = true;
        }

        if (!time.isEmpty() && time.length() < 6) {
            int letter = 0;
            for(int i = 0; i < time.length(); i++) {
                if(Character.isLetter(time.charAt(i))) {
                    letter++;
                }
            }
            if(letter == 0) {
                tim = true;
            }
        }

        return msg && cnt && sta && cit && add & dat & tim;
    }

    public static String normalizeTimestamp(String timestamp) {
        String[] split = timestamp.split(" ");

        String dates = split[0];
        String year = dates.substring(0, 4);
        String month = dates.substring(5, 7);
        String day = dates.substring(8, 10);

        switch(month) {
            case "01":
                month = "Jan";
                break;
            case "02":
                month = "Feb";
                break;
            case "03":
                month = "Mar";
                break;
            case "04":
                month = "Apr";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "Jun";
                break;
            case "07":
                month = "Jul";
                break;
            case "08":
                month = "Aug";
                break;
            case "09":
                month = "Sep";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            case "12":
                month = "Dec";
                break;
        }

        if(day.charAt(0) == '0') {
            day = day.substring(1);
        }

        String times = split[1].substring(0, 5);

        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append(". ");
        sb.append(month);
        sb.append(" ");
        sb.append(year);
        sb.append(". ");
        sb.append(times);
        sb.append("h");

        return sb.toString();
    }

    public static boolean validateUpdateTeam(TeamInfo team) {
        boolean teamName = false;
        boolean teamState = false;
        boolean teamCity = false;
        boolean teamEmail = false;

        if (team.getName().length() < 25 && !team.getName().isEmpty()) {
            teamName = true;
        }

        if (team.getState().length() < 25 && !team.getState().isEmpty()) {
            int character = 0;
            for (int i = 0; i < team.getState().length(); i++) {
                if (Character.isDigit(team.getState().charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                teamState = true;
            }
        }

        if (team.getCity().length() < 20 && !team.getCity().isEmpty()) {
            int character = 0;
            for (int i = 0; i < team.getCity().length(); i++) {
                if (Character.isDigit(team.getCity().charAt(i))) {
                    character++;
                }
            }
            if (character == 0) {
                teamCity = true;
            }
        }

        if (!team.getEmail().isEmpty()) {
            int at = 0;
            int dot = 0;
            for (int i = 0; i < team.getEmail().length(); i++) {
                if (team.getEmail().charAt(i) == '@') {
                    at++;
                } else if (team.getEmail().charAt(i) == '.') {
                    dot++;
                }
            }
            if (at > 0 && dot > 0) {
                teamEmail = true;
            }
        }

        return teamName && teamState && teamCity && teamEmail;
    }

    public static boolean validatePlayer(PlayerInfo player) {
        boolean nickname = false;
        boolean birthyear = false;

        if(!player.getNickname().isEmpty() && player.getNickname().length() < 20) {
            nickname = true;
        }

        String byStr = String.valueOf(player.getBirthyear());

        if(!byStr.isEmpty() && byStr.length() == 4) {
            int chr = 0;
            for(int i = 0; i < byStr.length(); i++) {
                if(Character.isLetter(byStr.charAt(i))) {
                    chr++;
                }
            }
            if(chr == 0) {
                birthyear = true;
            }
        }
        return nickname && birthyear;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

}
