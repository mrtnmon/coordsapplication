package be.ausy.controller;

import be.ausy.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Controller
@RequestMapping("/")
public class CoordinateController {

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.POST,
            path = "/map",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] map(@RequestBody JSONCoordsPayload payload) throws IOException {
        StringBuilder url =
                new StringBuilder();

        if (payload.getLocatieFormaat().equals(LocatieFormaat.GPS)){
            String splittedLAT[]= payload.getLocatie().getLAT().split("\\s+");
            String splittedLONG[]= payload.getLocatie().getLONG().split("\\s+");
            double x = CoordinateController.GPSToDecimal(Integer.valueOf(splittedLAT[1]), Integer.valueOf(splittedLAT[2]), Double.valueOf(splittedLAT[3]), splittedLAT[0]);
            double y = CoordinateController.GPSToDecimal(Integer.valueOf(splittedLONG[1]), Integer.valueOf(splittedLONG[2]), Double.valueOf(splittedLONG[3]), splittedLONG[0]);
            Locatie locatie = new Locatie();
            locatie.setX(String.valueOf(x));
            locatie.setY(String.valueOf(y));
            payload.setLocatie(locatie);
        }
        url.append("https://maps.googleapis.com/maps/api/staticmap?maptype=roadmap&center=")
                .append(payload.getLocatie().getX())
                .append(",").append(payload.getLocatie().getY())
                .append("&zoom=16&size=900x400&key=")
                .append(env.getProperty("maps.api.key"));


        URL urlObject = new URL(url.toString());
        URLConnection urlConnection = urlObject.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        return IOUtils.toByteArray(inputStream);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/without")
    public @ResponseBody
    Object testEndpoint(@RequestBody XYPayload payload) {
        return restTemplate.getForObject("https://maps.googleapis.com/maps/api/staticmap?maptype=satellite&center=37.530101,38.600062&zoom=14&size=640x400&key=AIzaSyBhgrftfI1Tv4lzUEvnoBbkk_vP_bEE7EA", Object.class);
    }


//    https://maps.googleapis.com/maps/api/staticmap?
//    // maptype=satellite&center=37.530101,38.600062&zoom=14&size=640x400&key=YOUR_API_KEY


    public static double GPSToDecimal(int grados, int minutos, double segundos, String direccion) {

        double decimal = Math.signum(grados) * (Math.abs(grados) + (minutos / 60.0) + (segundos / 3600.0));

        //reverse for south or west coordinates; north is assumed
        if (direccion.equals("S") || direccion.equals("W")) {
            decimal *= -1;
        }

        return decimal;
    }

    public String[] SeparararDMS(String coordenada, int type) {

        String grados = null;
        String minutos = null;
        String segundos = null;
        String direccion = null;


        switch (type) {
            case 1: // latitude
                grados = coordenada.substring(0, 2);
                minutos = coordenada.substring(2, 4);
                segundos = coordenada.substring(5, coordenada.length() - 1);
                break;
            case 2: // longitude
                grados = coordenada.substring(0, 3);
                minutos = coordenada.substring(3, 5);
                segundos = coordenada.substring(6, coordenada.length() - 1);
                break;
            default:

        }

        double sec = 0;
        try {
            sec = Double.parseDouble("0." + segundos);
        } catch (Exception e) {

        }


        sec = (sec * 60);
        direccion = coordenada.substring(coordenada.length() - 1);

        String[] data = {grados, minutos, sec + "", direccion};
        return data;
    }

}
