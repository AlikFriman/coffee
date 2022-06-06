/**
 * Статус заказа.
 */
export enum OrderStatusEnum {

  /**
   * Создан.
   */
  CREATED = 'Создан',

  /**
   * Подтвержден.
   */
  CONFIRMED = 'Подтвержден',

  /**
   * Доставлен.
   */
  DELIVERED = 'Доставлен',

  /**
   * Отменен.
   */
  CANCELED = 'Отменен'
}
