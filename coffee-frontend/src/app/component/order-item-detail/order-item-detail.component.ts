import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {OrderItem} from "../../model/order-item.model";
import {OrderService} from "../../service/order.service";
import {CoffeeType} from "../../model/coffee-type.model";

export interface OrderItemEditDialogData {
  orderId: number;
  itemId: number;
  index: number;
  coffeeTypes: CoffeeType[];
}

@Component({
  selector: 'app-order-item-detail',
  templateUrl: './order-item-detail.component.html',
  styleUrls: ['./order-item-detail.component.css']
})
export class OrderItemDetailComponent implements OnInit {

  orderItem: OrderItem = <OrderItem>{};

  constructor(public dialogRef: MatDialogRef<OrderItemDetailComponent>,
              @Inject(MAT_DIALOG_DATA) public data: OrderItemEditDialogData,
              private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.dialogRef.afterOpened().subscribe(() => {
      if (this.data.itemId) {
        this.loadOrderItem(this.data.orderId, this.data.itemId)
      } else {
        this.createItem();
      }
    });
  }

  createItem() {
    this.orderItem = <OrderItem>{
      count: 1,
      orderId: this.data.orderId
    }
  }

  loadOrderItem(orderId: number, itemId: number) {
    this.orderService.getOrderItem(orderId, itemId)
      ?.subscribe((orderItem: OrderItem) => {
        this.orderItem = orderItem;
      })
  }

  cancel() {
    this.dialogRef.close()
  }

  save() {
    this.dialogRef.close(this.orderItem)
  }

  get index(): number {
    return (this.data.index as number) + 1;
  }
}
