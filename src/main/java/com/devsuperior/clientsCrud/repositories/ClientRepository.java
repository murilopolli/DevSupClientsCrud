package com.devsuperior.clientsCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.clientsCrud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
