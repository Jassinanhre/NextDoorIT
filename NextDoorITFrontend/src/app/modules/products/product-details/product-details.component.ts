import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from 'src/app/global-constants';

import { Product } from 'src/app/models/product.model';

import { LocalStorageService } from 'src/app/services/local-storage.service';
import { ProductService } from 'src/app/services/product/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { FeedbackService } from 'src/app/services/user/feedback.service';


@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {
  reviewRatingForm: any = FormGroup;
  currentProduct: Product = {};
  // {
  //   id: "1",
  //   title: "Computer Accessories",
  //   description: "Equipment that supports other equipment. For example, smartphone accessories include phone cases, chargers and cables. Examples of computer accessories are laptop bags, cables, screen cleaners and USB drives, although the latter may also be considered a peripheral device.",
  //   image: "assets/img/computer_acc.jpg",
  //   price: "1254",
  //   reviewRatings: {
  //     rating: "4.8",
  //     reviews: [{
  //       title: "Terrific purchase",
  //       summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
  //     }, {
  //       title: "Terrific purchase",
  //       summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
  //     }]
  //   },
  //   productCategory: ""
  // };
  message = '';
  responseMessage = '';
  isLogin: boolean = false;
  newRating: number = 0;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
    private productService: ProductService,
    private feedbackService: FeedbackService,
    private localStorageService: LocalStorageService,
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.isLogin = this.localStorageService.getItem('isLoggedIn');
    this.reviewRatingForm = this.formBuilder.group({
      review: [null, [Validators.required]],
    });
    this.getProduct();
  }

  getProduct(): void {
    const id: string = this.route.snapshot.params.id;
    this.productService.get(id)
      .subscribe(
        (response: any) => {
          this.currentProduct = response.data;
          console.log(response.data);
        },
        error => {
          console.log(error);
        });
  }

  onRate(rating: number) {
    this.newRating = rating;
  }

  handleSubmit(): void {
    const formData = this.reviewRatingForm.value;
    this.ngxService.start();
    const data = {
      productId: this.route.snapshot.params.id,
      userId: 1,
      rating: this.newRating,
      summery: formData.review,
    }

    this.feedbackService.createProductRating(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = response?.data;
      // this.snackbarService.openSnackBar(this.responseMessage, "");
      this.reviewRatingForm.reset();
      this.reviewRatingForm.controls['review'].setErrors(null);
      this.getProduct();
    }, (error) => {
      this.ngxService.stop();
      if (error.error?.error) {
        this.responseMessage = error.error?.error
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    });
  }
}