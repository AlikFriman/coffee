package test.cafe.service;

import test.cafe.model.CoffeeType;

/**
 * Внутренний сервис по работе с объектом Тип кофе.
 */

public interface CoffeeTypeServiceInternal {

    /**
     * Получение сорта кофе.
     *
     * @return Сорт кофе
     */
    CoffeeType getById(Integer id);
}
