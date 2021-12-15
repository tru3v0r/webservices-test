package fr.eql.al36.soa.tp.webservices.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString @Builder
public class GenericMessage {

    public String message;
    public String details;

    public GenericMessage(String message) {
        this.message = message;
    }
}
