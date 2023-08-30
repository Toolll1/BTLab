package ru.BTLab.test.models;

import ru.BTLab.test.exceptions.BadRequestException;

public enum TruancyState {

    VOCATION(1, "Отпуск"),
    MEDICAL(2, "Больничный"),
    TRUANCY(3, "Прогул");

    private final int code;
    private final String name;

    TruancyState(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameById(Long id) {

        if (id == 1L) {
            return TruancyState.VOCATION.name;
        } else if (id == 2L) {
            return TruancyState.MEDICAL.name;
        } else if (id == 3L) {
            return TruancyState.TRUANCY.name;
        }
        throw new BadRequestException("нет такого статуса");
    }
}
