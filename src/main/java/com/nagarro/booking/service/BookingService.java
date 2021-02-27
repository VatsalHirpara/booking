package com.nagarro.booking.service;

import java.util.List;

import com.nagarro.booking.domain.BookingEntity;
import com.nagarro.booking.dto.BookingReqDto;

public interface BookingService {

	List<BookingEntity> getAllBookings();

	BookingEntity getBookingById(String bookingId) throws Exception;

	String addBooking(BookingEntity bookingEntity);

	BookingEntity bookService(BookingReqDto bookingReqDto) throws Exception;

	void updateBooking(BookingEntity bookingEntity) throws Exception;

}
