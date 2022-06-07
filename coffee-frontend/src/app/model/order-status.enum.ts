/**
 * Статус заказа.
 */
export enum OrderStatusEnum {

  /**
   * Создан.
   */
  CREATED = 'CREATED',

  /**
   * Подтвержден.
   */
  CONFIRMED = 'CONFIRMED',

  /**
   * Доставлен.
   */
  DELIVERED = 'DELIVERED',

  /**
   * Отменен.
   */
  CANCELED = 'CANCELED'
}
