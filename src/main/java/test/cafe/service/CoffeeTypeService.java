package test.cafe.service;

import test.cafe.dto.CoffeeTypeDto;
import java.util.List;

/**
 * Сервис по работе с объектом Тип кофе.
 */

public interface CoffeeTypeService {

    /**
     * Получение списка сортов кофе.
     *
     * @return Список сортов кофе
     */
    List<CoffeeTypeDto> list();
}
