package com.restaurantlisting.project.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.restaurantlisting.project.controller.RestaurantController;
import com.restaurantlisting.project.dto.RestaurantDto;
import com.restaurantlisting.project.entity.Restaurant;
import com.restaurantlisting.project.service.RestaurantService;

class RestaurantControllerTests {
	
	@InjectMocks
	RestaurantController controller;
	
	@Mock
	private RestaurantService service;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	
	}
	
	
	@Test
    void testGetByRestaurantId() {
		Restaurant mockdata = new Restaurant();
		mockdata.setRestaurantId(1);
		mockdata.setRestaurantName("VIGENESH RESTAURANT");
		mockdata.setCity("Hyderabad");
		mockdata.setAddress("Road No-1,Bangarahills");
		mockdata.setRestaurantDescription("Taste is very good");
		
		when(service.getRestaurantById(1)).thenReturn(mockdata);
		
		Restaurant restaur = controller.getRestaurantById(1);
		
		assertEquals(mockdata, restaur);
		
		
		
	}
	
	@Test
    void testaddRestaurant() {
		RestaurantDto mockdata = new RestaurantDto();
		mockdata.setRestaurantId(1);
		mockdata.setRestaurantName("VIGENESH RESTAURANT");
		mockdata.setCity("Hyderabad");
		mockdata.setAddress("Road No-1,Bangarahills");
		mockdata.setRestaurantDescription("Taste is very good");
		
		when(service.addRestaurant(mockdata)).thenReturn("Data saved");
		
		ResponseEntity<String> addRestaurant = controller.addRestaurant(mockdata);
		
		
		
		assertEquals(HttpStatus.CREATED,addRestaurant.getStatusCode());
		assertEquals("Data saved", addRestaurant.getBody());
		
		
		
	}

}
