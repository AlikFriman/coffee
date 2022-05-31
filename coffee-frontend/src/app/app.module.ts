import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from "@angular/forms";

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {HttpClientModule} from "@angular/common/http";
import {OrderListComponent} from "./component/order-list/order-list.component";
import {OrderListItemComponent} from './component/order-list-item/order-list-item.component';
import { OrderDetailComponent } from './component/order-detail/order-detail.component';
import { OrderItemListComponent } from './component/order-item-list/order-item-list.component';
import { OrderItemListItemComponent } from './component/order-item-list-item/order-item-list-item.component';
import { OrderItemDetailComponent } from './component/order-item-detail/order-item-detail.component';
import { CoffeeTypeListComponent } from './component/coffee-type-list/coffee-type-list.component';
import { CoffeeTypeListItemComponent } from './component/coffee-type-list-item/coffee-type-list-item.component';


@NgModule({
  declarations: [
    AppComponent,
    OrderListComponent,
    OrderListItemComponent,
    OrderDetailComponent,
    OrderItemListComponent,
    OrderItemListItemComponent,
    OrderItemDetailComponent,
    CoffeeTypeListComponent,
    CoffeeTypeListItemComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
