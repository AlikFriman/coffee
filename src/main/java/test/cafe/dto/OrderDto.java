package test.cafe.dto;

import lombok.Data;
import test.cafe.dto.type.DeliveryTypeDto;
import test.cafe.dto.type.OrderStatusDto;

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
    @Enumerated(EnumType.STRING)
    private DeliveryTypeDto deliveryType;

    /**
     * Полная стоимость заказа.
     */
    private BigDecimal fullOrderPrice;

    /**
     * Статус заказа.
     */
    @Enumerated(EnumType.STRING)
    private OrderStatusDto status;
}
