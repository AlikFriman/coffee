package test.cafe.dto;

import lombok.Data;
import test.cafe.dto.type.DeliveryTypeDto;
import test.cafe.dto.type.OrderStatusDto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;
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
    private Date dateTime;

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
