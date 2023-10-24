package com.example.testkku.service;

import com.example.testkku.dto.ContactsDto;
import com.example.testkku.dto.CreateClientDto;
import com.example.testkku.entity.Client;
import com.example.testkku.entity.PhoneContacts;
import com.example.testkku.enums.ContactsType;
import com.example.testkku.exception.ClientException;
import com.example.testkku.repo.ClientRepo;
import com.example.testkku.repo.PhoneContactRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final PhoneContactRepo phoneContactRepo;

    @Override
    @Transactional
    public Client createClientFromDto(CreateClientDto createClientDto) {
        Optional<Client> clientOptional = clientRepo.findByNameAndSurname(createClientDto.getName(), createClientDto.getSurname());
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        }
        List<PhoneContacts> phoneContactsList = new ArrayList<>(createClientDto.getPhoneContacts().size());
        PhoneContacts phoneContacts = new PhoneContacts();
        List<String> stringContactList = createClientDto.getPhoneContacts();
        for (String s : stringContactList) {
            phoneContacts.setPhoneNumber(s);
            phoneContactsList.add(phoneContacts);
        }
        phoneContactRepo.saveAll(phoneContactsList);
        Client client = new Client();
        client.setName(createClientDto.getName());
        client.setSurname(createClientDto.getSurname());
        client.setPhoneContacts(phoneContactsList);
        client.setEmailContacts(createClientDto.getEmailContacts());
        log.info("going save client into repo");
        return clientRepo.save(client);
    }

    @Override
    public void setNewClientContactById(Long clientId, ContactsDto contactsDto) throws ClientException {
        Optional<Client> clientOptional = clientRepo.findClientById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientException("client by this id is not present");
        }
        Client client = clientOptional.get();
        if (!contactsDto.getPhone().isEmpty()) {
            PhoneContacts phoneContacts = new PhoneContacts();
            phoneContacts.setPhoneNumber(contactsDto.getPhone());
            phoneContactRepo.save(phoneContacts);
            client.getPhoneContacts().add(phoneContacts);
        }
        if (!contactsDto.getEmail().isEmpty()) {
            client.getEmailContacts().add(contactsDto.getEmail());
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
    public CreateClientDto getClientContactsById(Long clientId) {
        Optional<Client> clientOptional = clientRepo.findClientById(clientId);
        return clientOptional.map(client -> new CreateClientDto(client.getName(),
                client.getSurname(),
                client.getPhoneContacts().stream().map(PhoneContacts::getPhoneNumber).collect(Collectors.toList()),
                client.getEmailContacts())).orElse(null);
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
                return client.getPhoneContacts().stream().map(PhoneContacts::getPhoneNumber).collect(Collectors.toList());
            }
        }
        return null;
    }
}
