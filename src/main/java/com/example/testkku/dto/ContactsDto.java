package com.example.testkku.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ContactsDto {

    private String phone;

    @Email
    private String email;

}
