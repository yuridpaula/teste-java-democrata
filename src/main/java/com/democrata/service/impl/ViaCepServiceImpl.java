package com.democrata.service.impl;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.democrata.dto.AddressDto;
import com.democrata.service.ViaCepService;
import com.democrata.util.Utils;

@Service
public class ViaCepServiceImpl implements ViaCepService {

	private final Logger LOGGER = LoggerFactory.getLogger(ViaCepServiceImpl.class);
	
	private static final String BASE_URL = "https://viacep.com.br/ws/{cep}/{type}/";
	
	@Override
	public Optional<AddressDto> getAdress(String cep) {
		
		//just for not polling viacep api
		try {
			Thread.sleep(3000);
			
		} catch (Exception e) {
		}
		
		Integer option = Utils.getRandom(1, 3);
		
		switch (option) {
		case 1://json
			return this.getJsonAdress(cep);
		case 2://xml
			return this.getXmlAdress(cep);
		default://piped
			return this.getPipedAdress(cep);		
		}
		
	}	
	
	
	public Optional<AddressDto> getXmlAdress(String cep) {
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<AddressDto> response = rest.getForEntity(getUrl(cep, "xml"), AddressDto.class);
		return Optional.ofNullable(response.getBody().getCep() == null ? null : response.getBody());
	}

	public Optional<AddressDto> getJsonAdress(String cep) {
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<AddressDto> response = rest.getForEntity(getUrl(cep, "json"), AddressDto.class);
		return Optional.ofNullable(response.getBody().getCep() == null ? null : response.getBody());
	}

	public Optional<AddressDto> getPipedAdress(String cep) {
		RestTemplate rest = new RestTemplate();
		
		ResponseEntity<String> response = rest.getForEntity(getUrl(cep, "piped"), String.class);

		StringTokenizer line = new StringTokenizer(response.getBody(), "|");

		AddressDto cepPiped = new AddressDto();

		while (line.hasMoreTokens()) {

			StringTokenizer pipe = new StringTokenizer(line.nextToken(), ":");

			Field field = null;

			try {
				while (pipe.hasMoreTokens()) {
					if (field == null) {
						field = AddressDto.class.getDeclaredField(pipe.nextToken());
						field.setAccessible(true);
					} else {
						field.set(cepPiped, pipe.nextToken());
					}
				}
			} catch (Exception e) {
				//
			}

		}

		return Optional.ofNullable(cepPiped.getCep() == null ? null : cepPiped);
	}

	private String getUrl(String cep, String type) {
		LOGGER.info("Searching for {} cep info for {} ", type, cep);
		return BASE_URL.replace("{cep}", cep).replace("{type}", type);
	}
	
}
