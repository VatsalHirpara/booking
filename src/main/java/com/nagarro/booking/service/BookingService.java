package com.nagarro.booking.service;

import java.util.List;

import com.nagarro.booking.domain.Booking;
import com.nagarro.booking.dto.BookingReqDto;

public interface BookingService {

	List<Booking> getAllBookings();

	Booking getBookingById(String bookingId) throws Exception;

	String addBooking(Booking booking);

	Booking bookService(BookingReqDto bookingReqDto) throws Exception;

}
