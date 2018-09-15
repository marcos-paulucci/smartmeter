package uk.co.scottishpower.smartmeter.smartreads;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * SmartreadDataRestRepository.java - The RepositoryRestResource from Spring Data Rest abstracts the creation of the layers
 * to fetch the data from the database and to serve it on the api
 * @author  Marcos Paulucci
 * see @SmartreadSummary, @Smartread
 */

@RepositoryRestResource(excerptProjection = SmartreadSummary.class, path = "reads", collectionResourceRel = "reads")
public interface SmartreadDataRestRepository extends CrudRepository<Smartread, Long> {

}
