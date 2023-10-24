package com.example.testkku.dto;

import com.example.testkku.enums.ContactsType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    private List< String> phoneContacts;

    private List< String> emailContacts;

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneContacts=" + phoneContacts +
                ", emailContacts=" + emailContacts +
                '}';
    }
}
