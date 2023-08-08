import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Cart } from 'src/app/models/cart.model';

import { SnackbarService } from 'src/app/services/snackbar.service';
import { CartService } from 'src/app/services/user/cart/cart.service';
import { ProductService } from 'src/app/services/product/product.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  isCartEmpty: boolean = true;
  cartList: Cart = {};

  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private cartService: CartService,
    private productService: ProductService,
    private localStorageService: LocalStorageService,
  ) { }

  ngOnInit(): void {
    this.fetchCartItems();
  }

  fetchCartItems(): void {
    const userId: string = this.localStorageService.getItem('userId');
    this.cartService.getAll(userId)
      .subscribe(
        (response: any) => {
          this.cartList = response.data;
          this.isCartEmpty = this.cartList.productDetails?.length ? false : true;
        },
        (error: any) => {
          console.log(error);
        });
  }

  onQuantityChange(obj: any): void {
    const data = {
      productId: obj.productId,
      userId: this.localStorageService.getItem('userId'),
      quantity: Number(obj.quantity),
    }
    this.productService.addToCart(data).subscribe((response: any) => {
      this.snackbarService.openSnackBar('Product qunatity updated successfully.', "");
      this.fetchCartItems();
    }, (error) => {
      console.log("error :-", error);
    });

  }

  removeItem(productId: any): void {
    const userId: string = this.localStorageService.getItem('userId');
    this.cartService.removeOne(userId, productId)
      .subscribe(
        (response: any) => {
          console.log(response.data);
          this.snackbarService.openSnackBar("Item removed successfully from cart.", "");
          this.fetchCartItems();
        },
        error => {
          console.log(error);
        });
  }

  checkout(): void {
    const userId: string = this.localStorageService.getItem('userId');
    this.router.navigate(['/checkout']);
  }

}
