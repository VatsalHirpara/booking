package com.nagarro.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.booking.domain.BookingEntity;
import com.nagarro.booking.dto.BookingReqDto;
import com.nagarro.booking.dto.ResponseTO;
import com.nagarro.booking.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping
	public List<BookingEntity> getAllBookings() {
		return bookingService.getAllBookings();
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<ResponseTO<BookingEntity>> getBookingById(@PathVariable("bookingId") String bookingId)
			throws Exception {
		ResponseTO<BookingEntity> response = new ResponseTO<>();
		BookingEntity bookingEntity = bookingService.getBookingById(bookingId);
		response.setData(bookingEntity);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/book-service")
	public ResponseEntity<ResponseTO<BookingEntity>> assignWorker(@RequestBody BookingReqDto bookingReqDto)
			throws Exception {
		ResponseTO<BookingEntity> response = new ResponseTO<>();

		BookingEntity bookingEntity = bookingService.bookService(bookingReqDto);
		response.setData(bookingEntity);

		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<ResponseTO<String>> assignWorker(@RequestBody BookingEntity bookingEntity) throws Exception {
		ResponseTO<String> response = new ResponseTO<>();
		bookingService.updateBooking(bookingEntity);
		response.setData("Success");
		return ResponseEntity.ok(response);
	}

}
