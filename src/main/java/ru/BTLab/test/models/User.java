package ru.BTLab.test.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private final Long id;
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "patronymic", length = 100)
    private String patronymic;
    @Column(name = "date_of_bird")
    private LocalDate dateOfBird;
    @Column(name = "email", nullable = false, length = 320, unique = true)
    private String email;
    @Column(name = "telephone", nullable = false, length = 11, unique = true)
    private String telephone;
    @Column(name = "passport_series", nullable = false, length = 4)
    private String passportSeries;
    @Column(name = "passport_number", nullable = false, length = 6)
    private String passportNumber;
    @Column(name = "passport_issued", nullable = false, length = 320)
    private String passportIssued; //кем выдан
    @Column(name = "passport_date")
    private LocalDate passportDate;
    @Column(name = "passport_kp", nullable = false, length = 6)
    private String passportKp;  //код подразделения
}
