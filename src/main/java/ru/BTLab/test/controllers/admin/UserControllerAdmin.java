package ru.BTLab.test.controllers.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.BTLab.test.dto.UserCreateDto;
import ru.BTLab.test.dto.UserReplyDto;
import ru.BTLab.test.exceptions.ConflictException;
import ru.BTLab.test.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/admin")
public class UserControllerAdmin {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserReplyDto createUser(@Valid @RequestBody UserCreateDto dto) {

        log.info("Received a request to create a user " + dto);

        return service.createUser(dto);
    }

    @GetMapping("/telephone/{telephone}")
    public UserReplyDto getUserByTelephone(@PathVariable String telephone) {

        log.info("Received a request to search user for telephone {}", telephone);

        if (telephone.length() != 11) {
            throw new ConflictException("wrong number dialed");
        }

        return service.getUserByTelephone(telephone);
    }

    @GetMapping("/{userId}")
    public UserReplyDto getUserById(@PathVariable Long userId) {

        log.info("Received a request to search user for id {}", userId);

        return service.getUser(userId);
    }

    @GetMapping
    public List<UserReplyDto> getUsers(@RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
                                       @RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size) {

        log.info("Received a request to search all users}");

        return service.getUsers(from, size);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {

        log.info("Received a request to delete a user with an id " + userId);

        service.deleteUser(userId);
    }

    @PatchMapping("/{userId}")
    public UserReplyDto updateUser(@RequestBody UserCreateDto dto,
                                   @PathVariable Long userId) {

        log.info("Received a request to update a user {}. userId = {}", dto, userId);

        return service.updateUser(dto, userId);
    }
}
