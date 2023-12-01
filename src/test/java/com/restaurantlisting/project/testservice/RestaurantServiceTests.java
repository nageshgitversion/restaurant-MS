package com.restaurantlisting.project.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import com.restaurantlisting.project.dto.RestaurantDto;
import com.restaurantlisting.project.entity.Restaurant;
import com.restaurantlisting.project.repository.RestaurantRepository;
import com.restaurantlisting.project.service.RestaurantService;

class RestaurantServiceTests {
	
	@InjectMocks
	RestaurantService service;
	
	@Mock
	private RestaurantRepository repo;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	
	}
	
	@Test
	void testaddRestaurant() {
		
		RestaurantDto mockdata = new RestaurantDto();
		mockdata.setRestaurantId(1);
		mockdata.setRestaurantName("VIGENESH RESTAURANT");
		mockdata.setCity("Hyderabad");
		mockdata.setAddress("Road No-1,Bangarahills");
		mockdata.setRestaurantDescription("Taste is very good");
		
		Restaurant restaurant = new Restaurant();
		
		BeanUtils.copyProperties(mockdata,restaurant);
		
		String status1 = "Restaurant "+ restaurant.getRestaurantName()+" with Id "+restaurant.getRestaurantId()+" saved successfully" ;
		
		
		
		when(repo.save(restaurant)).thenReturn(restaurant);
		
		String status = service.addRestaurant(mockdata);
		
		assertEquals(status1, status);
		
}
	@Test
	void testgetRestaurantById() {
		
		Integer mockId=1;
		
		Restaurant mockdata = new Restaurant();
		mockdata.setRestaurantId(1);
		mockdata.setRestaurantName("VIGENESH RESTAURANT");
		mockdata.setCity("Hyderabad");
		mockdata.setAddress("Road No-1,Bangarahills");
		mockdata.setRestaurantDescription("Taste is very good");
		
		when(repo.findById(mockId)).thenReturn(Optional.of(mockdata));
		
		Restaurant restaurant = service.getRestaurantById(mockId);
		
		
		assertEquals(mockId, restaurant.getRestaurantId());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}