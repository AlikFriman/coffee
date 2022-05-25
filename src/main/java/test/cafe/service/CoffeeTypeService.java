package test.cafe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * @param pageable
     */
    Page<CoffeeTypeDto> list(Pageable pageable);
}
