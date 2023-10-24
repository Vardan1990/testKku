package com.example.testkku.service;

import com.example.testkku.dto.ClientDto;
import com.example.testkku.dto.ContactDto;
import com.example.testkku.entity.Client;
import com.example.testkku.enums.ContactsType;
import com.example.testkku.exception.ClientException;
import com.example.testkku.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;

    @Override
    public Client createClientFromDto(ClientDto clientDto) {
        Optional<Client> clientOptional = clientRepo.findByNameAndSurname(clientDto.getName(), clientDto.getSurname());
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        }
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setPhoneContacts(clientDto.getPhoneContacts());
        client.setEmailContacts(clientDto.getEmailContacts());
        log.info("going save client into repo");
        return clientRepo.save(client);
    }

    @Override
    public void setNewClientContactById(Long clientId, ContactDto contactDto) throws ClientException {
        Optional<Client> clientOptional = clientRepo.findClientById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientException("client by this id is not present");
        }
        Client client = clientOptional.get();
        if (!contactDto.getPhone().isEmpty()) {
            client.getPhoneContacts().add(contactDto.getPhone());
        }
        if (!contactDto.getEmail().isEmpty()) {
            client.getEmailContacts().add(contactDto.getEmail());
        }
        clientRepo.save(client);
    }

    @Override
    public List<Client> getClientsList() {
        return clientRepo.findAll();
    }

    @Override
    public Client findClientById(Long clientId) throws ClientException {
        Optional<Client> clientOptional = clientRepo.findClientById(clientId);
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        }
        throw new ClientException("Client by this id not found");
    }

    @Override
    public ClientDto getClientContactsById(Long clientId) {
        Optional<Client> clientOptional = clientRepo.findClientById(clientId);
        return clientOptional.map(client -> new ClientDto(client.getName(), client.getSurname(), client.getPhoneContacts(), client.getEmailContacts())).orElse(null);
    }

    @Override
    public List<String> getClientContactsByContactTypeAndById(String contactType, Long clientId) {
        Optional<Client> clientOptional = clientRepo.findClientById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            if (ContactsType.EMAIL.name().equalsIgnoreCase(contactType)) {
                return client.getEmailContacts();
            }
            if (ContactsType.PHONE.name().equalsIgnoreCase(contactType)) {
                return client.getPhoneContacts();
            }
        }
        return null;
    }
}
