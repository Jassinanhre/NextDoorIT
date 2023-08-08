import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { GlobalConstants } from 'src/app/global-constants';
import { TrainingService } from 'src/app/services/training/training.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';


@Component({
  selector: 'app-training-enroll',
  templateUrl: './training-enroll.component.html',
  styleUrls: ['./training-enroll.component.scss']
})
export class TrainingEnrollComponent implements OnInit {
  enrollForm: any = FormGroup;
  responseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
    private trainingService: TrainingService,
    private localStorageService: LocalStorageService,
  ) { }

  ngOnInit(): void {
    this.enrollForm = this.formBuilder.group({
      name: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      trainingType: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      objective: [null, [Validators.required]],
    })
  }

  handleSubmit() {
    const formData = this.enrollForm.value;
    this.ngxService.start();
    const data = {
      name: formData.name,
      email: formData.email,
      trainingType: formData.trainingType,
      objective: formData.objective,
      userId: this.localStorageService.getItem('userId'),
    }
    this.trainingService.enroll(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = 'Enrollment successfully done in your selected training program.';
      this.snackbarService.openSnackBar(this.responseMessage, "");
      this.router.navigate(['/training/list']);
      this.enrollForm.reset();
      this.enrollForm.controls['name'].setErrors(null);
      this.enrollForm.controls['email'].setErrors(null);
      this.enrollForm.controls['trainingType'].setErrors(null);
      this.enrollForm.controls['objective'].setErrors(null);
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
