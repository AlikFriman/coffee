import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {CoffeeType} from "../model/coffee-type.model";
import {ContentPage} from "../model/content-page.model";
import {COFFEE_TYPES_LIST_URL} from "../constant/api-constants";
import {buildPageParams} from "../util/http-utlis";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class CoffeeTypeService {

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Получение страницы сортов кофе.
   */
  public getCoffeeTypeList(): Observable<CoffeeType[]> | null {
    const url = COFFEE_TYPES_LIST_URL;
    return this.httpClient.post<CoffeeType[]>(url, null);
  }
}

