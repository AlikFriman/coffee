import {Component, OnInit, ViewChild} from '@angular/core';
import {Order} from "../../model/order.model";
import {OrderService} from "../../service/order.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {OrderItem} from "../../model/order-item.model";
import {CoffeeType} from "../../model/coffee-type.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {CoffeeTypeService} from "../../service/coffee-type.service";
import {ContentPage} from "../../model/content-page.model";
import {OrderItemDetailComponent, OrderItemEditDialogData} from "../order-item-detail/order-item-detail.component";
import {OrderStatusEnum} from "../../model/order-status.enum";
import {DeliveryTypeEnum} from "../../model/delivery-type.enum";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  order: Order = <Order>{};

  page: number = 0;
  size: number = 20;
  total: number = 0;
  pageSizeOptions: number[] = [5, 10, 25, 50];

  orderStatuses = OrderStatusEnum;
  deliveryTypes = DeliveryTypeEnum;

  dataSource: MatTableDataSource<OrderItem> = new MatTableDataSource();

  coffeeTypes: CoffeeType[] = [];
  deliveryTypeOptions: DeliveryTypeEnum[] = [DeliveryTypeEnum.DELIVERY, DeliveryTypeEnum.PICKUP];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  displayedColumns: string[] = ['index', 'coffeeType', 'price', 'count', 'sum', 'delete', 'edit']

  constructor(private orderService: OrderService,
              private coffeeTypeService: CoffeeTypeService,
              private activatedRoute: ActivatedRoute,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit(): void {
    const orderId = this.activatedRoute.snapshot.params['id'];
    this.orderService.getOrder(orderId)?.subscribe((order: Order) => {
      this.order = order;
      this.loadOrderItems();
      this.loadCoffeeTypes();
    }, error => {
      console.error(error)
    })
  }

  save() {
    this.orderService.editOrder(this.order.id, this.order)?.subscribe((order: Order) => this.order = order);
  }

  /*
  Подтверждение заказа.
 */
  confirm() {
    this.orderService.confirmOrder(this.order.id)?.subscribe((order: Order) => this.order = order);
  }

  /*
  Отмена заказа.
   */
  cancel() {
    this.orderService.cancelOrder(this.order.id)?.subscribe((order: Order) => this.order = order);
  }

  /*
  Добавение позиции заказа.
   */
  addItem() {
    this.openDialog(null, this.dataSource.data.length)
  }

  /*
  Именение позиции заказа.
  */
  editItem(itemId: number, index: number) {
    this.openDialog(itemId, index);
  }

  /*
  Удаление позиции заказа.
  */
  deleteItem(itemId: number) {
    this.orderService.deleteItem(this.order.id, itemId)?.subscribe(() => this.loadOrderItems());
  }

  openDialog(itemId: number | null, index: number) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.autoFocus = true;
    dialogConfig.hasBackdrop = false;
    dialogConfig.data = <OrderItemEditDialogData>{
      orderId: this.order.id,
      itemId: itemId,
      index: index,
      coffeeTypes: this.coffeeTypes
    }

    const dialogRef = this.dialog.open(OrderItemDetailComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.info('Dialog result: ', result);
      if (result) {
        if (itemId) {
          this.orderService.editItem(this.order.id, itemId, result)?.subscribe(() => this.loadOrderItems());
        } else {
          this.orderService.addItem(this.order.id, result)?.subscribe(() => this.loadOrderItems());
        }
      }
    })
  }

  loadOrderItems() {
    this.orderService.getOrderItems(this.order.id, this.page, this.size)
      ?.subscribe((orderItemContentPage: ContentPage<OrderItem>) => {
        this.dataSource.data = orderItemContentPage.content;
        this.total = orderItemContentPage.totalElements;
      })
  }

  loadCoffeeTypes() {
    this.coffeeTypeService.getCoffeeTypeList()
      ?.subscribe((coffeeTypes: CoffeeType[]) => {
        this.coffeeTypes = coffeeTypes;
      })
  }

  getCoffeeTypeById(id: number): CoffeeType | undefined {
    return this.coffeeTypes.find(value => value.id == id);
  }

  pageChanged(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.loadOrderItems();
  }

  back() {
    this.router.navigateByUrl('');
  }
}
