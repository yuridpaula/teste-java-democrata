package com.democrata.service;

import java.util.Optional;

import com.democrata.dto.AddressDto;

public interface ViaCepService {
	Optional<AddressDto> getAdress(String cep);	
}
