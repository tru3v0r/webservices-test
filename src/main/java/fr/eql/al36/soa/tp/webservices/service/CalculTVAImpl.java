package fr.eql.al36.soa.tp.webservices.service;

import fr.eql.al36.soa.tp.webservices.dto.ResCalculTVA;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service //ou bien @Component (de Spring)
@WebService(endpointInterface = "fr.eql.al36.soa.tp.webservices.service.CalculTVA")
public class CalculTVAImpl implements CalculTVA {

    @Override
    public double tva(double ht, double taux) {
        return ht * taux/100;
    }

    @Override
    public double ttc(double ht, double taux) {
        return ht * (1 + taux/100);
    }

    @Override
    public String getAuteur() {
        return "Valentin";
    }

    @Override
    public ResCalculTVA TVAEtTTC(double ht, double taux) {
        return new ResCalculTVA(tva(ht, taux), ttc(ht, taux));
    }
}
