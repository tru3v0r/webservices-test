package fr.eql.al36.soa.tp.webservices.service;

import fr.eql.al36.soa.tp.webservices.entity.Country;

import java.util.List;

public interface CountryService {

    Country findByCode(String code);
    List<Country> getAllCountries();
    void deleteByCode(String code);
    void save(Country country);
}
