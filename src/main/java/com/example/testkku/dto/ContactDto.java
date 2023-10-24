package com.example.testkku.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {


    private String phone;
    @Email
    private String email;

    @Override
    public String toString() {
        return "ContactDto{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
