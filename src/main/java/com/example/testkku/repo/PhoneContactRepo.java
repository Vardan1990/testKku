package com.example.testkku.repo;

import com.example.testkku.entity.Client;
import com.example.testkku.entity.PhoneContacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneContactRepo extends JpaRepository<PhoneContacts, Long> {

    Optional<PhoneContacts> findByPhoneNumber(String phoneNumber);

}
