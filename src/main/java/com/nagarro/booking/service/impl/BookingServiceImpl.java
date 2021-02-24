package com.nagarro.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.booking.domain.Booking;
import com.nagarro.booking.dto.BookingReqDto;
import com.nagarro.booking.enums.BookingStatus;
import com.nagarro.booking.service.BookingService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class BookingServiceImpl implements BookingService {
	List<Booking> bookingsList = new ArrayList<>();

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	@PostConstruct
	public void populateData() {
		Booking booking1 = new Booking();
		booking1.setId(UUID.randomUUID().toString());
		booking1.setCustomerId(2);
		booking1.setServiceId(2);
		booking1.setWorkerId(3);
		booking1.setBookingStatus(BookingStatus.COMPLETED);
		Booking booking2 = new Booking();
		booking2.setId(UUID.randomUUID().toString());
		booking2.setCustomerId(2);
		booking2.setServiceId(3);
		booking2.setWorkerId(1);
		booking2.setBookingStatus(BookingStatus.PROCESSING);
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

	@Override
	public Booking bookService(BookingReqDto bookingReqDto) throws Exception {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("apigateway", false);
		String paymentUrl = instance.getHomePageUrl() + "/payments/" + bookingReqDto.getCustomerId();
		boolean paymentSuccess = restTemplate.getForObject(paymentUrl, Boolean.class);
		if (paymentSuccess) {
			return new Booking(UUID.randomUUID().toString(),BookingStatus.PROCESSING,bookingReqDto.getCustomerId(),0,paymentUrl, bookingReqDto.getServiceId(), null, null, null);
		} else {
			throw new Exception("payment failed");
		}
		
		
		
	}
}
