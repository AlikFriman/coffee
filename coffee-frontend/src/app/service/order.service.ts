import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from "../model/order.model";
import {ContentPage} from "../model/content-page.model";
import {OrderItem} from "../model/order-item.model";
import {GET_URL_PART, ITEMS_URL_PART, LIST_URL_PART, ORDERS_LIST_URL, ORDERS_URL} from "../constant/api-constants";
import {buildPageParams} from "../util/http-utlis";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient) {
  }

  public getOrdersList(page: number, size: number): Observable<ContentPage<Order>> | null {
    const url = ORDERS_LIST_URL
    const params = buildPageParams(page, size);

    return this.httpClient.post<ContentPage<Order>>(url, null, {params: params});
  }

  public getOrder(id: number): Observable<Order> | null {
    const url = `${ORDERS_URL}/${id}${GET_URL_PART}`;
    return this.httpClient.get<Order>(url);
  }

  public createOrder(order: Order): Observable<Order> | null {
    return null;
  }

  public editOrder(id: number, order: Order): Observable<Order> | null {
    return null;
  }

  public confirmOrder(id: number): Observable<Order> | null {
    return null;
  }

  public cancelOrder(id: number): Observable<Order> | null {
    return null;
  }

  public addItem(orderId: number, orderItem: OrderItem): Observable<OrderItem> | null {
    return null;
  }

  public deleteItem(orderId: number, itemId: number): Observable<void> | null {
    return null;
  }

  public editItem(orderId: number, itemId: number, orderItem: OrderItem): Observable<OrderItem> | null {
    return null;
  }

  public listOrderItems(id: number, page: number, size: number): Observable<ContentPage<OrderItem>> | null {
    const url = `${ORDERS_URL}/${id}${ITEMS_URL_PART}${LIST_URL_PART}`
    const params = buildPageParams(page, size);

    return this.httpClient.post<ContentPage<OrderItem>>(url, null, {params: params});
  }

}
