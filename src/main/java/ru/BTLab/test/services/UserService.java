package ru.BTLab.test.services;

import ru.BTLab.test.dto.UserCreateDto;
import ru.BTLab.test.dto.UserReplyDto;
import ru.BTLab.test.models.User;

import java.util.List;

public interface UserService {

    UserReplyDto createUser(UserCreateDto dto);

    void deleteUser(Long userId);

    UserReplyDto getUser(Long userId);

    UserReplyDto updateUser(UserCreateDto dto, Long userId);

    List<UserReplyDto> getUsers(Integer from, Integer size);

    UserReplyDto getUserByTelephone(String telephone);

    User findUserById(Long userId);
}
