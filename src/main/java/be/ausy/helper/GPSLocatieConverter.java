package be.ausy.helper;

import be.ausy.model.JSONCoordsPayload;
import be.ausy.model.Locatie;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GPSLocatieConverter {

    private static GPSLocatieConverter instance;
    // Spring makes sure Beans/Components are Singletons, this is just for demonstrative purposes
    private GPSLocatieConverter (){}

    public static GPSLocatieConverter getInstance (){
        if (instance == null){
            instance = new GPSLocatieConverter();
        }
        return instance;
    }

    public Locatie convertLocatie(JSONCoordsPayload payload){
        String splittedLAT[]= payload.getLocatie().getLAT().split("\\s+");
        String splittedLONG[]= payload.getLocatie().getLONG().split("\\s+");
        double x = GPSToDecimal(Integer.valueOf(splittedLAT[1]), Integer.valueOf(splittedLAT[2]), Double.valueOf(splittedLAT[3]), splittedLAT[0]);
        double y = GPSToDecimal(Integer.valueOf(splittedLONG[1]), Integer.valueOf(splittedLONG[2]), Double.valueOf(splittedLONG[3]), splittedLONG[0]);
        Locatie locatie = Locatie.builder().x(String.valueOf(x)).y(String.valueOf(y)).build();
        return locatie;
    }

    public static double GPSToDecimal(int degrees, int minutes, double seconds, String direction) {

        double decimal = Math.signum(degrees) * (Math.abs(degrees) + (minutes / 60.0) + (seconds / 3600.0));

        //reverse for south or west coordinates; north is assumed
        if (direction.equals("S") || direction.equals("W")) {
            decimal *= -1;
        }

        decimal = new BigDecimal(decimal).setScale(7, BigDecimal.ROUND_HALF_UP).doubleValue();

        return decimal;
    }
}
