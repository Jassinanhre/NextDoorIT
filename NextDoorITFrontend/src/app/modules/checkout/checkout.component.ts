import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { OrderService } from 'src/app/services/user/order/order.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { CartService } from 'src/app/services/user/cart/cart.service';
import { GlobalConstants } from 'src/app/global-constants';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  cartItem: any = {};
  shippingForm: any = FormGroup;
  responseMessage: any;

  constructor(
    private router: Router,
    private cartService: CartService,
    private orderService: OrderService,
    private formBuilder: FormBuilder,
    private localStorageService: LocalStorageService,
  ) { }

  ngOnInit(): void {
    this.fetchCartItems();
    this.shippingForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      address: [null, [Validators.required]],
      contactInfo: [null, [Validators.required, Validators.pattern(GlobalConstants.contactNumberRegex)]],
    })
  }

  fetchCartItems(): void {
    const userId: string = this.localStorageService.getItem('userId');
    this.cartService.getAll(userId)
      .subscribe(
        (response: any) => {
          this.cartItem = response.data;
        },
        (error: any) => {
          console.log(error);
        });
  }


  handlePlaceOrder(): void {
    const formData = this.shippingForm.value;
    const payload: any = {
      userId: this.localStorageService.getItem('userId'),
      "name": formData.name,
      "address": formData.address,
      "contactInfo": formData.contactInfo
    }
    this.orderService.placeOrder(payload).subscribe((response: any) => {
      this.router.navigate(['/payment']);
    }, (error: any) => {
      console.log("error: ", error);
    })
  }

}
