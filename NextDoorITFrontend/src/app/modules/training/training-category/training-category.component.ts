import { Component, EventEmitter, OnInit, Output } from '@angular/core';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { TrainingCategory } from 'src/app/models/trainingCategory.model';
import { TrainingCategoryService } from 'src/app/services/training/training-category.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/global-constants';

@Component({
  selector: 'app-training-category',
  templateUrl: './training-category.component.html',
  styleUrls: ['./training-category.component.scss']
})
export class TrainingCategoryComponent implements OnInit {
  @Output() categoryEvent = new EventEmitter<string>();

  trainingCategory?: TrainingCategory[] = [
    {
      id: "1",
      categoryName: "Computer Accessories",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
    },
    {
      id: "2",
      categoryName: "Printer Supplies",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
    },
    {
      id: "3",
      categoryName: "Networking Equipment",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
    }
  ];
  responseMessage: any;

  constructor(
    private categoryService: TrainingCategoryService,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
  ) { }

  ngOnInit(): void {
    // this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getAll().subscribe((response: any) => {
      this.ngxService.stop();
      this.trainingCategory = response.data;
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
