package com.democrata.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.democrata.entity.Address;
import com.democrata.service.AddressService;
import com.democrata.util.BaseResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/address")
@CrossOrigin(origins = "*")
@Api(value = "Address", description = "REST API for Address Controller", tags = { "Address" })
@SuppressWarnings("unchecked")
public class AddressController extends BaseResource {

	private final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressService addressService;

	@GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "return Adress given a cep info")
	public @ResponseBody ResponseEntity<Address> getbyCep(@PathVariable String cep){
		LOGGER.info("GET /v1/Address/{}", cep);

		Optional<Address> address;

		try {
			address = this.addressService.findByCep(cep);
		} catch (Exception ex) {
			return (ResponseEntity<Address>) buildResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		checkNotNull(address, "Address");

		return (ResponseEntity<Address>) buildResponse(OK, address);
	}
	
	@GetMapping(value="/uf", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "return list of Adress grouped by UF")
	public @ResponseBody ResponseEntity<HashMap<String, List<Address>>> listAllGroupByUf(){
		LOGGER.info("GET /v1/Address");

		Optional<HashMap<String, List<Address>>> map;

		try {
			map = this.addressService.listAllGroupByUf();
		} catch (Exception ex) {
			return (ResponseEntity<HashMap<String, List<Address>>>) buildResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		checkNotNull(map, "Address");

		return (ResponseEntity<HashMap<String, List<Address>>>) buildResponse(OK, map);
	}
	
	@GetMapping(value="/uf/{uf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "return list of Adress grouped by Localidade given a UF")
	public @ResponseBody ResponseEntity<HashMap<String, List<Address>>> listAllGroupByLocalidade(@PathVariable String uf){
		LOGGER.info("GET /v1/Address");

		Optional<HashMap<String, List<Address>>> map;

		try {
			map = this.addressService.listAllGroupByLocalidade(uf);
		} catch (Exception ex) {
			return (ResponseEntity<HashMap<String, List<Address>>>) buildResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		checkNotNull(map, "Address");

		return (ResponseEntity<HashMap<String, List<Address>>>) buildResponse(OK, map);
	}		

	@GetMapping(value="/localidade/{localidade}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "return list of Adress given a Localidade")
	public @ResponseBody ResponseEntity<List<Address>> listAllByLocalidade(@PathVariable String localidade){
		LOGGER.info("GET /v1/Address");

		Optional<List<Address>> list;

		try {
			list = this.addressService.listAllByLocalidade(localidade);
		} catch (Exception ex) {
			return (ResponseEntity<List<Address>>) buildResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		checkNotNull(list, "Address");

		return (ResponseEntity<List<Address>>) buildResponse(OK, list);
	}
}
