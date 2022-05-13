package test.cafe.model;

/**
 * Статус заказа.
 */
public enum OrderStatus {

    /**
     * Создан.
     */
    CREATED,

    /**
     * Подтвержден.
     */
    CONFIRMED,

    /**
     * Доставлен.
     */
    DELIVERED,

    /**
     * Отменен.
     */
    CANCELED
}
