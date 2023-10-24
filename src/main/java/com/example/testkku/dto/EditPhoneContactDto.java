package com.example.testkku.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EditPhoneContactDto {

    @NotBlank
    String oldPhoneNumber;

    @NotBlank
    String newPhoneNumber;
}
