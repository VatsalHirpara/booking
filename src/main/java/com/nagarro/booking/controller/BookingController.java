package com.nagarro.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.booking.domain.Booking;
import com.nagarro.booking.dto.BookingReqDto;
import com.nagarro.booking.dto.ResponseTO;
import com.nagarro.booking.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<ResponseTO<Booking>> getBookingById(@PathVariable("bookingId") String bookingId)
			throws Exception {
		ResponseTO<Booking> response = new ResponseTO<>();
		Booking booking = bookingService.getBookingById(bookingId);
		response.setData(booking);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ResponseTO<String>> addBooking(@RequestBody Booking booking) throws Exception {
		ResponseTO<String> response = new ResponseTO<>();
		String bookingId = bookingService.addBooking(booking);
		response.setData("Created with bookingId: " + bookingId);
		return new ResponseEntity<ResponseTO<String>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/assign-worker")
	public ResponseEntity<ResponseTO<Booking>> assignWorker(@RequestBody BookingReqDto bookingReqDto) {
		ResponseTO<Booking> response = new ResponseTO<>();

		return ResponseEntity.ok(response);
	}

}
