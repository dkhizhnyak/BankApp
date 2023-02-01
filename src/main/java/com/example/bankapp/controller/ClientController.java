package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;
import com.example.bankapp.mapper.ClientMapper;
import com.example.bankapp.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    final ClientService clientService;

    final ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping("/save")
    public ClientDto saveClient(@RequestBody Client client) {
        clientService.save(client);
        return clientMapper.clientToDto(client);
    }

    @GetMapping("/client/{id}")
    public ClientDto getClient(@PathVariable Long id) {
        return clientMapper.clientToDto(clientService.getClient(id));
    }

    @GetMapping("/clients")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients().stream().map(clientMapper::clientToDto).collect(Collectors.toList());
    }
}
