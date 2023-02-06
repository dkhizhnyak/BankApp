package com.example.bankapp.service;

import com.example.bankapp.entity.Client;
import com.example.bankapp.persistance.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client getClient(Long id) {
        return clientRepository.getReferenceById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
