import { Component, OnInit } from '@angular/core';
import { ServiceCategory } from 'src/app/models/serviceCategory.model';

@Component({
  selector: 'app-service-category',
  templateUrl: './service-category.component.html',
  styleUrls: ['./service-category.component.scss']
})
export class ServiceCategoryComponent implements OnInit {
  serviceCategory?: ServiceCategory[] = [
    {
      id: "1",
      name: "Computer Repair",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "2",
      name: "LAN installation",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "3",
      name: "Software installation",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "4",
      name: "Website",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "5",
      name: "App development",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
