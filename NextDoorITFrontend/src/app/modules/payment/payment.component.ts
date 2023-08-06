import { Component, OnInit } from '@angular/core';

import { OrderService } from 'src/app/services/user/order/order.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss'],

})
export class PaymentComponent implements OnInit {
  showReceipt: boolean = false;
  orderInfo: any = {};

  constructor(
    private orderService: OrderService,
    private localStorageService: LocalStorageService,
  ) { }

  ngOnInit(): void {
    this.getOrderInfo()
  }

  getOrderInfo(): void {
    const userId = this.localStorageService.getItem('userId');
    this.orderService.getOrderInfo(userId).subscribe((response: any) => {
      this.orderInfo = response.data;
    }, (error: any) => {
      console.log("error: ", error);
    })
  }

  handlePay() {
    this.showReceipt = !this.showReceipt;
  }

}
