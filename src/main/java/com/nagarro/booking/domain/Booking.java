package com.nagarro.booking.domain;

import com.nagarro.booking.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	private String id;

	private BookingStatus status = BookingStatus.PROCESSING;

	private Integer customerId;

	private Integer workerId;

	private Integer serviceId;
}
