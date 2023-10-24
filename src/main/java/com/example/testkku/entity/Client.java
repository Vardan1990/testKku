package com.example.testkku.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Id
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created", updatable = false, nullable = false)
    @Getter
    private LocalDateTime created;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_to_phone",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id"))
    private List<PhoneContacts> phoneContacts;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", name = "email_contacts")
    private List<String> emailContacts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return new EqualsBuilder().append(id, client.id).append(created, client.created).append(name, client.name).append(surname, client.surname).append(phoneContacts, client.phoneContacts).append(emailContacts, client.emailContacts).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(created).append(name).append(surname).append(phoneContacts).append(emailContacts).toHashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneContacts=" + phoneContacts +
                ", emailContacts=" + emailContacts +
                '}';
    }
}
