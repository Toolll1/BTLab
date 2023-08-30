package ru.BTLab.test.services;

import ru.BTLab.test.dto.TruancyCreateDto;
import ru.BTLab.test.dto.TruancyReplyDto;

import java.util.List;

public interface TruancyService {
    TruancyReplyDto createTruancy(TruancyCreateDto dto);

    TruancyReplyDto getTruancyById(Long truancyId);

    List<TruancyReplyDto> getTruancy(Integer from, Integer size);

    List<TruancyReplyDto> getTruancyByUserId(Long userId, Integer from, Integer size);

    void deleteTruancy(Long truancyId);

    TruancyReplyDto updateTruancy(TruancyCreateDto dto, Long truancyId);
}
