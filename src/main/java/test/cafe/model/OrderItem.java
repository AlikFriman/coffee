package test.cafe.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Позиция заказа.
 */
@Entity
@Data
@Table(name = "order_item")
public class OrderItem {
    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Сорт кофе.
     */
    @ManyToOne()
    @JoinColumn(name = "coffee_type_id", nullable = false)
    private CoffeeType coffeeType;

    /**
     * Число чашек.
     */
    @Column(name = "cup_counter", nullable = false)
    private Integer cupCounter;

    /**
     * Заказ.
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // TODO: 12.05.2022 Добавить поле "стоимость" ПРОВЕРИТЬ
    @Column(name = "full_price")
    private BigDecimal fullPrice;
}
