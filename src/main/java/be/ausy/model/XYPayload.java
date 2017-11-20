package be.ausy.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class XYPayload {
    private String politieZone;
    private LocatieFormaat locatieFormaat;
    private XYLocatie xyLocatie;

}
