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
     * @param pageable параметры пагинации.
     * @return список сортов кофе
     */
    Page<CoffeeTypeDto> list(Pageable pageable);
}
