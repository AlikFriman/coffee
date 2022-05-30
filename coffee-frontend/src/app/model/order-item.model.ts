/**
 * Позиция заказа.
 */
export interface OrderItem {
  /**
   * Уникальный идентификатор.
   */
  id: number;

  /**
   * Идентификатор сорта кофе.
   */
  coffeeTypeId: number;

  /**
   * Количество.
   */
  count: number;

  /**
   * Идентификатор заказа.
   */
  orderId: number;

  /**
   * Стоимость
   */
  sum: number;
}
