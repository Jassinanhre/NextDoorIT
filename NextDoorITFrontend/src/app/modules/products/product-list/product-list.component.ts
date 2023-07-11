import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  allProducts?: Product[] = [
    {
      id: "1",
      title: "Hardware Sales",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "2",
      title: "Home Services",
      description: "Our skilled team offers on-site hardware services to address any technical issues you may encounter at your home.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "3",
      title: "Software Development",
      description: "Whether it's web development, software development, or any other development-related inquiries, we are here to assist you.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "4",
      title: "Trouble shoot",
      description: "Our team is ready to provide assistance and troubleshooting support for any issues you may encounter.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "5",
      title: "App Development",
      description: "Our team is skilled in developing custom applications tailored to your specific requirements.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "6",
      title: "Trainings",
      description: "Our services extend to providing training and online courses focused on various aspects of development.",
      image: "assets/img/bulb.png",
      productCategory: ""
    }
  ];
  products?: Product[] = [
    {
      id: "1",
      title: "Hardware Sales",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "2",
      title: "Home Services",
      description: "Our skilled team offers on-site hardware services to address any technical issues you may encounter at your home.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "3",
      title: "Software Development",
      description: "Whether it's web development, software development, or any other development-related inquiries, we are here to assist you.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "4",
      title: "Trouble shoot",
      description: "Our team is ready to provide assistance and troubleshooting support for any issues you may encounter.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "5",
      title: "App Development",
      description: "Our team is skilled in developing custom applications tailored to your specific requirements.",
      image: "assets/img/bulb.png",
      productCategory: ""
    },
    {
      id: "6",
      title: "Trainings",
      description: "Our services extend to providing training and online courses focused on various aspects of development.",
      image: "assets/img/bulb.png",
      productCategory: ""
    }
  ];
  currentService?: Product;
  currentIndex = -1;
  title = '';

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.retrieveProducts();
  }

  retrieveProducts(): void {
    this.productService.getAll()
      .subscribe(
        data => {
          this.products = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveProducts();
    this.currentService = undefined;
    this.currentIndex = -1;
  }

  setActiveProduct(product: Product, index: number): void {
    this.currentService = product;
    this.currentIndex = index;
  }

  removeAllProducts(): void {
    this.productService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.productService.findByTitle(this.title)
      .subscribe(
        data => {
          this.products = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  filterByCategory(id: string) {
    // this.retrieveProducts();
    this.products = id ? this.allProducts?.filter(product => { return product.id === id }) : this.allProducts;
  }

  filterByText(search: string) {
    this.title = search;
    // this.searchTitle();
    this.products = search ? this.allProducts?.filter(product => { return product.title?.includes(search) }) : this.allProducts;
  }
}
