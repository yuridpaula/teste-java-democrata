package com.democrata.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.democrata.dto.AddressDto;
import com.democrata.entity.Address;
import com.democrata.repository.AddressRepository;
import com.democrata.service.AddressService;
import com.democrata.service.ViaCepService;
import com.democrata.util.Utils;

@Service
public class AddressServiceImpl implements AddressService {
	
	private static final long MINUTES_LIMIT = 5;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public void persistRandomValidCep(Integer times) {
		Integer count = 0;
		
		String cep = "000";
		
		do {
			try {
				cep = this.getRandomValidCep(cep);
				
				Optional<AddressDto> dto = this.viaCepService.getAdress(cep);
						
				if(dto.isPresent()) {
					this.addressRepository.save(this.dtoToEntity(dto.get()));
					
					count++;
				}
			} catch(Exception e) {
				//
			}

		} while(count < times);
		
	}
	
	@Override
	public Optional<Address> findByCep(String cep) {
		Boolean needSearch = false;
		
		Optional<Address> address = this.addressRepository.findByCep(cep);
		
		if(address.isPresent()) {
			
			long minutes = address.get().getCreatedOn().until( LocalDateTime.now(), ChronoUnit.MINUTES );
			
			needSearch = minutes >= MINUTES_LIMIT;
		} else {
			needSearch = true;
		}
		
		if (needSearch) {
			Optional<AddressDto> dto = this.viaCepService.getAdress(cep);
			
			if(dto.isPresent()) {
				address = Optional.of(this.dtoToEntity(dto.get()));
				
				this.addressRepository.save(address.get());
			} else {
				address = Optional.ofNullable(null);
			}
		}
		
		return address;
	}
	
	@Override
	public Optional<HashMap<String, List<Address>>> listAllGroupByUf() {
		List<Address> list = this.addressRepository.findAll();
		
		HashMap<String, List<Address>> map = 
			(HashMap<String, List<Address>>) 
			list.stream().collect(Collectors.groupingBy(Address::getUf));
		
		return Optional.ofNullable(map);
	}
	
	@Override
	public Optional<HashMap<String, List<Address>>> listAllGroupByLocalidade(String uf) {
		List<Address> list = this.addressRepository.findByUfIgnoreCase(uf);
		
		HashMap<String, List<Address>> map = 
			(HashMap<String, List<Address>>) 
			list.stream().collect(Collectors.groupingBy(Address::getLocalidade));
		
		return Optional.ofNullable(map);
	}
	

	@Override
	public Optional<List<Address>> listAllByLocalidade(String localidade) {
		List<Address> list = this.addressRepository.findByLocalidadeIgnoreCase(localidade);
		
		return Optional.ofNullable(list);
	}
	
	private Address dtoToEntity(AddressDto from) {
		Address to = new Address();
		
		to.setBairro(from.getBairro());
		to.setCep(from.getCep());
		to.setComplemento(from.getComplemento());
		to.setDdd(from.getDdd());
		to.setGia(from.getGia());
		to.setIbge(from.getIbge());
		to.setLocalidade(from.getLocalidade());
		to.setLogradouro(from.getLogradouro());
		to.setSiafi(from.getSiafi());
		to.setUf(from.getUf());
		to.setUnidade(from.getUnidade());
		
		return to;
	}
	
	private String getRandomValidCep(String lastTry) {
		
		/**
		 * Correios - primeiros 5 digitos:
		 * 1. Região do Estado
		 * 2. Sub-Região
		 * 3. Setor Região
		 * 4. Setor da região e adjacências
		 * 5. SubSetor (cidade)
		 * 
		 * Cada algarismo pode estar entre 0 a 9
		 * Já os 3 últimos números são o sufixo individual por localidade
		 * Faixa de Sufixos utilizada: 000 a 899 (exceto sufixos expeciais).
		 * 
		 */	
		
		/* Implementation
		 * try first with full random numbers and if not get info
		 * try with "000" individual locale
		 */
		
		String cep = "";

		if (lastTry.endsWith("000")) { 
			
			int numRegiao = Utils.getRandom(0, 9);
	
			int subRegiao = Utils.getRandom(0, 9);
	
			int setorRegiao = Utils.getRandom(0, 9);
	
			int setorRegiaoAdj = Utils.getRandom(0, 9);
	
			int subSetor = Utils.getRandom(0, 9);
	
			int sufixo = Utils.getRandom(000, 899);
			
			String sufixoEndereco = String.format("%03d", new Object[] { sufixo });
			cep = String.valueOf(numRegiao) + String.valueOf(subRegiao) + String.valueOf(setorRegiao)
			+ String.valueOf(setorRegiaoAdj) + String.valueOf(subSetor) + sufixoEndereco;
		} else {
			cep = lastTry.substring(0, 5) + "000";
		}
				
		Optional<Address> adress = this.addressRepository.findByCep(cep);
		
		if(adress.isPresent()) {
			return getRandomValidCep(cep);			
		} else {
			return cep;
		}
	}

	
}
