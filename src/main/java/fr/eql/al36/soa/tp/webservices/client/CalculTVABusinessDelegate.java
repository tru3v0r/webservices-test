package fr.eql.al36.soa.tp.webservices.client;

import fr.eql.al36.soa.tp.webservices.dto.ResCalculTVA;
import fr.eql.al36.soa.tp.webservices.service.CalculTVA;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class CalculTVABusinessDelegate implements CalculTVA {

    private CalculTVA calculateurWSProxy = null;

    public CalculTVABusinessDelegate() {
        QName SERVICE_NAME = new QName("http://service.webservices.tp.soa.al36.eql.fr/", "CalculTVAImplService");
        QName PORT_NAME = new QName("http://service.webservices.tp.soa.al36.eql.fr/", "CalculTVAImplPort");
        // en précisant une URL WSDL connue et accessible
        String serverHost = "localhost";
        String wsdlUrl = "http://" + serverHost + ":8484/webservices/soap/calculTVA?wsdl";
        URL wsdlDocumentLocation = null;

        try {
            wsdlDocumentLocation = new URL(wsdlUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //avec import javax.xml.ws.Service;
        Service service = Service.create(wsdlDocumentLocation, SERVICE_NAME);
        calculateurWSProxy = (CalculTVA) service.getPort(PORT_NAME, CalculTVA.class);
    }

    @Override
    public double tva(double ht, double taux) {
        return calculateurWSProxy.tva(ht, taux);
    }

    @Override
    public double ttc(double ht, double taux) {
        return calculateurWSProxy.ttc(ht, taux);
    }

    @Override
    public String getAuteur() {
        return null;
    }

    @Override
    public ResCalculTVA TVAEtTTC(double ht, double taux) {
        return null;
    }
}
