package com.car.controller;

import com.car.entities.Car;
import com.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@Tag(name = "Car Management", description = "Endpoints for managing cars")
public class CarRestController {

    @Autowired
    private CarService carService;

    @Operation(summary = "Get all cars with pagination")
    @GetMapping
    public ResponseEntity<Page<Car>> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Car> cars = carService.getAllCars(pageable);
        return ResponseEntity.ok(cars);
    }

    @Operation(summary = "Filter cars by name, model, or year with pagination")
    @GetMapping("/filter")
    public ResponseEntity<Page<Car>> filterCars(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Car> filteredCars = carService.filterCars(name, model, year, pageable);
        return ResponseEntity.ok(filteredCars);
    }

    @Operation(summary = "Get a car by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    @Operation(summary = "Add a new car")
    @PostMapping
    public ResponseEntity<Object> addCar(@RequestBody Car car) {
        if (car.getId() != null) {
            return ResponseEntity.badRequest().body("ID should not be provided for a new car");
        }
        Car createdCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }


    @Operation(summary = "Update a car by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id, @Valid @RequestBody Car carDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Car updatedCar = carService.updateCar(id, carDetails);
        return ResponseEntity.ok(updatedCar);
    }

    @Operation(summary = "Delete a car by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Global search by query")
    @GetMapping("/search")
    public ResponseEntity<List<Car>> globalSearch(@RequestParam String query) {
        List<Car> cars = carService.searchByGlobal(query);
        return ResponseEntity.ok(cars);
    }
}
