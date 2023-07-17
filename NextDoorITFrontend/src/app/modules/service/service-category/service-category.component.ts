import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { ServiceCategory } from 'src/app/models/serviceCategory.model';
import { ServicesService } from 'src/app/services/service/services.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/services/global-constants';

@Component({
  selector: 'app-service-category',
  templateUrl: './service-category.component.html',
  styleUrls: ['./service-category.component.scss']
})
export class ServiceCategoryComponent implements OnInit {
  @Output() categoryEvent = new EventEmitter<string>();

  serviceCategory?: ServiceCategory[] = [
    {
      id: "1",
      categoryName: "Computer Repair",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "2",
      categoryName: "LAN installation",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "3",
      categoryName: "Software installation",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "4",
      categoryName: "Website",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    },
    {
      id: "5",
      categoryName: "App development",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png",
    }
  ];
  responseMessage: any;

  constructor(
    private categoryService: ServicesService,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
  ) { }

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getAllCategories().subscribe((response: any) => {
      this.ngxService.stop();
      this.serviceCategory = response.data;
    }, (error) => {
      this.ngxService.stop();
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
        this.snackbarService.openSnackBar(this.responseMessage, "");
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }


  onCategoryChange(item: any): void {
    this.categoryEvent.emit(item?.id);
  }

}
