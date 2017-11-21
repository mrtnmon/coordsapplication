package be.ausy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JSONCoordsPayload {
    private String politieZone;
    private LocatieFormaat locatieFormaat;
    protected Locatie locatie;
}
