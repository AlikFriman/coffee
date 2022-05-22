package test.cafe.model;


import lombok.*;
import test.cafe.model.type.DeliveryType;
import test.cafe.model.type.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Заказ.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "\"order\"")
public class Order {

    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * Статус заказа.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
// TODO: 12.05.2022 Разобраться со временем
    /**
     * Дата и время подтверждения заказа.
     */
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    /**
     * Имя заказчика.
     */
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    /**
     * Тип доставки.
     */
    @Column(name = "delivery_type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    /**
     * Адрес доставки.
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;
    // TODO: 13.05.2022 Ленивая загрузка? Hibernate lazy fetch
    /**
     * Позиции заказа.
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude

    private List<OrderItem> items = new ArrayList<>();
    // TODO: Сделать nullable
    /**
     * Полная стоимость заказа.
     */
    @Column(name = "sum")
    private BigDecimal sum;
}
