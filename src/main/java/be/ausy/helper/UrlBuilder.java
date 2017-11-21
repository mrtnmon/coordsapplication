package be.ausy.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class UrlBuilder {

    @Autowired
    Environment env;

    public String getUrl(String xCoordinate, String yCoordinate){
        StringBuilder url = new StringBuilder();
        url.append(env.getProperty("maps.api.url"))
                .append(xCoordinate)
                .append(",").append(yCoordinate)
                .append("&zoom=16&size=900x400&key=")
                .append(env.getProperty("maps.api.key"));
        return url.toString();
    }

    public String getUrl(String xCoordinate, String yCoordinate, int ySize, int xSize, int zoom){
        StringBuilder url = new StringBuilder();
        url.append(env.getProperty("maps.api.url"))
                .append(xCoordinate)
                .append(",")
                .append(yCoordinate)
                .append("&zoom=" + zoom)
                .append("&size=" + ySize + "x" + xSize)
                .append("&key=")
                .append(env.getProperty("maps.api.key"));
        return url.toString();
    }
}
