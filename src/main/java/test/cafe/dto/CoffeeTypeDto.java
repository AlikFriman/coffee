package test.cafe.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Сорт кофе.
 */
@Data
public class CoffeeTypeDto {

    /**
     * Уникальный идентификатор.
     */
    private Integer id;

    /**
     * Наименование на русском.
     */
    private String nameRu;

    /**
     * Наименование на английском.
     */
    private String nameEng;

    /**
     * Цена.
     */
    private BigDecimal price;

    /**
     * Признак наличия сорта кофе.
     */
    private boolean available;
}
