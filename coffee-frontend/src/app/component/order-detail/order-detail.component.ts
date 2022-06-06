import {Component, OnInit, ViewChild} from '@angular/core';
import {Order} from "../../model/order.model";
import {OrderService} from "../../service/order.service";
import {ActivatedRoute} from "@angular/router";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatTableDataSource} from "@angular/material/table";
import {OrderItem} from "../../model/order-item.model";
import {CoffeeType} from "../../model/coffee-type.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {CoffeeTypeService} from "../../service/coffee-type.service";
import {ContentPage} from "../../model/content-page.model";
import {OrderItemDetailComponent, OrderItemEditDialogData} from "../order-item-detail/order-item-detail.component";

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

  dataSource: MatTableDataSource<OrderItem> = new MatTableDataSource();

  coffeeTypes: CoffeeType[] = [];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  displayedColumns: string[] = ['coffeeType', 'price', 'count', 'sum', 'delete', 'edit']

  constructor(private orderService: OrderService,
              private coffeeTypeService: CoffeeTypeService,
              private activatedRoute: ActivatedRoute,
              private dialog: MatDialog) {
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

  pageChanged(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.loadOrderItems();
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

  deleteItem(itemId: number) {
    this.orderService.deleteItem(this.order.id, itemId)?.subscribe(() => this.loadOrderItems());
  }

  editItem(itemId: number) {
    this.openDialog(itemId);
  }



  openDialog(itemId: number) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.autoFocus = true;
    dialogConfig.hasBackdrop = false;
    dialogConfig.data = <OrderItemEditDialogData>{
      order: this.order,
      itemId: itemId
    }

    const dialogRef = this.dialog.open(OrderItemDetailComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.info('Dialog result: ', result);

    })
  }
}
