package ru.BTLab.test.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.BTLab.test.adapters.DateTimeAdapter;
import ru.BTLab.test.dto.TruancyCreateDto;
import ru.BTLab.test.dto.TruancyReplyDto;
import ru.BTLab.test.models.Reason;
import ru.BTLab.test.models.Truancy;
import ru.BTLab.test.models.TruancyState;
import ru.BTLab.test.services.UserService;

@Service
@RequiredArgsConstructor
public class TruancyMapper {

    private final UserService service;

    public Truancy dtoToObject(TruancyCreateDto dto) {

        Reason reason = Reason.builder()
                .id(dto.getReason())
                .name(TruancyState.getNameById(dto.getReason()))
                .build();

        return Truancy.builder()
                .reason(reason)
                .startDate(DateTimeAdapter.stringToDate(dto.getStartDate()))
                .duration(dto.getDuration())
                .discounted(dto.getDiscounted())
                .description(dto.getDescription())
                .user(service.findUserById(dto.getUser()))
                .build();
    }

    public static TruancyReplyDto objectToReplyDto(Truancy truancy) {

        StringBuilder user = new StringBuilder();

        user.append("id ").append(truancy.getUser().getId()).append(", ").append(truancy.getUser().getFirstName()).append(" ").append(truancy.getUser().getLastName());

        if (truancy.getUser().getPatronymic() != null) {
            user.append(" ").append(truancy.getUser().getPatronymic());
        }

        user.append(", ").append(DateTimeAdapter.dateToString(truancy.getUser().getDateOfBird()));

        return TruancyReplyDto.builder()
                .id(truancy.getId())
                .reason(truancy.getReason().getName())
                .startDate(DateTimeAdapter.dateToString(truancy.getStartDate()))
                .duration(truancy.getDuration())
                .discounted(truancy.getDiscounted())
                .description(truancy.getDescription())
                .user(user.toString())
                .build();
    }
}
