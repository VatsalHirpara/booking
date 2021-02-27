package com.nagarro.booking.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.booking.domain.BookingEntity;
import com.nagarro.booking.domain.ServiceEntity;
import com.nagarro.booking.dto.BookingReqDto;
import com.nagarro.booking.dto.ResponseTO;
import com.nagarro.booking.enums.BookingStatus;
import com.nagarro.booking.enums.ServiceCategory;
import com.nagarro.booking.enums.ServiceName;
import com.nagarro.booking.service.BookingService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class BookingServiceImpl implements BookingService {

	List<BookingEntity> bookingsList = Collections.synchronizedList(new ArrayList<>());

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	@PostConstruct
	public void populateData() {
		BookingEntity booking1 = new BookingEntity();
		booking1.setId(UUID.randomUUID().toString());
		booking1.setCustomerId(2);
		booking1.setServiceId(2);
		booking1.setWorkerId(3);
		booking1.setWorkerName("Ramesh");
		booking1.setBookingStatus(BookingStatus.COMPLETED);
		booking1.setServiceCategory(ServiceCategory.CARPENTERS);
		booking1.setServiceName(ServiceName.DOOR_INSTALLATION);
		bookingsList.add(booking1);
	}

	@Override
	public List<BookingEntity> getAllBookings() {
		return bookingsList;
	}

	@Override
	public BookingEntity getBookingById(String bookingId) throws Exception {
		for (BookingEntity bookingEntity : bookingsList) {
			if (bookingEntity.getId().equals(bookingId))
				return bookingEntity;
		} 
		throw new Exception("No booking found for given id");
	}

	@Override
	public String addBooking(BookingEntity bookingEntity) {
		bookingEntity.setId(UUID.randomUUID().toString());
		this.bookingsList.add(bookingEntity);
		return bookingEntity.getId();
	}

	@Override
	public BookingEntity bookService(BookingReqDto bookingReqDto) throws Exception {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("apigateway", false);
		veifyPayment(bookingReqDto.getCustomerId());
		String serviceUrl = instance.getHomePageUrl() + "/services/" + bookingReqDto.getServiceId();
		ResponseEntity<ResponseTO<ServiceEntity>> response = restTemplate.exchange(serviceUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseTO<ServiceEntity>>() {
				});
		ServiceEntity serviceEntity = response.getBody().getData();
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setId(UUID.randomUUID().toString());
		bookingEntity.setBookingStatus(BookingStatus.PROCESSING);
		bookingEntity.setCustomerId(bookingReqDto.getCustomerId());
		bookingEntity.setServiceId(serviceEntity.getId());
		bookingEntity.setServiceCategory(serviceEntity.getServiceCategory());
		bookingEntity.setServiceName(serviceEntity.getServiceName());
		bookingEntity.setCreationTimeStamp(Instant.now());
		bookingsList.add(bookingEntity);
		sendBookingRequestedEvent(bookingEntity.getId());
		return bookingEntity;
	}

	private void sendBookingRequestedEvent(String bookingID) {
		jmsTemplate.convertAndSend("BookingRequestReceived", bookingID);
	}

	private void veifyPayment(Integer customerId) throws Exception {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("apigateway", false);
		String paymentUrl = instance.getHomePageUrl() + "/payments/" + customerId;
		boolean paymentRes = restTemplate.getForObject(paymentUrl, Boolean.class);
		if (!paymentRes) {
			throw new Exception("payment failed");
		}
	}

	@Override
	public void updateBooking(BookingEntity updatedbookingEntity) throws Exception {
		for (int i = 0; i < this.bookingsList.size(); i++) {
			BookingEntity bookingEntity = bookingsList.get(i);
			if (bookingEntity.getId().equals(updatedbookingEntity.getId())) {
				bookingsList.set(i, updatedbookingEntity);
				return;
			}
		}
		throw new Exception("no booking exists with this id ");
	}
}
