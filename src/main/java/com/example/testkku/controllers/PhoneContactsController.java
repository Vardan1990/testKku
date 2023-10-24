package com.example.testkku.controllers;

import com.example.testkku.dto.CreatePhoneContactDto;
import com.example.testkku.dto.EditPhoneContactDto;
import com.example.testkku.dto.SetPhoneToClientDto;
import com.example.testkku.entity.Client;
import com.example.testkku.entity.PhoneContacts;
import com.example.testkku.exception.ClientException;
import com.example.testkku.exception.PhoneContacttException;
import com.example.testkku.service.PhoneContactsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(path = "/api/phone")
@RequiredArgsConstructor
public class PhoneContactsController {

    private final PhoneContactsService phoneContactsService;

    /* пример запроса
    {
      "phoneNumber": "4896321741"
      }
   */

    @PostMapping(path = "/createPhone")
    public ResponseEntity<PhoneContacts> createPhoneContact(@RequestBody CreatePhoneContactDto createPhoneContactDto) {
        log.info("phoneContact#create by dto {}:", createPhoneContactDto);
        return ResponseEntity.ok(phoneContactsService.createPhoneContact(createPhoneContactDto));
    }

    /* пример запроса
    {
       "oldPhoneNumber": "56456498484",
       "newPhoneNumber": "66456498484"
            }
   */
    @PutMapping(path = "/editPhone")
    public ResponseEntity<PhoneContacts> editPhoneContact(@RequestBody @Valid EditPhoneContactDto editPhoneContactDto) throws PhoneContacttException {
        log.info("phoneContact#edit by dto {}:", editPhoneContactDto);
        return ResponseEntity.ok(phoneContactsService.editPhoneContact(editPhoneContactDto));
    }

    /* пример запроса
      {
       "phoneNumber": "66456498484",
        "clientId": 5
       }
   */
    @PostMapping(path = "/setPhoneToClient")
    public ResponseEntity<Client> setPhoneToClient(@RequestBody SetPhoneToClientDto setPhoneToClientDto) throws ClientException, PhoneContacttException {
        log.info("phoneContact#set to client by dto {}:", setPhoneToClientDto);
        return ResponseEntity.ok(phoneContactsService.setPhoneToClient(setPhoneToClientDto));
    }
}
