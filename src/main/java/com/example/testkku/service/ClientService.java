package com.example.testkku.service;

import com.example.testkku.dto.ClientDto;
import com.example.testkku.dto.ContactDto;
import com.example.testkku.entity.Client;
import com.example.testkku.exception.ClientException;

import java.util.List;

public interface ClientService {

    Client createClientFromDto(ClientDto clientDto);

    void setNewClientContactById(Long clientId, ContactDto contactDto) throws ClientException;

    List<Client> getClientsList();

    Client findClientById(Long clientId) throws ClientException;

    ClientDto getClientContactsById(Long clientId);

    List<String> getClientContactsByContactTypeAndById(String contactType, Long clientId);


}
