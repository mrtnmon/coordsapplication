package be.ausy.controller;

import be.ausy.model.JSONCoordsPayload;
import be.ausy.model.Locatie;
import be.ausy.model.LocatieFormaat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoordinateControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postRequestShouldReturnByteArray() throws Exception {

        JSONCoordsPayload payload = JSONCoordsPayload.builder()
                .politieZone("ANW")
                .locatieFormaat(LocatieFormaat.GPS)
                .locatie(Locatie.builder()
                        .LAT("N 1 1 1")
                        .LONG("E 10 50 12.54321")
                        .build())
                .build();


        InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());


        assertThat(this.restTemplate.postForObject("http://localhost:" + this.port + "/map",
                payload, byte[].class)).isNotNull();
    }
}
