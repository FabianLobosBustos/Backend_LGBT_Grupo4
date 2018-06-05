package edu.usach.lgbt.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import edu.usach.lgbt.entities.Region;


public interface RegionRepository extends PagingAndSortingRepository<Region, Integer> {
	

}