package com.example.testkku.service;

import com.example.testkku.dto.CreatePhoneContactDto;
import com.example.testkku.dto.EditPhoneContactDto;
import com.example.testkku.dto.SetPhoneToClientDto;
import com.example.testkku.entity.Client;
import com.example.testkku.entity.PhoneContacts;
import com.example.testkku.exception.ClientException;
import com.example.testkku.exception.PhoneContacttException;
import com.example.testkku.repo.ClientRepo;
import com.example.testkku.repo.PhoneContactRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneContactsServiceImpl implements PhoneContactsService {

    private final PhoneContactRepo phoneContactRepo;
    private final ClientRepo clientRepo;

    @Override
    public PhoneContacts createPhoneContact(CreatePhoneContactDto createPhoneContactDto) {
        Optional<PhoneContacts> phoneContactsOptional = phoneContactRepo.findByPhoneNumber(createPhoneContactDto.getPhoneNumber());
        if (phoneContactsOptional.isPresent()) {
            return phoneContactsOptional.get();
        }
        PhoneContacts phoneContacts = new PhoneContacts();
        phoneContacts.setPhoneNumber(createPhoneContactDto.getPhoneNumber());
        return phoneContactRepo.save(phoneContacts);
    }

    @Override
    public PhoneContacts editPhoneContact(EditPhoneContactDto editPhoneContactDto) throws PhoneContacttException {
        PhoneContacts phoneContacts = phoneContactRepo.findByPhoneNumber(editPhoneContactDto.getOldPhoneNumber())
                .orElseThrow(() -> new PhoneContacttException("phone contact not found"));
        boolean updated = false;
        if (editPhoneContactDto.getNewPhoneNumber() != null) {
            phoneContacts.setPhoneNumber(editPhoneContactDto.getNewPhoneNumber());
            updated = true;
        }
        if (updated) {
            phoneContacts = phoneContactRepo.save(phoneContacts);
        }
        return phoneContacts;
    }

    @Override
    public Client setPhoneToClient(SetPhoneToClientDto setPhoneToClientDto) throws ClientException, PhoneContacttException {
        Client client = clientRepo.findClientById(setPhoneToClientDto.getClientId())
                .orElseThrow(() -> new ClientException("client by this id not found"));
        PhoneContacts phoneContacts = phoneContactRepo.findByPhoneNumber(setPhoneToClientDto.getPhoneNumber())
                .orElseThrow(() -> new PhoneContacttException("phone number not found"));
        client.getPhoneContacts().add(phoneContacts);
        return clientRepo.save(client);
    }
}
