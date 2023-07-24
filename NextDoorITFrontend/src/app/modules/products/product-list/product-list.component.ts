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
    },
    {
      id: "2",
      title: "Printer",
      description: "Laser printers are known for their fast print speeds and high-quality text prints. These printers use a laser to fuse toner onto the paper, creating sharp, crisp text ideal for documents and business materials.",
      image: "assets/img/printer.png",
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
    },
    {
      id: "3",
      title: "Category 5 Cable",
      description: "Category 5 cable (Cat 5) is a twisted pair cable for computer networks. Since 2001, the variant commonly in use is the Category 5e specification (Cat 5e).",
      image: "assets/img/cat5cable.jpg",
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
    },
    {
      id: "4",
      title: "Home Theater",
      description: "An audio/video entertainment center that has a large-screen TV and high fidelity audio system with at least three speakers in the front (left, right and center) and left and right speakers in the rear.",
      image: "assets/img/theater.jpg",
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
    },
    {
      id: "5",
      title: "Laptop",
      description: "A laptop is a personal computer that can be easily moved and used in a variety of locations. Most laptops are designed to have all of the functionality of a desktop computer, which means they can generally run the same software and open the same types of files.",
      image: "assets/img/laptop.webp",
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
    },
    {
      id: "6",
      title: "HDMI Cable",
      description: "HDMI stands for High Definition Multimedia Interface and is the most frequently used HD signal for transferring both high definition audio and video over a single cable.",
      image: "assets/img/hdmi.jpg",
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
    },
  ];
  products?: Product[] = [
    {
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
    },
    {
      id: "2",
      title: "Printer",
      description: "Laser printers are known for their fast print speeds and high-quality text prints. These printers use a laser to fuse toner onto the paper, creating sharp, crisp text ideal for documents and business materials.",
      image: "assets/img/printer.png",
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
    },
    {
      id: "3",
      title: "Category 5 Cable",
      description: "Category 5 cable (Cat 5) is a twisted pair cable for computer networks. Since 2001, the variant commonly in use is the Category 5e specification (Cat 5e).",
      image: "assets/img/cat5cable.jpg",
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
    },
    {
      id: "4",
      title: "Home Theater",
      description: "An audio/video entertainment center that has a large-screen TV and high fidelity audio system with at least three speakers in the front (left, right and center) and left and right speakers in the rear.",
      image: "assets/img/theater.jpg",
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
    },
    {
      id: "5",
      title: "Laptop",
      description: "A laptop is a personal computer that can be easily moved and used in a variety of locations. Most laptops are designed to have all of the functionality of a desktop computer, which means they can generally run the same software and open the same types of files.",
      image: "assets/img/laptop.webp",
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
    },
    {
      id: "6",
      title: "HDMI Cable",
      description: "HDMI stands for High Definition Multimedia Interface and is the most frequently used HD signal for transferring both high definition audio and video over a single cable.",
      image: "assets/img/hdmi.jpg",
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
    },
  ];
  currentProduct?: Product;
  currentIndex = -1;
  sortKey: string = 'title';
  sortValue: number = -1;
  title = '';

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    // this.retrieveProducts();
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
    this.currentProduct = undefined;
    this.currentIndex = -1;
  }

  setActiveProduct(product: Product, index: number): void {
    this.currentProduct = product;
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
