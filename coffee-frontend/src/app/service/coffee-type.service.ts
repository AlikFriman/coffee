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

  public getCoffeeTypeList(page: number, size: number): Observable<ContentPage<CoffeeType>> | null {
    const url = COFFEE_TYPES_LIST_URL;
    const params = buildPageParams(page, size);

    return this.httpClient.post<ContentPage<CoffeeType>>(url, null, {params: params});
  }
}

