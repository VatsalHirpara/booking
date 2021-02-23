package com.nagarro.booking.service;

import java.util.List;

import com.nagarro.booking.domain.Booking;

public interface BookingService {

	List<Booking> getAllBookings();

	Booking getBookingById(String bookingId) throws Exception;

	String addBooking(Booking booking);

}
