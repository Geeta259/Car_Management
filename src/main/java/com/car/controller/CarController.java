package com.car.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.car.entities.Car;
import com.car.service.CarService;


import jakarta.validation.Valid;

@Controller
@Validated
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    
    
    @GetMapping("/{page}")
    public String getAllCars(@PathVariable("page") Integer page, Model model) {
        int pageSize = 5; 
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Car> cars = carService.getAllCars(pageable); 

    
        model.addAttribute("cars", cars);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",cars.getTotalPages());
		
		 model.addAttribute("car", new Car());
        return "cars";
    }
    
    @GetMapping("/filterbox")
    public String filterBox() {
        return "car_list";  
    }
    
    @GetMapping("/filter/{page}")
    public String filterCars(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) Integer year,
        @PathVariable("page") Integer page,
        Model m) {

        // Define page size
        int pageSize = 5;

        // Create Pageable object for pagination
        Pageable pageable = PageRequest.of(page, pageSize);

        // Apply filtering with pagination
        Page<Car> filteredCars = carService.filterCars(name, model, year, pageable);

        // Add the filtered cars and pagination information to the model
        m.addAttribute("cars", filteredCars);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", filteredCars.getTotalPages());
        m.addAttribute("name", name);
        m.addAttribute("model", model);
        m.addAttribute("year", year);

        return "car_list"; // Return the template
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);  
        model.addAttribute("car", car);
        return "update_car";  
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @Valid @ModelAttribute Car carDetails, BindingResult result, Model model) {
        if (result.hasErrors()) {
              model.addAttribute("validationErrors", result.getAllErrors());
            model.addAttribute("car", carDetails); 
            return "update_car"; 
        }
        carService.updateCar(id, carDetails);
        return "redirect:/cars/0"; 
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id,Model model) {
        carService.deleteCar(id);
        model.addAttribute("car", new Car());
        return "redirect:/cars/0";  
    }

    @GetMapping("/search")
    public String globalSearch(@RequestParam String query, Model model) {
        List<Car> cars = carService.searchByGlobal(query);
        model.addAttribute("cars", cars);
        model.addAttribute("query", query);
        return "car_list";
    }


    @PostMapping("/add")
    public String addCar(@Validated @ModelAttribute("car") Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            
            model.addAttribute("car", car);

            int currentPage = 0; 
            Page<Car> carsPage = carService.getCars(currentPage);
            model.addAttribute("cars", carsPage);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", carsPage.getTotalPages());

            return "cars"; 
        }

        
        carService.addCar(car);
        return "redirect:/cars/0";
    }


}
