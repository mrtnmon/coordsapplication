package be.ausy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties
@Data
@Builder
public class Locatie {
    private String x;
    private String y;
    private String LAT;
    private String LONG;
}
