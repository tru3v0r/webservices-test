package fr.eql.al36.soa.tp.webservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Currency {

    @Id
    private String ticker;
    private String name;
    private double value;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    List<Country> countries;


    @Override
    public String toString() {
        return "Currency{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
