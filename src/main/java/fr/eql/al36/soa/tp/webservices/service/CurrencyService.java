package fr.eql.al36.soa.tp.webservices.service;

import fr.eql.al36.soa.tp.webservices.entity.Currency;
import java.util.List;
import java.util.Optional;

//runtimeException-inheriting exceptions if encountering problems
public interface CurrencyService {

    Currency getByTicker(String ticker);
    List<Currency> getAllCurrencies();
    void deleteByTicker(String ticker);
    void save(Currency currency);
    //...
}
