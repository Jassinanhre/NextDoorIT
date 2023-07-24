import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss'],

})
export class PaymentComponent implements OnInit {
  showReceipt: boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

  handlePay() {
    this.showReceipt = !this.showReceipt;
  }

}
