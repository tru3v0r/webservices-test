package fr.eql.al36.soa.tp.webservices.init;

import fr.eql.al36.soa.tp.webservices.entity.Currency;
import fr.eql.al36.soa.tp.webservices.service.CurrencyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("initDataSet") //to activate only if "initDataSet" profile is set at startup
public class InitDataSet {

    private final CurrencyService currencyService;

    public InitDataSet(CurrencyService currencyService) {
        this.currencyService = currencyService;
        this.initData();
    }

    public void initData() {
        currencyService.save(Currency.builder().ticker("EUR").name("Euro").value(1).build());
        currencyService.save(Currency.builder().ticker("USD").name("Dollar").value(1.1).build());
        currencyService.save(Currency.builder().ticker("GBP").name("Pound Sterling").value(0.9).build());
        currencyService.save(Currency.builder().ticker("JPY").name("Japanese Yen").value(113).build());
    }
}
