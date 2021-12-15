package fr.eql.al36.soa.tp.webservices.service;

import fr.eql.al36.soa.tp.webservices.dao.CountryDAO;
import fr.eql.al36.soa.tp.webservices.entity.Country;
import fr.eql.al36.soa.tp.webservices.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryDAO countryDAO;

    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public Country findByCode(String code) {
        Optional<Country> optionalCountry = countryDAO.findById(code);
        return optionalCountry.orElseThrow(() -> new NotFoundException("No country found with this code"));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDAO.findAll();
    }

    @Override
    public void deleteByCode(String code) {
        countryDAO.deleteById(code);
    }

    @Override
    public void save(Country country) {
        countryDAO.save(country);
    }
}
