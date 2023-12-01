package com.restaurantlisting.project.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantlisting.project.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Serializable>{
	
	

}
