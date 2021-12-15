package fr.eql.al36.soa.tp.webservices.dto;

import fr.eql.al36.soa.tp.webservices.entity.Currency;

import java.util.ArrayList;
import java.util.List;

public class DTOUtil {
    public static List<CurrencyDTO> currenciesToDTO(List<Currency> currencies) {
        List<CurrencyDTO> currenciesDTO = new ArrayList<>();
        for (Currency c : currencies) {
            currenciesDTO.add(new CurrencyDTO(c.getTicker(),c.getName(),c.getValue()));
        }
        return currenciesDTO;
    }
}
