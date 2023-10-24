package com.example.testkku.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "phone_contacts")
@NoArgsConstructor
@AllArgsConstructor
public class PhoneContacts {

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

    @Column(name = "phone_number")
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PhoneContacts that = (PhoneContacts) o;

        return new EqualsBuilder().append(id, that.id).append(created, that.created).append(phoneNumber, that.phoneNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(created).append(phoneNumber).toHashCode();
    }

    @Override
    public String toString() {
        return "PhoneContacts{" +
                "id=" + id +
                ", created=" + created +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
