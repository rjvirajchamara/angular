import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ItemService} from "../../services/item.service";
import {Customer} from "../../dtos/customer";
import {Items} from "../../dtos/items";
import {NgForm} from "@angular/forms";
import {PlaceOrderServiceService} from "../../services/place-order-service.service";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent implements OnInit {

  items: Array<Items> = [];
  selectedItems: Items = new Items;
  tempItems: Items = null;
  manuallySelected: boolean = false;
  ItemID=0;
  @ViewChild("frmItems") frmItems: NgForm;

  constructor(private itemService:ItemService, private elem: ElementRef) { }

  ngOnInit() {
    this.loadAllItems();
    this.loadOrder();
  }

  loadAllItems(): void {
    this.itemService.getAllItems().subscribe(
      (result) => {
        this.items = result;
        console.log(this.items);
      }
    )
  }

  selectItems(item: Items): void {
    this.itemService.searchItem(item.code).subscribe(
      value => {
        if (value){
          this.selectedItems=value;
        }
      }
    );

  }

  clear(): void {
    let index = this.items.indexOf(this.selectedItems);

    if (index !== -1) {
      this.items[index] = this.tempItems;
      this.tempItems = null;
    }
    this.selectedItems = new Items;
    this.manuallySelected = false;
  }

  saveItems():void{
    this.selectedItems.code=this.elem.nativeElement.querySelector('#txtId').value;
    this.itemService.saveItems(this.selectedItems).subscribe(
      (result)=> {
        if (result) {
          //console.log(this.selectedItems)
          alert("Items has been saved successfully");
          this.loadAllItems();
          this.loadOrder();
        } else {
          alert("Failed to save the Items");
        }
      }
    )

  }

  deleteItems(code):void {
    if (confirm("Are you sure you want to delete this Item?")) {

      this.itemService.deleteItem(code).subscribe(
        (result) => {
          if (result) {
            alert("Items has been Deleted successfully");
          } else {
            alert("Failed to deleted Items");
          }
          this.loadAllItems();
        }
      )
    }
  }
  update(id):void{
      this.itemService.saveItems(this.selectedItems).subscribe(
        value => {
          if (value) {
            alert("Items has been Update successfully");
            this.loadAllItems();
          } else {
            alert("Failed to update the Items");
          }
        }
      )
  }
  loadOrder():void{
    this.itemService.getTotalItems().subscribe(
      value => {
        this.ItemID=value+1;
      }
    );
  }


}

