package fr.eql.al36.soa.tp.webservices.dao;

import fr.eql.al36.soa.tp.webservices.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyDAO extends JpaRepository<Currency, String> {

}
