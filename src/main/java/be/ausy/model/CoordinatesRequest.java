package be.ausy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesRequest {
    private String politieZone;
    private LocatieFormaat locatieFormaat;
    protected Locatie locatie;
}
