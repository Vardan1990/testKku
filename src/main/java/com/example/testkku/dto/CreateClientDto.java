package com.example.testkku.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    private List<String> phoneContacts;

    private List<String> emailContacts;

}
