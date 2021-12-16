package fr.eql.al36.soa.tp.webservices.delegate;

import fr.eql.al36.soa.tp.webservices.entity.Currency;

import java.util.List;

public interface RefreshCurrency {
    public List<Currency> retreiveRecentCurrencyValues();
    public List<Currency> refreshCurrencyValuesInDataBase();
}