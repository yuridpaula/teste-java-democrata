package com.democrata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.democrata.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
	Optional<Address> findByCep(String cep);
	
	List<Address> findByUfIgnoreCase(String uf);

	List<Address> findByLocalidadeIgnoreCase(String localidade);
	
}