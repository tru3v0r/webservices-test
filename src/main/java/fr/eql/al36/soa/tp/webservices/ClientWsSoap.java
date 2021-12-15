package fr.eql.al36.soa.tp.webservices;

import fr.eql.al36.soa.tp.webservices.client.CalculTVABusinessDelegate;
import fr.eql.al36.soa.tp.webservices.service.CalculTVA;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientWsSoap {

    public ClientWsSoap() {

    }
    public static void main(String[] args) {

        CalculTVA calculTVA = new CalculTVABusinessDelegate();
        double ttc = calculTVA.ttc(200,20);
        System.out.println("ttc=" + ttc);
    }
}
