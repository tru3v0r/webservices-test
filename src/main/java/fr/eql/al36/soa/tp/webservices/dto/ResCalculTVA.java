package fr.eql.al36.soa.tp.webservices.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//DTO : Data Transfer Object
//Sur version WS SOAP : sera converti en XML via JAX-WS et JAXB2 (javax.xml.bind)
//Sur version WS REST : sera converti en JSON via jackson-databind

@XmlType(namespace = "http://dto.webservices.tp.soap.al36.eql.fr/") //namespace xml (package à l'envers)
@XmlRootElement(name = "resCalculTVA") //nom de balise xml (pour conv java --> xml)
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ResCalculTVA {

    private double tva;
    private double ttc;
}
