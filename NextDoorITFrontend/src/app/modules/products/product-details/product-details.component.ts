import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { ProductService } from 'src/app/services/product/product.service';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {
  currentProduct: Product = {
    id: "1",
    title: "Computer Accessories",
    description: "Equipment that supports other equipment. For example, smartphone accessories include phone cases, chargers and cables. Examples of computer accessories are laptop bags, cables, screen cleaners and USB drives, although the latter may also be considered a peripheral device.",
    image: "assets/img/computer_acc.jpg",
    price: "1254",
    reviewRatings: {
      rating: "4.8",
      reviews: [{
        title: "Terrific purchase",
        summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
      }, {
        title: "Terrific purchase",
        summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
      }]
    },
    productCategory: ""
  };
  message = '';
  isLogin: boolean = false;
  constructor(
    private productService: ProductService,
    private localStorageService: LocalStorageService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.isLogin = this.localStorageService.getItem('isLoggedIn');
    this.getProduct(this.route.snapshot.params.id);
  }

  getProduct(id: string): void {
    this.productService.get(id)
      .subscribe(
        data => {
          this.currentProduct = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updateService(): void {
    this.productService.update(this.currentProduct.id, this.currentProduct)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message;
        },
        error => {
          console.log(error);
        });
  }

  deleteService(): void {
    this.productService.delete(this.currentProduct.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/service']);
        },
        error => {
          console.log(error);
        });
  }

}