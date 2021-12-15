package fr.eql.al36.soa.tp.webservices.init;

import fr.eql.al36.soa.tp.webservices.entity.Country;
import fr.eql.al36.soa.tp.webservices.entity.Currency;
import fr.eql.al36.soa.tp.webservices.service.CountryService;
import fr.eql.al36.soa.tp.webservices.service.CurrencyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("initDataSet") //to activate only if "initDataSet" profile is set at startup
public class InitDataSet {

    private final CurrencyService currencyService;
    private final CountryService countryService;

    public InitDataSet(CurrencyService currencyService, CountryService countryService) {
        this.currencyService = currencyService;
        this.countryService = countryService;
        this.initData();
    }

    public void initData() {
        Currency euro = Currency.builder().ticker("EUR").name("Euro").value(1).build();
        Currency dollar = Currency.builder().ticker("USD").name("Dollar").value(1.1).build();
        Currency pound = Currency.builder().ticker("GBP").name("Pound Sterling").value(0.9).build();
        Currency yen = Currency.builder().ticker("JPY").name("Japanese Yen").value(113).build();
        currencyService.save(euro);
        currencyService.save(dollar);
        currencyService.save(pound);
        currencyService.save(yen);

        countryService.save(Country.builder().code("FR").name("France").currency(euro).build());
        countryService.save(Country.builder().code("DE").name("Germany").currency(euro).build());
        countryService.save(Country.builder().code("ES").name("Spain").currency(euro).build());
        countryService.save(Country.builder().code("US").name("United States of America").currency(euro).build());
        countryService.save(Country.builder().code("GB").name("Great Britain").currency(euro).build());
        countryService.save(Country.builder().code("JP").name("Japan").currency(euro).build());
    }
}
