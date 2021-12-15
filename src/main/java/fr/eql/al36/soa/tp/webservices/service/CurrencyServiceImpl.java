package fr.eql.al36.soa.tp.webservices.service;

import fr.eql.al36.soa.tp.webservices.dao.CurrencyDAO;
import fr.eql.al36.soa.tp.webservices.entity.Currency;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyDAO currencyDAO;

    public CurrencyServiceImpl(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    @Override
    public Currency getByTicker(String ticker) {
        return currencyDAO.findById(ticker).orElse(null);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyDAO.findAll();
    }

    @Override
    public void deleteByTicker(String ticker) {
        currencyDAO.deleteById(ticker);
    }

    @Override
    public void save(Currency currency) {
        currencyDAO.save(currency);
    }

}
