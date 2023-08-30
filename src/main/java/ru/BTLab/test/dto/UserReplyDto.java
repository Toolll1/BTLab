package ru.BTLab.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class UserReplyDto {

    private final Long id;
    private final String fullName;
    private final String dateOfBird;
    private final String email;
    private final String telephone;
    private final String passport;
}
