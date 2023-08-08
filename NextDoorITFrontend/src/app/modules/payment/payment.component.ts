import { Component, OnInit } from '@angular/core';

import { OrderService } from 'src/app/services/user/order/order.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss'],

})
export class PaymentComponent implements OnInit {
  showReceipt: boolean = false;
  orderInfo: any = {};
  paymentForm: any = FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private orderService: OrderService,
    private localStorageService: LocalStorageService,
  ) { }

  ngOnInit(): void {
    this.getOrderInfo()
    this.paymentForm = this.formBuilder.group({
      method: [null, [Validators.required]],
      cardNumber: [null, [Validators.required]],
      expiry: [null, [Validators.required]],
      cvv: [null, [Validators.required]],
    })
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
    const formData = this.paymentForm.value;
    const payload: any = {
      userId: this.localStorageService.getItem('userId'),
      "paymentMethod": formData.method,
      "cardNumber": formData.cardNumber,
      "expiry": formData.expiry,
      "cvv": formData.cvv
    }
    this.orderService.makePayment(payload).subscribe((response: any) => {
      this.showReceipt = !this.showReceipt;
    }, (error: any) => {
      console.log("error: ", error);
    });
  }
}
