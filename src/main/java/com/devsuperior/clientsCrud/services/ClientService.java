package com.devsuperior.clientsCrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clientsCrud.dtos.ClientDTO;
import com.devsuperior.clientsCrud.entities.Client;
import com.devsuperior.clientsCrud.repositories.ClientRepository;
import com.devsuperior.clientsCrud.services.exceptions.ResourceNotFoundException;

public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client c = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ClientDTO(c);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(c -> new ClientDTO(c));
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client c = new Client();
		copyDtoToEntity(dto, c);
		c = repository.save(c);
		return new ClientDTO(c);
	}
	
	
	
	private void copyDtoToEntity(ClientDTO dto, Client c) {
		c.setName(dto.getName());
		c.setCpf(dto.getCpf());
		c.setIncome(dto.getIncome());
		c.setBirthDate(dto.getBirthDate());
		c.setChildren(dto.getChildren());
	}
}
