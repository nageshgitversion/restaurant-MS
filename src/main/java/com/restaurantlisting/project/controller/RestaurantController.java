package com.restaurantlisting.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantlisting.project.dto.RestaurantDto;
import com.restaurantlisting.project.entity.Restaurant;
import com.restaurantlisting.project.service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addRestaurant(@RequestBody RestaurantDto restaurantdto) {
		
		String status = service.addRestaurant(restaurantdto);
		
		return  new ResponseEntity<>(status,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getbyid/{restaurantid}")
	public Restaurant getRestaurantById(@PathVariable Integer restaurantid) {
		
		return service.getRestaurantById(restaurantid);
	}

}
