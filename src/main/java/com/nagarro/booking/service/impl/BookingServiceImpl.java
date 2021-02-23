package com.nagarro.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.nagarro.booking.domain.Booking;
import com.nagarro.booking.enums.BookingStatus;
import com.nagarro.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	List<Booking> bookingsList = new ArrayList<>();

	@PostConstruct
	public void populateData() {
		Booking booking1 = new Booking();
		booking1.setId(UUID.randomUUID().toString());
		booking1.setCustomerId(2);
		booking1.setServiceId(2);
		booking1.setWorkerId(3);
		booking1.setStatus(BookingStatus.COMPLETED);
		Booking booking2 = new Booking();
		booking2.setId(UUID.randomUUID().toString());
		booking2.setCustomerId(2);
		booking2.setServiceId(3);
		booking2.setWorkerId(1);
		booking2.setStatus(BookingStatus.PROCESSING);
		bookingsList.add(booking1);
		bookingsList.add(booking2);
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingsList;
	}

	@Override
	public Booking getBookingById(String bookingId) throws Exception {
		for (Booking booking : bookingsList) {
			if (booking.getId().equals(bookingId))
				return booking;
		}
		throw new Exception("No booking found for given id");
	}

	@Override
	public String addBooking(Booking booking) {
		booking.setId(UUID.randomUUID().toString());
		this.bookingsList.add(booking);
		return booking.getId();
	}
}
