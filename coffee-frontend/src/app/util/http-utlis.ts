import {HttpParams} from "@angular/common/http";

export function buildPageParams(page: number, size: number): HttpParams {
  let params = new HttpParams();
  if (page) {
    params = params.set('page', page);
  }
  if (size) {
    params = params.set('size', size);
  }
  return params;
}
