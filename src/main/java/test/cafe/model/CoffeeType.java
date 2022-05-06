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
    @GeneratedValue
    private Integer id;

    /**
     * Наименование на русском.
     */
    @Column(name = "grade_name_ru")
    private String gradeNameRu;

    /**
     * Наименование на английском.
     */
    private String gradeNameEng;

    /**
     * Цена.
     */
    private BigDecimal price;

    /**
     * Наличие сорта кофе.
     */
    private boolean disabled;
}
