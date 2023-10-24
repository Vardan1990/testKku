package com.example.testkku.controllers;

import com.example.testkku.dto.ClientDto;
import com.example.testkku.dto.ContactDto;
import com.example.testkku.entity.Client;
import com.example.testkku.exception.ClientException;
import com.example.testkku.service.ClientServiceImpl;
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

    private final ClientServiceImpl clientServiceImpl;


    @PostMapping(path = "/createClient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(@RequestBody ClientDto clientDto) throws ClientException {
        log.info("Client#create by dto {}", clientDto);
        if (clientDto.getName() == null || clientDto.getSurname() == null) {
            throw new ClientException("wrong client name or surname");
        }
        return ResponseEntity.ok(clientServiceImpl.createClientFromDto(clientDto));
    }

    @PutMapping(path = "/setNewContact", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setNewContact(@RequestParam(name = "clientId") Long clientId, @RequestBody ContactDto contactDto) throws ClientException {
        log.info("Client#set new contact  {}", contactDto);
        clientServiceImpl.setNewClientContactById(clientId, contactDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getClientList")
    public ResponseEntity<List<Client>> getClientList() {
        log.info("Client#get list");
        return ResponseEntity.ok(clientServiceImpl.getClientsList());

    }

    @GetMapping(path = "/getById")
    public ResponseEntity<Client> getClientById(@RequestParam(name = "clientId") Long clientId) throws ClientException {
        log.info("Client#get by id  {}", clientId);
        return ResponseEntity.ok(clientServiceImpl.findClientById(clientId));
    }

    @GetMapping(path = "/getContacts")
    public ResponseEntity<ClientDto> getClientContactsById(@RequestParam(name = "clientId") Long clientId) {
        log.info("Client#get contacts by id {}", clientId);
        return ResponseEntity.ok(clientServiceImpl.getClientContactsById(clientId));
    }

    @GetMapping(path = "/getContactsByTypeAndId")
    public ResponseEntity<List<String>> getContactsByTypeAndId(@RequestParam(name = "contactType") String contactType, @RequestParam(name = "clientId") Long clientId) {
        log.info("Client#et contacts bay contactType and by id  {} ,{}", contactType, clientId);
        return ResponseEntity.ok(clientServiceImpl.getClientContactsByContactTypeAndById(contactType, clientId));
    }
}
