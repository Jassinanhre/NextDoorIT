import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { GlobalConstants } from 'src/app/global-constants';
import { ServicesService } from 'src/app/services/service/services.service';
import { SnackbarService } from 'src/app/services/snackbar.service';


@Component({
  selector: 'app-service-request',
  templateUrl: './service-request.component.html',
  styleUrls: ['./service-request.component.scss']
})
export class ServiceRequestComponent implements OnInit {
  requestForm: any = FormGroup;
  responseMessage: any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private snackbarService: SnackbarService,
    private ngxService: NgxUiLoaderService,
    private servicesService: ServicesService,
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
    this.ngxService.start();
    const data = {
      serviceName: formData.name,
      userEmail: formData.email,
      trainingType: formData.category,
      userQuery: formData.query,
    }

    this.servicesService.create(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = response?.data;
      this.snackbarService.openSnackBar(this.responseMessage, "");
      this.router.navigate(['/service/list']);
      this.requestForm.reset();
      this.requestForm.controls['name'].setErrors(null);
      this.requestForm.controls['email'].setErrors(null);
      this.requestForm.controls['category'].setErrors(null);
      this.requestForm.controls['query'].setErrors(null);
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
