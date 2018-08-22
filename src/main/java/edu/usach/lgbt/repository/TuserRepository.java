package edu.usach.lgbt.repository;
import java.util.Collection;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.usach.lgbt.entities.Tuser;

public interface TuserRepository extends PagingAndSortingRepository<Tuser, Integer> {

	@Query(value = "select * from tuser order by relevance_tuser desc limit 10",nativeQuery=true)
	Collection<Tuser> findTenRelevants();
	
}





