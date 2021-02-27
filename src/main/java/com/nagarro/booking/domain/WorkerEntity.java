package com.nagarro.booking.domain;

import com.nagarro.booking.enums.ServiceCategory;
import com.nagarro.booking.enums.ServiceName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerEntity {
	private Integer id;

	private String name;

	private String email;

	private String city;

	/**
	 * area of expertise 
	 */
	private ServiceCategory serviceCategory;
	
	private ServiceName serviceName;

}