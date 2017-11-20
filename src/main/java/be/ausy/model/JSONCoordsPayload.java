package be.ausy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JSONCoordsPayload {
    private String politieZone;
    private LocatieFormaat locatieFormaat;
    protected Locatie locatie;

    public String getPolitieZone() {
        return politieZone;
    }

    public void setPolitieZone(String politieZone) {
        this.politieZone = politieZone;
    }

    public LocatieFormaat getLocatieFormaat() {
        return locatieFormaat;
    }

    public void setLocatieFormaat(LocatieFormaat locatieFormaat) {
        this.locatieFormaat = locatieFormaat;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }
}
