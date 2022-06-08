import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {OrderItemEditDialogData} from "../order-item-detail/order-item-detail.component";
import {Order} from "../../model/order.model";
import {DeliveryTypeEnum} from "../../model/delivery-type.enum";
import {OrderStatusEnum} from "../../model/order-status.enum";

export interface OrderCreateDialogData {
  index: number;
}

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {

  order: Order = <Order>{}

  orderStatuses = OrderStatusEnum;
  deliveryTypes = DeliveryTypeEnum;
  deliveryTypeOptions: DeliveryTypeEnum[] = [DeliveryTypeEnum.DELIVERY, DeliveryTypeEnum.PICKUP];

  constructor(public dialogRef: MatDialogRef<OrderCreateComponent>,
              @Inject(MAT_DIALOG_DATA) public data: OrderItemEditDialogData) {
  }

  ngOnInit(): void {
    this.createOrder();
  }

  createOrder() {
    this.order = <Order>{
      deliveryType: DeliveryTypeEnum.PICKUP
    };
  }

  cancel() {
    this.dialogRef.close();
  }

  save() {
    this.dialogRef.close(this.order);
  }
}
