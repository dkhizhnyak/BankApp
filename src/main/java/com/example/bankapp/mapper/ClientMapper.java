package com.example.bankapp.mapper;

import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto clientToDto(Client client) {
        return ClientDto.builder()
                .address(client.getAddress())
                .age(client.getAge())
                .name(client.getName()).build();
    }
}
