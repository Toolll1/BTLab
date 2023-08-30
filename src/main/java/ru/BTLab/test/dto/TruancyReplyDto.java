package ru.BTLab.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TruancyReplyDto {

    private final Long id;
    private final String reason;
    private final String startDate;
    private final Integer duration;
    private final Boolean discounted;
    private final String description;
    private final String user;
}
