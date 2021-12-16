package fr.eql.al36.soa.tp.webservices.delegate;

import java.util.ArrayList;
import java.util.List;

import fr.eql.al36.soa.tp.webservices.dto.FixerIoResponse;
import fr.eql.al36.soa.tp.webservices.entity.Currency;
import fr.eql.al36.soa.tp.webservices.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class RefreshCurrencyImpl implements RefreshCurrency {

    private final WebClient webClient;
    private final CurrencyService currencyService;

    private String tempApiKey="26ca93ee7fc19cbe0a423aaa27cab235";//formateur didier defrance
    private String fixerApiUrl="http://data.fixer.io/api/latest"
            +"?access_key="+tempApiKey; //apiKey may be passed in header with other api

    @Autowired
    public RefreshCurrencyImpl(WebClient.Builder builder,
                             CurrencyService deviseService) {
        this.webClient = builder
                .baseUrl(fixerApiUrl)
                //.defaultHeader("api_key", tempApiKey) //not possible with fixer.io api
                .build();
        this.currencyService = deviseService;
    }


    @Override
    public List<Currency> retreiveRecentCurrencyValues() {
        List<Currency> devises = new ArrayList<>();

		/*
		 Mono<String> reactiveStream = webClient.get()
	            .retrieve()
	            .bodyToMono(new ParameterizedTypeReference<String>() {});
	    String result = reactiveStream.block();
	    System.out.println("result="+result);
		 */
        //type de réponse brute attendue:
		/*
			{"success":true,"timestamp":1635959583,"base":"EUR","date":"2021-11-03",
			"rates":{"AED":4.254663,"AFN":105.467869,..., "EUR":1 , ...}}
		*/

        Mono<FixerIoResponse> reactiveStream = webClient.get()//.uri("/suiteUrlQuiVaBien")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<FixerIoResponse>() {});
        FixerIoResponse fixerIoResponse = reactiveStream.block();

		/*
		ResponseEntity<FixerIoResponse> fixerIoResponseEntity=
				webClient.get().retrieve()
				.toEntity(FixerIoResponse.class).block();

		FixerIoResponse fixerIoResponse = null;
		if(fixerIoResponseEntity.getStatusCode()==HttpStatus.OK) {
		 fixerIoResponse=fixerIoResponseEntity.getBody();
		}
		*/

        System.out.println("result="+fixerIoResponse);

        for(String currencyTicker : fixerIoResponse.getRates().keySet()) {
            Double value = fixerIoResponse.getRates().get(currencyTicker);
            String currencyName ="__"+currencyTicker+"__"; //au mieux (valeur à améliorer ultérieurement)
            Currency c = new Currency(currencyTicker,currencyName,value);
            devises.add(c);
        }
        return devises;
    }

    @Override
    @Transactional
    public List<Currency> refreshCurrencyValuesInDataBase() {
        List<Currency> freshDevises = this.retreiveRecentCurrencyValues();
        for(Currency c : freshDevises) {
            boolean existing = currencyService.existsByTicker(c.getTicker());
            if(existing) {
                Currency ec = currencyService.getByTicker(c.getTicker());
                ec.setValue(c.getValue());
                //automatic update/save in transactional context
            }else {
                //cela fonctionne , mais trop de monnaies non significatives
                //deviseService.sauvegarderDevise(d);
            }
        }
        return freshDevises;
    }

}