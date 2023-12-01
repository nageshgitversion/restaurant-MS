package com.restaurantlisting.project.dto;

import lombok.Data;

@Data
public class RestaurantDto {
	

		
		private Integer restaurantId;
		private String restaurantName;
		private String address;
		private String city;
		private String restaurantDescription;
		
		
		public Integer getRestaurantId() {
			return restaurantId;
		}
		public void setRestaurantId(Integer restaurantId) {
			this.restaurantId = restaurantId;
		}
		public String getRestaurantName() {
			return restaurantName;
		}
		public void setRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getRestaurantDescription() {
			return restaurantDescription;
		}
		public void setRestaurantDescription(String restaurantDescription) {
			this.restaurantDescription = restaurantDescription;
		}
		

}
