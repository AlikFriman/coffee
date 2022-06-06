import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Order} from "../../model/order.model";
import {OrderItem} from "../../model/order-item.model";
import {OrderService} from "../../service/order.service";
import {CoffeeType} from "../../model/coffee-type.model";
import {ActivatedRoute} from "@angular/router";

export interface OrderItemEditDialogData {
  order: Order;
  itemId: number;
}

@Component({
  selector: 'app-order-item-detail',
  templateUrl: './order-item-detail.component.html',
  styleUrls: ['./order-item-detail.component.css']
})
export class OrderItemDetailComponent implements OnInit {
  order: Order = <Order>{};
  orderItem: OrderItem = <OrderItem>{};


  constructor(public dialogRef: MatDialogRef<OrderItemDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: OrderItemEditDialogData,
              private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.dialogRef.afterOpened().subscribe(() => console.info('Data: ', this.data));
  }

  loadOrderItem(orderId: number, itemId: number) {
    this.orderService.getOrderItem(orderId, itemId)
      ?.subscribe((orderItem: OrderItem) => {
        this.orderItem = orderItem;
      })
  }

  close(result: any) {
    this.dialogRef.close(result)
  }

}
