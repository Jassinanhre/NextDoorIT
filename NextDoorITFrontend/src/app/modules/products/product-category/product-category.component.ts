import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ProductCategory } from 'src/app/models/productCategory.model';

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

  constructor() { }

  ngOnInit(): void {
  }

  onCategoryChange(item: any) {
    this.categoryEvent.emit(item?.id);
  }

}
