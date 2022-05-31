import {Component, OnInit} from '@angular/core';
import {CoffeeTypeService} from "./service/coffee-type.service";
import {OrderService} from "./service/order.service";
import {Order} from "./model/order.model";
import {OrderItem} from "./model/order-item.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private coffeeTypeService: CoffeeTypeService,
              private orderService: OrderService) {
  }

  title = 'coffee-frontend';

  ngOnInit(): void {
    // Coffee types list
    this.coffeeTypeService.getCoffeeTypeList(0, 20)?.subscribe(result => {
      console.info('Get coffee type list: ', result)
    })

    // Orders list
    this.orderService.getOrders(0, 20)?.subscribe(result => {
      console.info('Orders list: ', result)
    })

    // Orders list
    this.orderService.getOrderItems(7, 0, 20)?.subscribe(result => {
      console.info('Order items: ', result)
    })

    // Order
    this.orderService.getOrder(7)?.subscribe(result => {
      console.info('Order #7: ', result)
    })

    // Create order
    let order = {
      customerName: "Тема",
      deliveryAddress: "Улица Потемкина",
      deliveryType: "DELIVERY"
    }
    // this.orderService.createOrder(<Order>order)?.subscribe(result => {
    //   console.info('New order: ', result)
    // })

    // Edit order
    order = {
      customerName: "Артемка",
      deliveryAddress: "Улица Потемкина",
      deliveryType: "PICKUP"
    }
    this.orderService.editOrder(14, <Order>order)?.subscribe(results => {
      console.info('Edit order; ', results)
    })

    // Confirm order реализован, но повторно выдает 404, так как стал CONFIRMED
    // this.orderService.confirmOrder(12)?.subscribe(result => {
    //   console.info('Order #12: ', result)
    // })

    // Cancel order реализован.
    // this.orderService.cancelOrder(11)?.subscribe(result => {
    //   console.info('Order #11: CANCEL', result)
    // })

    // Add item
    let orderItem = {
      coffeeTypeId: 2,
      count: 2
    }
    this.orderService.addItem(7, <OrderItem>orderItem)?.subscribe(result => {
      console.info('Order #7 add item X3', result)
    })

    //Delete item удаляет наглухо!
    // this.orderService.deleteItem(7,1)?.subscribe(result => {
    //   console.info('Order #7 delete itemId #5', result)
    // })

    // Edit item
    orderItem = {
      coffeeTypeId: 3,
      count: 3
    }
    this.orderService.editItem(7, 9, <OrderItem>orderItem)?.subscribe(result => {
      console.info('Order #7, edit itemId 9, orderItem X3', result)
    })
  }
}


// order list
// order list item
// order detail
// order item list
// order item list item
// order item detail
// coffee type list
// coffee type list item

// TODO: изучить Observable
