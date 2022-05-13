package test.cafe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.cafe.model.type.DeliveryType;
import test.cafe.model.type.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Заказ.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
public class Order {

    /**
     * Позиция заказа.
     */
    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems = new ArrayList<>();
    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * Дата и время заказа.
     */
    // TODO: 12.05.2022 Разобраться со временем
    @Column(name = "order_date_time")
    private Date orderDateTime;
    /**
     * Имя заказчика.
     */
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    /**
     * Адрес доставки.
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;
    /**
     * Тип доставки.
     */
    @Column(name = "delivery_type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    /**
     * Полная стоимость заказа.
     */
    @Column(name = "full_order_price")
    private BigDecimal fullOrderPrice;
    /**
     * Статус заказа.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
