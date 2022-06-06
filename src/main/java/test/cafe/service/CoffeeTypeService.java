package test.cafe.service;

import org.springframework.data.domain.Page;
import test.cafe.dto.CoffeeTypeDto;

import java.util.List;

/**
 * Сервис по работе с объектом Тип кофе.
 */
public interface CoffeeTypeService {

    /**
     * Получение списка сортов кофе.
     *
     * @return список сортов кофе
     */
    List<CoffeeTypeDto> list();
}
