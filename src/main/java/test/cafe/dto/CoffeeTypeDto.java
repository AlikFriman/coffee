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
    private String gradeNameRu;

    /**
     * Наименование на английском.
     */
    private String gradeNameEng;

    /**
     * Цена.
     */
    private BigDecimal price;

    /**
     * Наличие сорта кофе.
     */
    private boolean disabled;
}
