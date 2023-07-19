import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { GlobalConstants } from 'src/app/global-constants';
import { TrainingService } from 'src/app/services/training/training.service';
import { SnackbarService } from 'src/app/services/snackbar.service';


@Component({
  selector: 'app-training-request',
  templateUrl: './training-request.component.html',
  styleUrls: ['./training-request.component.scss']
})
export class TrainingRequestComponent implements OnInit {
  requestForm: any = FormGroup;
  responseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
    private trainingService: TrainingService,
  ) { }

  ngOnInit(): void {
    this.requestForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      category: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      query: [null, [Validators.required]],
    })
  }

  handleSubmit() {
    const formData = this.requestForm.value;
    console.log('Here Training request form values are :', formData);
    this.ngxService.start();
    const data = {
      name: formData.name,
      email: formData.email,
      category: formData.category,
      query: formData.query,
    }

    this.trainingService.create(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = response?.message;
      this.snackbarService.openSnackBar(this.responseMessage, "");
      this.router.navigate(['/training/list']);
    }, (error) => {
      this.ngxService.stop();
      if (error.error?.error) {
        this.responseMessage = error.error?.error
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }
}
