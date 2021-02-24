package com.nagarro.booking.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingReqDto {
	private Integer customerId;
	private Integer serviceId;
	private LocalDateTime seviceTime;
}
