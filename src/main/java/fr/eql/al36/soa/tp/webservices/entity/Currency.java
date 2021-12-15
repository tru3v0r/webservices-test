package fr.eql.al36.soa.tp.webservices.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Currency {

    @Id
    private String ticker;
    private String name;
    private double value;

}
