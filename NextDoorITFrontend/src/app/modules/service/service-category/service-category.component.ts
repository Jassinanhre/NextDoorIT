import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { ServiceCategory } from 'src/app/models/serviceCategory.model';
import { ServiceCategoryService } from 'src/app/services/service/service-category.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/global-constants';

@Component({
  selector: 'app-service-category',
  templateUrl: './service-category.component.html',
  styleUrls: ['./service-category.component.scss']
})
export class ServiceCategoryComponent implements OnInit {
  @Output() categoryEvent = new EventEmitter<string>();

  serviceCategory?: ServiceCategory[] = [];
  responseMessage: any;

  constructor(
    private categoryService: ServiceCategoryService,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
  ) { }

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getAll().subscribe((response: any) => {
      this.ngxService.stop();
      this.serviceCategory = response.data;
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


  onCategoryChange(item: any): void {
    this.categoryEvent.emit(item?.id);
  }

}
