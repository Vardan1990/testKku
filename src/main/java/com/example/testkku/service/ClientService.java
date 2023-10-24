package com.example.testkku.service;

import com.example.testkku.dto.ContactsDto;
import com.example.testkku.dto.CreateClientDto;
import com.example.testkku.entity.Client;
import com.example.testkku.exception.ClientException;

import java.util.List;

public interface ClientService {

    Client createClientFromDto(CreateClientDto createClientDto);

    void setNewClientContactById(Long clientId, ContactsDto contactsDto) throws ClientException;

    List<Client> getClientsList();

    Client findClientById(Long clientId) throws ClientException;

    CreateClientDto getClientContactsById(Long clientId);

    List<String> getClientContactsByContactTypeAndById(String contactType, Long clientId);


}
