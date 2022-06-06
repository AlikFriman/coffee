import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {OrderService} from "../../service/order.service";
import {Order} from "../../model/order.model";
import {ContentPage} from "../../model/content-page.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit, AfterViewInit {

  page: number = 0;
  size: number = 20;
  total: number = 0;
  pageSizeOptions: number[] = [5, 10, 25, 50];

  displayedColumns: string[] = ['id', 'customerName', 'deliveryType', 'deliveryAddress', 'sum', 'dateTime', 'status'];
  dataSource: MatTableDataSource<Order> = new MatTableDataSource();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private orderService: OrderService) {
  }

  ngAfterViewInit() {
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.orderService.getOrders(this.page, this.size)?.subscribe((orderContentPage: ContentPage<Order>) => {
      console.info('Orders: ', orderContentPage);
      this.dataSource.data = orderContentPage.content;
      this.total = orderContentPage.totalElements;
    })
  }

  pageChanged(event: PageEvent) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.loadData();
  }
}
