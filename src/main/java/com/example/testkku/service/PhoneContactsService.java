package com.example.testkku.service;

import com.example.testkku.dto.CreatePhoneContactDto;
import com.example.testkku.dto.EditPhoneContactDto;
import com.example.testkku.dto.SetPhoneToClientDto;
import com.example.testkku.entity.Client;
import com.example.testkku.entity.PhoneContacts;
import com.example.testkku.exception.ClientException;
import com.example.testkku.exception.PhoneContacttException;

public interface PhoneContactsService {

    PhoneContacts createPhoneContact(CreatePhoneContactDto createPhoneContactDto);

    PhoneContacts editPhoneContact(EditPhoneContactDto editPhoneContactDto) throws PhoneContacttException;

    Client setPhoneToClient(SetPhoneToClientDto setPhoneToClientDto) throws ClientException, PhoneContacttException;

}
