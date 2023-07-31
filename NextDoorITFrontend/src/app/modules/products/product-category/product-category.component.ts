import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { ProductCategory } from 'src/app/models/productCategory.model';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { ProductCategoryService } from 'src/app/services/product/product-category.service';

import { GlobalConstants } from 'src/app/global-constants';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.scss']
})
export class ProductCategoryComponent implements OnInit {
  @Output() categoryEvent = new EventEmitter<string>();

  productCategory?: ProductCategory[] = [
    {
      id: "1",
      name: "Computer Accessories",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "2",
      name: "Printer Supplies",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "3",
      name: "Networking Equipment",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    }
  ];
  responseMessage: any;

  constructor(
    private categoryService: ProductCategoryService,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
  ) { }

  ngOnInit(): void {
    // this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getAll().subscribe((response: any) => {
      this.ngxService.stop();
      this.productCategory = response.data;
    }, (error) => {
      this.ngxService.stop();
      if (error.error?.error) {
        this.responseMessage = error.error?.error
        this.snackbarService.openSnackBar(this.responseMessage, "");
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  onCategoryChange(item: any) {
    this.categoryEvent.emit(item?.id);
  }

}
