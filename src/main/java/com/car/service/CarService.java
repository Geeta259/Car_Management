package com.car.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.car.dao.CarRepository;
import com.car.entities.Car;

@Service
public class CarService {
	

	 @Autowired
	    private CarRepository carRepository;

	    public CarService(CarRepository carRepository) {
	        this.carRepository = carRepository;
	    }

	    public Car addCar(Car car) {
	        return carRepository.save(car);
	    }

	    
	    public Page<Car> getAllCars(Pageable pageable) {
	        return carRepository.findAll(pageable); // Ensure this method exists in CarRepository
	    }
	    
	    public Car getCarById(Long id) {
	    	return carRepository.findById(id).orElse(null);
	    }

	    public Page<Car> filterCars(String name, String model, Integer year, Pageable pageable) {
	        // If all filters are null, return an empty page
	        if (name == null && model == null && year == null) {
	            return Page.empty();
	        }

	        // Otherwise, perform the query
	        return carRepository.findByFilters(name, model, year, pageable);
	    }

	    public List<Car> searchByGlobal(String query) {
	        return carRepository.searchByGlobal(query);
	    }
	    
	    public Page<Car> getCars(int page) {
	        return carRepository.findAll(PageRequest.of(page, 5)); // Example: 5 cars per page
	    }

	    public Car updateCar(Long id, Car carDetails) {
	        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
	        car.setName(carDetails.getName());
	        car.setModel(carDetails.getModel());
	        car.setYear(carDetails.getYear());
	        car.setPrice(carDetails.getPrice());
	        car.setColor(carDetails.getColor());
	        car.setFuelType(carDetails.getFuelType());
	        return carRepository.save(car);
	    }
	    
	    public void deleteCar(Long id) {
	        if (!carRepository.existsById(id)) {
	            throw new RuntimeException("Car not found with id: " + id);
	        }
	        carRepository.deleteById(id);
	    }
}
