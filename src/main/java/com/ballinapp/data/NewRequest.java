package com.ballinapp.data;

public class NewRequest {
    private int id;
    private String opponentName;
    private String message;
    private String contact;
    private String state;
    private String city;
    private String address;
    private String date;
    private String time;
    private String sentAt;
    private boolean status;

    public NewRequest(String opponentName, String message, String contact, String state, String city, String date, String time, String address, String sentAt, int id, boolean status) {
        this.opponentName = opponentName;
        this.message = message;
        this.contact = contact;
        this.state = state;
        this.city = city;
        this.address = address;
        this.date = date;
        this.time = time;
        this.sentAt = normalizeTimestamp(sentAt);
        this.id = id;
        this.status = status;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }
    
    private String normalizeTimestamp(String timestamp) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
