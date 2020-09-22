package com.democrata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.democrata.entity.Address;

public interface AddressService {
	
	public void persistRandomValidCep(Integer times);
	
	Optional<Address> findByCep(String cep);
	
	Optional<HashMap<String, List<Address>>> listAllGroupByUf();
	
	Optional<HashMap<String, List<Address>>> listAllGroupByLocalidade(String uf);
	
	Optional<List<Address>> listAllByLocalidade(String localidade);
}
