package fr.eql.al36.soa.tp.webservices.dao;

import fr.eql.al36.soa.tp.webservices.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDAO extends JpaRepository<Country, String> {
}
