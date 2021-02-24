package com.nagarro.booking.domain;

import java.time.LocalDateTime;

import com.nagarro.booking.enums.BookingStatus;
import com.nagarro.booking.enums.ServiceCategory;
import com.nagarro.booking.enums.ServiceName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	private String id;

	private BookingStatus bookingStatus = BookingStatus.PROCESSING;

	private Integer customerId;

	private Integer workerId;

	private String workerName;

	private Integer serviceId;

	private ServiceCategory serviceCategory;

	private ServiceName serviceName;

	private LocalDateTime serviceTime;
}
