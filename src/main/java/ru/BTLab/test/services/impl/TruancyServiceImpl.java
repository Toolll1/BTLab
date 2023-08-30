package ru.BTLab.test.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.BTLab.test.adapters.DateTimeAdapter;
import ru.BTLab.test.dto.TruancyCreateDto;
import ru.BTLab.test.dto.TruancyReplyDto;
import ru.BTLab.test.exceptions.ConflictException;
import ru.BTLab.test.exceptions.ObjectNotFoundException;
import ru.BTLab.test.mappers.TruancyMapper;
import ru.BTLab.test.models.Reason;
import ru.BTLab.test.models.Truancy;
import ru.BTLab.test.models.TruancyState;
import ru.BTLab.test.repositories.TruancyRepository;
import ru.BTLab.test.services.TruancyService;
import ru.BTLab.test.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TruancyServiceImpl implements TruancyService {

    private final TruancyRepository repository;
    private final TruancyMapper mapper;
    private final UserService userService;

    @Override
    public TruancyReplyDto createTruancy(TruancyCreateDto dto) {

        Truancy truancy = repository.save(mapper.dtoToObject(dto));

        return TruancyMapper.objectToReplyDto(truancy);
    }

    @Override
    public TruancyReplyDto getTruancyById(Long truancyId) {

        return TruancyMapper.objectToReplyDto(findTruancyById(truancyId));
    }

    @Override
    public List<TruancyReplyDto> getTruancy(Integer from, Integer size) {

        return repository.findAll(PageRequest.of(from / size, size, Sort.by("startDate").descending()))
                .stream()
                .map(TruancyMapper::objectToReplyDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TruancyReplyDto> getTruancyByUserId(Long userId, Integer from, Integer size) {

        userService.findUserById(userId);

        return repository.findAllByUserId(userId, PageRequest.of(from / size, size, Sort.by("startDate").descending()))
                .stream()
                .map(TruancyMapper::objectToReplyDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTruancy(Long truancyId) {

        repository.delete(findTruancyById(truancyId));
    }

    @Override
    public TruancyReplyDto updateTruancy(TruancyCreateDto dto, Long truancyId) {

        Truancy truancy = findTruancyById(truancyId);
        Truancy newTruancy = validation(truancy, dto);

        return TruancyMapper.objectToReplyDto(repository.save(newTruancy));
    }

    private Truancy validation(Truancy truancy, TruancyCreateDto dto) {

        if (dto.getReason() != null) {
            if (dto.getReason() > 0 && dto.getReason() < 4) {
                Reason reason = Reason.builder()
                        .id(dto.getReason())
                        .name(TruancyState.getNameById(dto.getReason()))
                        .build();

                truancy.setReason(reason);
            } else {
                throw new ConflictException("incorrect reason");
            }
        }
        if (dto.getStartDate() != null && !dto.getStartDate().isBlank() && !dto.getStartDate().isEmpty()) {
            truancy.setStartDate(DateTimeAdapter.stringToDate(dto.getStartDate()));
        }
        if (dto.getDuration() != null) {
            if (dto.getDuration() > 0) {
                truancy.setDuration(dto.getDuration());
            } else {
                throw new ConflictException("incorrect duration");
            }
        }
        if (dto.getDiscounted()) {
            truancy.setDiscounted(true);
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank() && !dto.getDescription().isEmpty()) {
            if (dto.getDescription().length() <= 1024) {
                truancy.setDescription(dto.getDescription());
            } else {
                throw new ConflictException("incorrect description");
            }
        }
        if (dto.getUser() != null) {
            truancy.setUser(userService.findUserById(dto.getUser()));
        }

        return truancy;
    }

    private Truancy findTruancyById(Long truancyId) {

        return repository.findById(truancyId).orElseThrow(() -> new ObjectNotFoundException("There is no truancy with this id"));
    }
}
