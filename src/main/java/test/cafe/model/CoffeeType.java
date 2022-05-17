package test.cafe.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Сорт кофе.
 */
@Data
@Entity
@Table(name = "coffee_type")
public class CoffeeType {

    /**
     * Уникальный идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Наименование на русском.
     */
    @Column(name = "name_ru", nullable = false)
    private String nameRu;

    /**
     * Наименование на английском.
     */
    @Column(name = "name_eng", nullable = false)
    private String nameEng;

    /**
     * Цена.
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * Наличие сорта кофе.
     */
    @Column(name = "available", nullable = false)
    private boolean available;
}
