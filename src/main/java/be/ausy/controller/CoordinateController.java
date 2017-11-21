package be.ausy.controller;

import be.ausy.helper.GPSLocatieConverter;
import be.ausy.helper.UrlBuilder;
import be.ausy.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Controller
@RequestMapping("/")
public class CoordinateController {

    @Autowired
    UrlBuilder urlBuilder;

    @RequestMapping(method = RequestMethod.POST,
            path = "/requestmap",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    Resource getRequestedMap(@RequestBody JSONCoordsPayload payload) throws IOException {

        GPSLocatieConverter locatieConverter = GPSLocatieConverter.getInstance();

        if (payload.getLocatieFormaat().equals(LocatieFormaat.GPS)) {
            payload.setLocatie(locatieConverter.convertLocatie(payload));
        }

        URL urlObject = new URL(urlBuilder.getUrl(payload.getLocatie().getX(),payload.getLocatie().getY()));
        URLConnection urlConnection = urlObject.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        return new ByteArrayResource(IOUtils.toByteArray(inputStream));
    }
}
