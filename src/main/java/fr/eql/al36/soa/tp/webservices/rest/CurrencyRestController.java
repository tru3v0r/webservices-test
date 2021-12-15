package fr.eql.al36.soa.tp.webservices.rest;

import fr.eql.al36.soa.tp.webservices.entity.Currency;
import fr.eql.al36.soa.tp.webservices.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="rest/currency-api", headers="Accept=application/json")
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

//    //V2 with CurrencyDTO
//    List<Currency> getAllByCriteria(...) {
//
//    }
//
//    Currency getByCriteria(...) {
//
//    }

    //CRUD : POST, GET, PUT, DELETE

    @GetMapping("public/currency")
    List<Currency> getAll() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("public/currency/{id}")
    Currency getCurrency(@PathVariable String id) {
        return currencyService.getByTicker(id);
    }

    //POST
    //http://localhost:8484/webservices/currency-api/private/currency
    //with { "ticker" : "DDK" , "name" : "Danish Krone", "value" : 7.77 }
    @PostMapping("private/currency")
    public ResponseEntity<Currency> postCurrency(@RequestBody Currency currency) {
        // V1 (to be greatly improved)
        currencyService.save(currency);
        return new ResponseEntity<Currency>(currency, HttpStatus.OK);
    }

    //PUT
    //http://localhost:8484/webservices/currency-api/private/currency
    //with { "ticker" : "DDK" , "name" : "Danish Krone", "value" : 7.77 }
    @PutMapping("private/currency")
    public ResponseEntity<Currency> putCurrency(@RequestBody Currency currency) {
        // V1 (to be greatly improved)
        currencyService.save(currency);
        return new ResponseEntity<Currency>(currency, HttpStatus.OK);
    }


    //DELETE
    //http://localhost:8484/webservices/currency-api/private/currency
    @DeleteMapping("private/devise/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable String id) {
        this.currencyService.deleteByTicker(id);
        Map msgOk = new HashMap<String,Object>();
        msgOk.put("message", "Deleting successful");
        return new ResponseEntity<Map<String,Object>>(msgOk, HttpStatus.OK);
    }

}
