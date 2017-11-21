package be.ausy.controller;

import be.ausy.model.CoordinatesRequest;
import be.ausy.model.Locatie;
import be.ausy.model.LocatieFormaat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CoordinateControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void postRequestShouldReturnResource() throws Exception {

        CoordinatesRequest payload = CoordinatesRequest.builder()
                .politieZone("ANW")
                .locatieFormaat(LocatieFormaat.GPS)
                .locatie(Locatie.builder()
                        .LAT("N 1 1 1")
                        .LONG("E 10 50 12.54321")
                        .build())
                .build();


        this.mockMvc
                .perform(post("/requestmap")
                        .content(asJsonString(payload))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

