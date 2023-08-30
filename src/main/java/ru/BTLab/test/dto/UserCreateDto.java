package ru.BTLab.test.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class UserCreateDto {

    @NotBlank
    @Size(min = 2, max = 100)
    private final String lastName;
    @NotBlank
    @Size(min = 2, max = 100)
    private final String firstName;
    @Size(min = 2, max = 100)
    private final String patronymic;
    @NotBlank
    @Size(min = 10, max = 10)
    private final String dateOfBird;
    @Email
    @NotBlank
    @Size(min = 6, max = 320)
    private final String email;
    @NotBlank
    @Size(min = 4, max = 4)
    private final String passportSeries;
    @NotBlank
    @Size(min = 6, max = 6)
    private final String passportNumber;
    @NotBlank
    @Size(min = 2, max = 1000)
    private final String passportIssued; //кем выдан
    @NotBlank
    @Size(min = 10, max = 10)
    private final String passportDate;
    @NotBlank
    @Size(min = 6, max = 6)
    private final String passportKp;  //код подразделения
    @NotBlank
    @Size(min = 11, max = 11)
    private  final String telephone;
}
