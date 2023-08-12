import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  currentProduct?: Product;
  currentIndex = -1;
  sortKey: string = 'title';
  sortValue: number = -1;
  title = '';

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.retrieveProducts();
  }

  retrieveProducts(): void {
    this.productService.getAll()
      .subscribe(
        (response: any) => {
          this.products = response.data;
          console.log(response.data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveProducts();
    this.currentProduct = undefined;
    this.currentIndex = -1;
  }

  setActiveProduct(product: Product, index: number): void {
    this.currentProduct = product;
    this.currentIndex = index;
  }

  searchTitle(): void {
    this.productService.findByTitle(this.title)
      .subscribe(
        (response: any) => {
          this.products = response.data;
          console.log(response.data);
        },
        error => {
          console.log(error);
        });
  }

  filterByCategory(id: string) {
    if (id) {
      this.productService.getAllByCategory(id)
        .subscribe((response: any) => {
          this.products = response.data;
        }, error => {
          console.log(error);
        });
    } else {
      this.productService.getAll()
        .subscribe((response: any) => {
          this.products = response.data;
        }, error => {
          console.log(error);
        });
    }
  }

  filterByText(search: string) {
    this.title = search;
    // this.searchTitle();
    // this.products = search ? this.allProducts?.filter(product => { return product.title?.includes(search) }) : this.allProducts;
  }
}
