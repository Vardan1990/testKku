package com.example.testkku.controllers;

import com.example.testkku.dto.ContactsDto;
import com.example.testkku.dto.CreateClientDto;
import com.example.testkku.entity.Client;
import com.example.testkku.exception.ClientException;
import com.example.testkku.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    /* пример запроса
     *{
        "name": "",
        "surname": "",
        "phoneContacts": [""],
        "emailContacts": [""]
         }
     */
    @PostMapping(path = "/createClient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody CreateClientDto createClientDto) throws ClientException {
        log.info("Client#create by dto {}", createClientDto);
        if (createClientDto.getName() == null || createClientDto.getSurname() == null) {
            throw new ClientException("wrong client name or surname");
        }
        return ResponseEntity.ok(clientService.createClientFromDto(createClientDto));
    }

     /* пример запроса
      request param name- clientId
      {
       "phone": "",
       "email": ""
      }
     */

    @PutMapping(path = "/setNewContact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setNewContact(@RequestParam(name = "clientId") Long clientId, @RequestBody ContactsDto contactsDto) throws ClientException {
        log.info("Client#set new contact  {}", contactsDto);
        clientService.setNewClientContactById(clientId, contactsDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getClientList")
    public ResponseEntity<List<Client>> getClientList() {
        log.info("Client#get list");
        return ResponseEntity.ok(clientService.getClientsList());

    }
   /* пример запроса
      request param name- clientId
   */

    @GetMapping(path = "/getById")
    public ResponseEntity<Client> getClientById(@RequestParam(name = "clientId") Long clientId) throws ClientException {
        log.info("Client#get by id  {}", clientId);
        return ResponseEntity.ok(clientService.findClientById(clientId));
    }

    /* пример запроса
      request param name- clientId
   */

    @GetMapping(path = "/getContacts")
    public ResponseEntity<CreateClientDto> getClientContactsById(@RequestParam(name = "clientId") Long clientId) {
        log.info("Client#get contacts by id {}", clientId);
        return ResponseEntity.ok(clientService.getClientContactsById(clientId));
    }

    /* пример запроса
      request param name- clientId
      request param name- contactType //value - PHONE,EMAIL//
   */

    @GetMapping(path = "/getContactsByTypeAndId")
    public ResponseEntity<List<String>> getContactsByTypeAndId(@RequestParam(name = "contactType") String contactType, @RequestParam(name = "clientId") Long clientId) {
        log.info("Client#et contacts bay contactType and by id  {} ,{}", contactType, clientId);
        return ResponseEntity.ok(clientService.getClientContactsByContactTypeAndById(contactType, clientId));
    }
}
