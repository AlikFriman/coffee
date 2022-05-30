/**
 * Заказ.
 */
import {DeliveryTypeEnum} from "./delivery-type.enum";
import {OrderStatusEnum} from "./order-status.enum";
import {OrderItem} from "./order-item.model";

export interface Order {

  /**
   * Уникальный идентификатор.
   */
  id: number;

  /**
   * Дата и время подтверждения заказа.
   */
  dateTime: Date;

  /**
   * Имя заказчика.
   */
  customerName: string;

  /**
   * Адрес доставки.
   */
  deliveryAddress: string;

  /**
   * Тип доставки.
   */
  deliveryType: DeliveryTypeEnum;

  /**
   * Полная стоимость заказа.
   */
  sum: number;

  /**
   * Статус заказа.
   */
  status: OrderStatusEnum;

  /**
   * Позиции заказа.
   */
  items: OrderItem[];
}
