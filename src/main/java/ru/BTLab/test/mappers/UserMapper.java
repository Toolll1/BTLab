package ru.BTLab.test.mappers;

import org.springframework.stereotype.Service;
import ru.BTLab.test.adapters.DateTimeAdapter;
import ru.BTLab.test.dto.UserCreateDto;
import ru.BTLab.test.dto.UserReplyDto;
import ru.BTLab.test.models.User;

@Service
public class UserMapper {

    public static UserReplyDto objectToReplyDto(User user) {

        StringBuilder fullName = new StringBuilder();
        String passport = "ПаспортРФ " + user.getPassportSeries().substring(0, 2) + " " + user.getPassportSeries().substring(2)
                + " " + user.getPassportNumber() + " выдан " + user.getPassportIssued() + " " + DateTimeAdapter.dateToString(user.getPassportDate())
                + "г. кп " + user.getPassportKp().substring(0, 3) + "-" + user.getPassportKp().substring(3);

        fullName.append(user.getFirstName()).append(" ").append(user.getLastName());

        if (user.getPatronymic() != null) {
            fullName.append(" ").append(user.getPatronymic());
        }

        return UserReplyDto.builder()
                .id(user.getId())
                .fullName(fullName.toString())
                .dateOfBird(DateTimeAdapter.dateToString(user.getDateOfBird()))
                .email(user.getEmail())
                .telephone(user.getTelephone())
                .passport(passport)
                .build();
    }

    public User dtoToObject(UserCreateDto dto) {

        return User.builder()
                .email(dto.getEmail())
                .telephone(dto.getTelephone())
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .patronymic(dto.getPatronymic())
                .dateOfBird(DateTimeAdapter.stringToDate(dto.getDateOfBird()))
                .passportSeries(dto.getPassportSeries())
                .passportNumber(dto.getPassportNumber())
                .passportIssued(dto.getPassportIssued())
                .passportDate(DateTimeAdapter.stringToDate(dto.getPassportDate()))
                .passportKp(dto.getPassportKp())
                .build();
    }
}
