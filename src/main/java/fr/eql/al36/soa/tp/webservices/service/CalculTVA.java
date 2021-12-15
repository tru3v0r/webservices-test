package fr.eql.al36.soa.tp.webservices.service;

import fr.eql.al36.soa.tp.webservices.dto.ResCalculTVA;
import org.springframework.context.annotation.Bean;

import javax.jws.WebParam;
import javax.jws.WebService;

//@WebService(targetNamespace = "http://service.webservices.tp.soa.al36.eql.fr/")
@WebService(targetNamespace = "http://service.webservices.tp.soa.al36.eql.fr/")
public interface CalculTVA {
    //WebParam pour bien générer WSDL
    double tva(@WebParam(name = "ht") double ht, @WebParam(name = "taux") double taux);
    double ttc(@WebParam(name = "ht") double ht, @WebParam(name = "taux") double taux);
    String getAuteur();
    ResCalculTVA TVAEtTTC(@WebParam(name = "ht") double ht,
                          @WebParam(name = "taux") double taux);
}
