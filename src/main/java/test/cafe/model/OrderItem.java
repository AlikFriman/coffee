package test.cafe.model;

import lombok.Data;
import lombok.ToString;

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
     * Количество.
     */
    @Column(name = "count", nullable = false)
    private Integer count;

    /**
     * Ссылка на сущность "Заказ".
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @ToString.Exclude // Для предотвращения бесконечного цикла с Order при формировании строки
    private Order order;

    /**
     * Стоимость.
     */
    @Column(name = "sum")
    private BigDecimal sum;
}
