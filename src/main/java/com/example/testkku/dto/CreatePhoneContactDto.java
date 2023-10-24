package com.example.testkku.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePhoneContactDto {

    @NotBlank
    String phoneNumber;
}
