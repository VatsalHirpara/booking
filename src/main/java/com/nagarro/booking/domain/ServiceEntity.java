package com.nagarro.booking.domain;

import com.nagarro.booking.enums.ServiceCategory;
import com.nagarro.booking.enums.ServiceName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
	private Integer id;

	private ServiceCategory serviceCategory;

	private ServiceName serviceName;

	private Double price;

}
