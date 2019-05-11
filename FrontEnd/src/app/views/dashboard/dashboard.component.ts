import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {ItemService} from "../../services/item.service";
import {PlaceOrderServiceService} from "../../services/place-order-service.service";
import {OrdersDetail} from "../../dtos/orders-detail";
import {Items} from "../../dtos/items";
import {Orders} from "../../dtos/orders";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  totalCustomers: number = 0;
  totalItems: number=0;
  totalOrders: number=0;

  listOrder: Array<Orders> = [];

  constructor(
    private placeOrderService:PlaceOrderServiceService,
    private customerService: CustomerService,
    private itemService: ItemService) { }

  ngOnInit() {
    this.getTotalCustomers();
    this.getTotalItems();
    this.getTotalOrders();
    this.loadOrder();
  }

  getTotalCustomers(): void{
    this.customerService.getTotalCustomer().subscribe(
      (count) =>{
        this.totalCustomers = count;
      }
    )
  }


  getTotalItems(): void {
    this.itemService.getTotalItems().subscribe(
      (count) => {
        this.totalItems = count;
      }
    )
  }

  getTotalOrders():void{
    this.placeOrderService.getTotalOrders().subscribe(
      (result)=>{
        this.totalOrders=result;
      }
    )
  }

  loadOrder():void{
    this.placeOrderService.getAllOrders().subscribe(
      value => {
        this.listOrder=value;
      }
    );
  }

}
