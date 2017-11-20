package be.ausy.model;


import lombok.Data;

@Data
public class GPSCoordinate {
    private int degrees;
    private int minutes;
    private int seconds;
    private String direction;


    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
