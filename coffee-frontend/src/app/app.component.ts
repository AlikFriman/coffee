import {Component, OnInit} from '@angular/core';
import {CoffeeTypeService} from "./service/coffee-type.service";
import {OrderService} from "./service/order.service";

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
    this.orderService.getOrdersList(0, 20)?.subscribe(result => {
      console.info('Orders list: ', result)
    })

    // Order
    this.orderService.getOrder(7)?.subscribe(result => {
      console.info('Order #7: ', result)
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
