package fr.eql.al36.soa.tp.webservices.client;

import fr.eql.al36.soa.tp.webservices.dto.CurrencyDTO;
import fr.eql.al36.soa.tp.webservices.dto.FixerIoResponse;
import fr.eql.al36.soa.tp.webservices.entity.Currency;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RestClientApp {

    public static void main(String[] args) {
        postNewCurrency();
    }

    private static void postNewCurrency() {
        String baseUrl = "http://localhost:8484/webservices/currency-api";
        WebClient.Builder builder = WebClient.builder();
        WebClient webClient = builder
                .baseUrl(baseUrl)
                //.defaultHeader("api_key, tempAPIKey) //not possible with
                .build();

        //create an instance of CurrencyDTO
        //wth values
        //{ "code" : "DDK", "name" : "Danish Krone", "value" : 7.77 }
        // send it via POST call

        CurrencyDTO ddk = new CurrencyDTO("DDK", "Danish Krone", 7.77);
        Mono<CurrencyDTO> reactiveStream = webClient.post()
                .uri("/private/currency")
                .body(Mono.just(ddk), CurrencyDTO.class)
                .retrieve()
                .bodyToMono(CurrencyDTO.class)
                .onErrorReturn(new CurrencyDTO("?", "not saved",0));

        CurrencyDTO savedCurrencyDTO = reactiveStream.block();

        System.out.println("savedCurrencyDTO=" + savedCurrencyDTO.toString());

    }
}
