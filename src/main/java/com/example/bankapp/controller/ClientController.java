package com.example.bankapp.controller;

import com.example.bankapp.entity.Client;
import com.example.bankapp.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("client/save")
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("client/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
