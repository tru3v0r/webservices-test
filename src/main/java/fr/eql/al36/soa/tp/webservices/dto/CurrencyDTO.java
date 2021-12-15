package fr.eql.al36.soa.tp.webservices.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor
public class CurrencyDTO implements Serializable {
    private String ticker;
    private String name;
    private double value;

    public CurrencyDTO(String ticker, String name, double value) {
        this.ticker = ticker;
        this.name = name;
        this.value = value;
    }
}
