package test.cafe.dto;

import lombok.Data;

import java.math.BigDecimal;


/**
 * Позиция заказа.
 */
@Data
public class OrderItemDto {
    /**
     * Уникальный идентификатор.
     */
    private Integer id;

    /**
     * Идентификатор сорта кофе.
     */
    private Integer coffeeTypeId;

    /**
     * Количество.
     */
    private Integer count;

    /**
     * Идентификатор заказа.
     */
    private Integer orderId;

    /**
     * Стоимость
     */
    private BigDecimal sum;
}
