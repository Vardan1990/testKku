package com.example.testkku.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SetPhoneToClientDto {

    @NotBlank
    String phoneNumber;

    @NotBlank
    Long clientId;
}
