package com.restaurantlisting.project.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantlisting.project.dto.RestaurantDto;
import com.restaurantlisting.project.entity.Restaurant;
import com.restaurantlisting.project.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository repo;
	
	
	public String addRestaurant(RestaurantDto restaurantdto) {
		
		Restaurant restaurant = new Restaurant();
		
		BeanUtils.copyProperties(restaurantdto, restaurant);
		
		if(restaurant.getRestaurantId() == null) {
			
			   repo.save(restaurant);
			   
			
		}
		return "Restaurant "+ restaurant.getRestaurantName()+" with Id "+restaurant.getRestaurantId()+" saved successfully"   ;
	}
	
	
	public Restaurant getRestaurantById(Integer restaurantId) {
		
		Optional<Restaurant> restaurant = repo.findById(restaurantId);
		
		if(restaurant.isPresent()) {
			return restaurant.get();
		}
		
		return null;
	}

}
