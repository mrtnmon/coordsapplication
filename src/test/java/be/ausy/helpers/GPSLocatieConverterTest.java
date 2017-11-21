package be.ausy.helpers;

import be.ausy.helper.GPSLocatieConverter;
import be.ausy.model.CoordinatesRequest;
import be.ausy.model.Locatie;
import be.ausy.model.LocatieFormaat;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GPSLocatieConverterTest {


    GPSLocatieConverter locatieConverter =  GPSLocatieConverter.getInstance();

    @Test
    public void GPSLocatieConverterReturnsCorrectLocatie(){

        CoordinatesRequest payload = CoordinatesRequest.builder()
                .politieZone("ANW")
                .locatieFormaat(LocatieFormaat.GPS)
                .locatie(Locatie.builder()
                        .LAT("N 1 1 1")
                        .LONG("E 10 50 12.54321")
                        .build())
                .build();

        payload.setLocatie(locatieConverter.convertLocatie(payload));

        CoordinatesRequest expectedPayload = CoordinatesRequest.builder()
                .politieZone("ANW")
                .locatieFormaat(LocatieFormaat.GPS)
                .locatie(Locatie.builder()
                        .x("1.0169444")
                        .y("10.8368176")
                        .build())
                .build();

        assertThat(payload.getLocatie().getX()).isEqualTo(expectedPayload.getLocatie().getX());
        assertThat(payload.getLocatie().getY()).isEqualTo(expectedPayload.getLocatie().getY());
    }
}
