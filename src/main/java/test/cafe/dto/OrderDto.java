package test.cafe.dto;

import lombok.Data;
import test.cafe.dto.type.DeliveryTypeDto;
import test.cafe.dto.type.OrderStatusDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
     * Дата и время подтверждения заказа.
     */
    private LocalDateTime dateTime;

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
    private DeliveryTypeDto deliveryType;

    /**
     * Полная стоимость заказа.
     */
    private BigDecimal sum;

    /**
     * Статус заказа.
     */
    private OrderStatusDto status;

    /**
     * Позиции заказа.
     */
    private List<OrderItemDto> items;
}
