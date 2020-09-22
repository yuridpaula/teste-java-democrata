package com.democrata.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.democrata.service.AddressService;

@Component
public class RequestSchedule {
	
	@Autowired
	private AddressService addressService;
	
	
	@Scheduled(fixedRate = 1000 * 60 * 5)
    public void create() {
		this.addressService.persistRandomValidCep(5);
    }
}
