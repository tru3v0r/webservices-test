package fr.eql.al36.soa.tp.webservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Builder
public class Country {

    @Id
    private String code;
    private String name;


    @ManyToOne
    @JoinColumn(name = "currency_ticker")
    @JsonIgnore
    private Currency currency;

    public Country(String code, String name, Currency currency) {
        this.code = code;
        this.name = name;
        this.currency = currency;
    }

}
