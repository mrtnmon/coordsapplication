package be.ausy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class Locatie {
    private String x;
    private String y;
    private String LAT;
    private String LONG;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLONG() {
        return LONG;
    }

    public void setLONG(String LONG) {
        this.LONG = LONG;
    }
}
