package com.car.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	 List<Car> findByNameContainingIgnoreCase(String name);
	    List<Car> findByModelContainingIgnoreCase(String model);
	    List<Car> findByYear(int year);
	    
	    @Query("SELECT c FROM Car c WHERE " +
	            "LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
	            "LOWER(c.model) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
	            "CAST(c.year AS string) LIKE CONCAT('%', :query, '%') OR " +
	            "LOWER(c.color) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
	            "LOWER(c.fuelType) LIKE LOWER(CONCAT('%', :query, '%'))")
	     List<Car> searchByGlobal(@Param("query") String query);
	    
	    

	    @Query("SELECT c FROM Car c WHERE " +
	           "(:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
	           "(:model IS NULL OR LOWER(c.model) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
	           "(:year IS NULL OR c.year = :year)")
	    Page<Car> findByFilters(@Param("name") String name,
	                            @Param("model") String model,
	                            @Param("year") Integer year,
	                            Pageable pageable);
	    
}
