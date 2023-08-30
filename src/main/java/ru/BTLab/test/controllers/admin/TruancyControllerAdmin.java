package ru.BTLab.test.controllers.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.BTLab.test.dto.TruancyCreateDto;
import ru.BTLab.test.dto.TruancyReplyDto;
import ru.BTLab.test.services.TruancyService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/truancy/admin")
public class TruancyControllerAdmin {

    private final TruancyService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TruancyReplyDto createTruancy(@Valid @RequestBody TruancyCreateDto dto) {

        log.info("Received a request to create a truancy " + dto);

        return service.createTruancy(dto);
    }

    @GetMapping("/{truancyId}")
    public TruancyReplyDto getTruancyById(@PathVariable Long truancyId) {

        log.info("Received a request to search truancy for id {}", truancyId);

        return service.getTruancyById(truancyId);
    }

    @GetMapping
    public List<TruancyReplyDto> getTruancy(@RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
                                            @RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size) {

        log.info("Received a request to search all truancy");

        return service.getTruancy(from, size);
    }

    @GetMapping("/user/{userId}")
    public List<TruancyReplyDto> getTruancyByUserId(@RequestParam(value = "from", defaultValue = "0") @Min(0) Integer from,
                                                    @RequestParam(value = "size", defaultValue = "10") @Min(1) Integer size,
                                                    @PathVariable Long userId) {

        log.info("Received a request to search truancy by user id {}", userId);

        return service.getTruancyByUserId(userId, from, size);
    }

    @DeleteMapping("/{truancyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTruancy(@PathVariable Long truancyId) {

        log.info("Received a request to delete a truancy with an id " + truancyId);

        service.deleteTruancy(truancyId);
    }

    @PatchMapping("/{truancyId}")
    public TruancyReplyDto updateTruancy(@RequestBody TruancyCreateDto dto,
                                         @PathVariable Long truancyId) {

        log.info("Received a request to update a truancy {}. truancyId = {}", dto, truancyId);

        return service.updateTruancy(dto, truancyId);
    }
}
