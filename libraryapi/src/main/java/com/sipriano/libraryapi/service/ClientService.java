package com.sipriano.libraryapi.service;

import com.sipriano.libraryapi.model.Client;
import com.sipriano.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public Client salvar(Client client) {
        String senhaCripto = passwordEncoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCripto);
        return clientRepository.save(client);
    }

    public Client obterClientePorId(UUID id) {
        return clientRepository.findClientById(id);
    }

}
