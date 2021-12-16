package fr.eql.al36.soa.tp.webservices.rest;

import fr.eql.al36.soa.tp.webservices.delegate.RefreshCurrency;
import fr.eql.al36.soa.tp.webservices.dto.CurrencyDTO;
import fr.eql.al36.soa.tp.webservices.dto.DTOUtil;
import fr.eql.al36.soa.tp.webservices.dto.GenericMessage;
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

    private final RefreshCurrency refreshCurrency;

    public CurrencyRestController(CurrencyService currencyService, RefreshCurrency refreshCurrency) {
        this.currencyService = currencyService;
        this.refreshCurrency = refreshCurrency;
    }

    //http://localhost:8484/webServices/devise-api/private/refresh
    @GetMapping(value="/private/refresh")
    public GenericMessage refreshValues() {
        List<Currency> currencies = this.refreshCurrency.refreshCurrencyValuesInDataBase();
        return new GenericMessage("refresh ok",currencies.toString());
    }

    @GetMapping("public/currency")
    List<CurrencyDTO> getAll() {
        return DTOUtil.currenciesToDTO(currencyService.getAllCurrencies());
    }

    @GetMapping("public/currency/{id}")
    CurrencyDTO getCurrency(@PathVariable String id) {
        Currency currency = currencyService.getByTicker(id);
        //Converting entity to DTO through constructor
        return new CurrencyDTO(currency.getTicker(), currency.getName(), currency.getValue());

        //BeanUtils.copyProperties(sourceObj, targetObj);
    }

    //POST
    //http://localhost:8484/webservices/currency-api/private/currency
    //with { "ticker" : "DDK" , "name" : "Danish Krone", "value" : 7.77 }
    @PostMapping("private/currency")
    public ResponseEntity<?> postCurrency(@RequestBody CurrencyDTO currencyDTO) {
        if(currencyService.getByTicker(currencyDTO.getTicker()) != null) {
            return new ResponseEntity<GenericMessage>(
                    new GenericMessage("message","Conflict: currency with same ticker already exists"),
                    HttpStatus.CONFLICT);
        }
        else {
            currencyService.save(new Currency(currencyDTO.getTicker(), currencyDTO.getName(), currencyDTO.getValue()));
            return new ResponseEntity<CurrencyDTO>(currencyDTO, HttpStatus.OK);
        }
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
