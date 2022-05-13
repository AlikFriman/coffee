package test.cafe.dto;

import lombok.Data;


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
     * Сорт кофе.
     */
     // TODO: 12.05.2022 Тут не совсем понятно, подправил похоже не правильно.
    private CoffeeTypeDto coffeeType;

    /**
     * Число чашек.
     */
    private Integer cupCounter;

    /**
     * Заказ.
     */
    private OrderDto order;
}
