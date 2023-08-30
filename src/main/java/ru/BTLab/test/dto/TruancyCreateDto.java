package ru.BTLab.test.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class TruancyCreateDto {

    @NotNull
    private Long reason; //id причины об отсутствии
    @NotBlank
    private String startDate;  //начало периода отсутствия
    @NotNull
    private Integer duration; //срок отсутствия
    @NotNull
    private Boolean discounted; //учтено в зп или нет
    @NotBlank
    @Size(max = 1024)
    private String description; // описание
    @NotNull
    private Long user; //id отсутствующего сотрудника
}
