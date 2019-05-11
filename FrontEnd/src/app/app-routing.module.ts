import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./views/dashboard/dashboard.component";
import {ManageCustomersComponent} from "./views/manage-customers/manage-customers.component";
import {ManageCustomerComponentCandeactivateGuard} from "./guards/manage-customer-component-candeactivate.guard";
import {MainComponent} from "./views/main/main.component";
import {LoginComponent} from "./views/login/login.component";
import {AuthGuard} from "./guards/auth.guard";
import {ItemsComponent} from "./views/items/items.component";
import {ManageItemComponentCandeactivateGuardGuard} from "./guards/manage-item-component-candeactivate-guard.guard";
import {PlaceOrderComponent} from "./views/place-order/place-order.component";

const appRoutes: Routes = [
  {
    path: "",
    component: MainComponent,
    children: [
      {path: "dashboard", component: DashboardComponent},
      {
        path: "manage-customers",
        component: ManageCustomersComponent

      },
      {
        path: "manage-items",
        component: ItemsComponent

      },
      {
        path: "place-order",
        component: PlaceOrderComponent
      },

      {
        path: "main",
        pathMatch : "full",
        redirectTo: "/dashboard"
      }
    ]

  },

 // {path: "login", component: MainComponent},
  ///{path: "", redirectTo: "/main/dashboard"}
  // {path: "", redirectTo: "/main/dashboard"}
]

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
