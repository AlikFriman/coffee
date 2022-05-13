package test.cafe.dto;

import lombok.Data;
import test.cafe.model.DeliveryType;
import test.cafe.model.OrderStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Заказ.
 */

@Data
public class OrderDto {

    /**
     * Уникальный идентификатор.
     */
    private Integer id;

    /**
     * Дата и время заказа.
     */
    private Date orderDateTime;

    /**
     * Имя заказчика.
     */
    private String customerName;

    /**
     * Адрес доставки.
     */
    private String deliveryAddress;

    /**
     * Тип доставки.
     */
    //TODO: 12.05.2022 Проверить на правильность. ПРОВЕРИТЬ
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    /**
     * Полная стоимость заказа.
     */
    private BigDecimal fullOrderPrice;

    /**
     * Статус заказа.
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
