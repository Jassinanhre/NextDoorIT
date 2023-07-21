import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { Service } from 'src/app/models/service.model';
import { ServicesService } from 'src/app/services/service/services.service';
import { FeedbackService } from 'src/app/services/user/feedback.service';
import { SnackbarService } from 'src/app/services/snackbar.service';

import { GlobalConstants } from 'src/app/global-constants';

@Component({
  selector: 'app-service-details',
  templateUrl: './service-details.component.html',
  styleUrls: ['./service-details.component.scss'],
})
export class ServiceDetailsComponent implements OnInit {
  reviewRatingForm: any = FormGroup;
  currentService: Service = {
    "id": '4',
    "serviceName": "Angular",
    "description": "Creating angular web applications",
    "category": null,
    "image": "bulb.png",
    "price": "1200",
    "duration": "3",
    "userOverallRating": 3,
    "reviewRatings": [{
      "username": "Dikshit",
      "rating": 2,
      "summary": "Its a good experience using these services.Very satisfied with the purchase. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."
    }, {
      "title": "Rohit",
      "rating": 4,
      "summary": "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
    }]
  };
  message = '';
  responseMessage = '';
  newRating: number = 0;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
    private servicesService: ServicesService,
    private feedbackService: FeedbackService
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.reviewRatingForm = this.formBuilder.group({
      review: [null, [Validators.required]],
    })
    this.getService(this.route.snapshot.params.id);
  }

  getService(id: string): void {
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
      userId: 1,
      rating: this.newRating,
      review: formData.review,
    }
    console.log("data  :-->", data);

    this.feedbackService.create(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = response?.data;
      this.snackbarService.openSnackBar(this.responseMessage, "");
      this.reviewRatingForm.reset();
      this.reviewRatingForm.controls['review'].setErrors(null);
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