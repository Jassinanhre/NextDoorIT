import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { Service } from 'src/app/models/service.model';
import { ServicesService } from 'src/app/services/service/services.service';
import { FeedbackService } from 'src/app/services/user/feedback.service';
import { SnackbarService } from 'src/app/services/snackbar.service';

import { GlobalConstants } from 'src/app/global-constants';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-service-details',
  templateUrl: './service-details.component.html',
  styleUrls: ['./service-details.component.scss'],
})
export class ServiceDetailsComponent implements OnInit {
  reviewRatingForm: any = FormGroup;
  currentService: Service = {};
  message = '';
  responseMessage = '';
  newRating: number = 0;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
    private servicesService: ServicesService,
    private feedbackService: FeedbackService,
    private localStorageService: LocalStorageService
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.reviewRatingForm = this.formBuilder.group({
      review: [null, [Validators.required]],
    })
    this.getService();
  }

  getService(): void {
    const id: string = this.route.snapshot.params.id;
    this.servicesService.get(id)
      .subscribe(
        (response: any) => {
          this.currentService = response.data;
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
      serviceId: this.route.snapshot.params.id,
      username: this.localStorageService.getItem('userName'),
      rating: this.newRating,
      summery: formData.review,
    }

    this.feedbackService.createServiceRating(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = response?.data;
      // this.snackbarService.openSnackBar(this.responseMessage, "");
      this.reviewRatingForm.reset();
      this.reviewRatingForm.controls['review'].setErrors(null);
      this.getService();
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