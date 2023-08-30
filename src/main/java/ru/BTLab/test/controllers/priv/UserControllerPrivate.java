package ru.BTLab.test.controllers.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.BTLab.test.dto.UserReplyDto;
import ru.BTLab.test.exceptions.BadRequestException;
import ru.BTLab.test.services.UserService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserControllerPrivate {

    private final UserService service;

    @GetMapping("/{userId}")
    public UserReplyDto getUser(@PathVariable Long userId,
                                @RequestHeader("X-Sharer-Owner-Id") Long ownerId) {

        if (!userId.equals(ownerId)) {
            throw new BadRequestException("You cannot request another user's data");
        }

        log.info("Received a request to search user for id {}", userId);

        return service.getUser(userId);
    }
}
