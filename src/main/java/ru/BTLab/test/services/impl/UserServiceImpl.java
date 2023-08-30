package ru.BTLab.test.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.BTLab.test.adapters.DateTimeAdapter;
import ru.BTLab.test.dto.UserCreateDto;
import ru.BTLab.test.dto.UserReplyDto;
import ru.BTLab.test.exceptions.ConflictException;
import ru.BTLab.test.exceptions.ObjectNotFoundException;
import ru.BTLab.test.mappers.UserMapper;
import ru.BTLab.test.models.User;
import ru.BTLab.test.repositories.UserRepository;
import ru.BTLab.test.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserReplyDto updateUser(UserCreateDto dto, Long userId) {

        User user = findUserById(userId);
        User newUser = validation(user, dto);

        return UserMapper.objectToReplyDto(repository.save(newUser));
    }

    @Override
    public List<UserReplyDto> getUsers(Integer from, Integer size) {

        return repository.findAll(PageRequest.of(from / size, size)).stream().map(UserMapper::objectToReplyDto).collect(Collectors.toList());
    }

    @Override
    public UserReplyDto getUserByTelephone(String telephone) {

        Optional<User> user = repository.findByTelephone(telephone);

        if (user.isPresent()) {
            return UserMapper.objectToReplyDto(user.get());
        } else {
            return UserReplyDto.builder().build();
        }
    }

    @Override
    public UserReplyDto createUser(UserCreateDto dto) {

        User user = repository.save(mapper.dtoToObject(dto));

        return UserMapper.objectToReplyDto(user);
    }

    @Override
    public void deleteUser(Long userId) {

        repository.delete(findUserById(userId));
    }

    @Override
    public UserReplyDto getUser(Long userId) {

        return UserMapper.objectToReplyDto(findUserById(userId));
    }

    @Override
    public User findUserById(Long userId) {

        return repository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("There is no user with this id"));
    }

    private User validation(User user, UserCreateDto dto) {

        if (dto.getTelephone() != null && !dto.getTelephone().isBlank() && !dto.getTelephone().isEmpty()) {
            if (dto.getTelephone().length() == 11) {
                user.setTelephone(dto.getTelephone());
            } else {
                throw new ConflictException("incorrect telephone");
            }
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank() && !dto.getEmail().isEmpty()) {
            if (!dto.getEmail().equals(user.getEmail())) {
                throw new ConflictException("it is forbidden to change email");
            }
        }
        if (dto.getFirstName() != null && !dto.getFirstName().isBlank() && !dto.getFirstName().isEmpty()) {
            if (dto.getFirstName().length() > 2 && dto.getFirstName().length() < 100) {
                user.setFirstName(dto.getFirstName());
            } else {
                throw new ConflictException("incorrect firstName");
            }
        }
        if (dto.getLastName() != null && !dto.getLastName().isBlank() && !dto.getLastName().isEmpty()) {
            if (dto.getLastName().length() > 2 && dto.getLastName().length() < 100) {
                user.setLastName(dto.getLastName());
            } else {
                throw new ConflictException("incorrect lastName");
            }
        }
        if (dto.getPatronymic() != null && !dto.getPatronymic().isBlank() && !dto.getPatronymic().isEmpty()) {
            if (dto.getPatronymic().length() > 2 && dto.getPatronymic().length() < 100) {
                user.setPatronymic(dto.getPatronymic());
            } else {
                throw new ConflictException("incorrect patronymic");
            }
        }
        if (dto.getPassportSeries() != null && !dto.getPassportSeries().isBlank() && !dto.getPassportSeries().isEmpty()) {
            if (dto.getPassportSeries().length() == 4) {
                user.setPassportSeries(dto.getPassportSeries());
            } else {
                throw new ConflictException("incorrect passportSeries");
            }
        }
        if (dto.getPassportNumber() != null && !dto.getPassportNumber().isBlank() && !dto.getPassportNumber().isEmpty()) {
            if (dto.getPassportNumber().length() == 6) {
                user.setPassportNumber(dto.getPassportNumber());
            } else {
                throw new ConflictException("incorrect passportNumber");
            }
        }
        if (dto.getPassportIssued() != null && !dto.getPassportIssued().isBlank() && !dto.getPassportIssued().isEmpty()) {
            if (dto.getPassportIssued().length() > 2 && dto.getPassportIssued().length() < 1000) {
                user.setPassportIssued(dto.getPassportIssued());
            } else {
                throw new ConflictException("incorrect patronymic");
            }
        }
        if (dto.getPassportDate() != null && !dto.getPassportDate().isBlank() && !dto.getPassportDate().isEmpty()) {
            if (dto.getPassportDate().length() == 10) {
                user.setPassportDate(DateTimeAdapter.stringToDate(dto.getPassportDate()));
            } else {
                throw new ConflictException("incorrect passportDate");
            }
        }
        if (dto.getPassportKp() != null && !dto.getPassportKp().isBlank() && !dto.getPassportKp().isEmpty()) {
            if (dto.getPassportKp().length() == 6) {
                user.setPassportKp(dto.getPassportKp());
            } else {
                throw new ConflictException("incorrect passportKp");
            }
        }

        return user;
    }
}
