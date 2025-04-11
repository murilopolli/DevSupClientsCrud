package com.devsuperior.clientsCrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.clientsCrud.dtos.ClientDTO;
import com.devsuperior.clientsCrud.entities.Client;
import com.devsuperior.clientsCrud.repositories.ClientRepository;
import com.devsuperior.clientsCrud.services.exceptions.DataBaseException;
import com.devsuperior.clientsCrud.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
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

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client c = repository.getReferenceById(id);
			copyDtoToEntity(dto, c);		
			c = repository.save(c);
			return new ClientDTO(c);
		} catch(EntityNotFoundException r) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id)) 
			throw new ResourceNotFoundException("Recurso não encontrado");
		
		try {
	       	repository.deleteById(id);    		
		} catch (DataIntegrityViolationException e) {
	       	throw new DataBaseException("Falha de integridade referencial");
	   	}

	}
	
	private void copyDtoToEntity(ClientDTO dto, Client c) {
		c.setName(dto.getName());
		c.setCpf(dto.getCpf());
		c.setIncome(dto.getIncome());
		c.setBirthDate(dto.getBirthDate());
		c.setChildren(dto.getChildren());
	}
}
