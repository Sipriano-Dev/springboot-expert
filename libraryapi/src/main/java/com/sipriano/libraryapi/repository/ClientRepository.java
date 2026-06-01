package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findClientByClientId(String clientId);
}
