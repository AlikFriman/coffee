import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from "../model/order.model";
import {ContentPage} from "../model/content-page.model";
import {OrderItem} from "../model/order-item.model";
import {
  CANCEL_URL_PART,
  CONFIRM_URL_PART,
  CREATE_URL_PART,
  EDIT_URL_PART,
  GET_URL_PART,
  ITEMS_URL_PART,
  LIST_URL_PART,
  ORDERS_LIST_URL,
  ORDERS_URL
} from "../constant/api-constants";
import {buildPageParams} from "../util/http-utlis";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Получение страницы заказов.
   *
   * @param page - номер страницы
   * @param size - размер страницы
   */
  public getOrders(page: number, size: number): Observable<ContentPage<Order>> | null {
    const url = ORDERS_LIST_URL
    const params = buildPageParams(page, size);
    return this.httpClient.post<ContentPage<Order>>(url, null, {params: params});
  }

  /**
   * Получение заказа.
   *
   * @param id - идентификатор заказа.
   */
  public getOrder(id: number): Observable<Order> | null {
    const url = `${ORDERS_URL}/${id}${GET_URL_PART}`; // Интерполяция
    return this.httpClient.get<Order>(url);
  }

  /**
   * Получение позиции заказа
   *
   * @param orderId - идентификатор заказа.
   * @param itemId - идентификатор позиции заказа
   */
  public getOrderItem(orderId: number, itemId: number): Observable<OrderItem> | null {
    const url = `${ORDERS_URL}/${orderId}${ITEMS_URL_PART}/${itemId}`;
    return this.httpClient.get<OrderItem>(url);
  }

  /**
   * Создание заказа.
   *
   * @param order - данные для создания нового заказа.
   */
  public createOrder(order: Order): Observable<Order> | null {
    const url = `${ORDERS_URL}${CREATE_URL_PART}`;
    return this.httpClient.post<Order>(url, order);
  }

  /**
   * Изменение заказа.
   *
   * @param id - идентификатор заказа.
   * @param order - данные изменяемого заказа.
   */
  public editOrder(id: number, order: Order): Observable<Order> | null {
    const url = `${ORDERS_URL}/${id}${EDIT_URL_PART}`;
    return this.httpClient.put<Order>(url, order);
  }

  /**
   * Подтверждение заказа.
   *
   * @param id - идентификатор заказа.
   */
  public confirmOrder(id: number): Observable<Order> | null {
    const url = `${ORDERS_URL}/${id}${CONFIRM_URL_PART}`;
    return this.httpClient.put<Order>(url, null);
  }

  /**
   * Отмена заказа.
   *
   * @param id - идентификатор заказа.
   */
  public cancelOrder(id: number): Observable<Order> | null {
    const url = `${ORDERS_URL}/${id}${CANCEL_URL_PART}`;
    return this.httpClient.put<Order>(url, null);
  }

  /**
   * Добавление позиции заказа.
   *
   * @param orderId - идентификатор заказа.
   * @param orderItem - данные позиций заказа.
   */
  public addItem(orderId: number, orderItem: OrderItem): Observable<OrderItem> | null {
    const url = `${ORDERS_URL}/${orderId}${ITEMS_URL_PART}`;
    return this.httpClient.post<OrderItem>(url, orderItem);
  }

  /**
   * Уаление позиции заказа.
   *
   * @param orderId - идентификатор заказа.
   * @param itemId - идентификатор позиции заказа
   */
  public deleteItem(orderId: number, itemId: number): Observable<void> | null {
    const url = `${ORDERS_URL}/${orderId}${ITEMS_URL_PART}/${itemId}`;
    return this.httpClient.delete<void>(url);
  }

  /**
   * Изменеие позиции заказа.
   *
   * @param orderId - идентификатор заказа.
   * @param itemId - идентификатор позиции заказа
   * @param orderItem - данные позиций заказа.
   */
  public editItem(orderId: number, itemId: number, orderItem: OrderItem): Observable<OrderItem> | null {
    const url = `${ORDERS_URL}/${orderId}${ITEMS_URL_PART}/${itemId}`;
    return this.httpClient.put<OrderItem>(url, orderItem);
  }

  /**
   * Получение страницы позиций заказа
   *
   * @param id - идентификатор заказа
   * @param page - номер страницы
   * @param size - размер страницы
   */
  public getOrderItems(id: number, page: number, size: number): Observable<ContentPage<OrderItem>> | null {
    const url = `${ORDERS_URL}/${id}${ITEMS_URL_PART}${LIST_URL_PART}`;
    const params = buildPageParams(page, size);
    return this.httpClient.post<ContentPage<OrderItem>>(url, null, {params: params});
  }
}
