import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { GlobalConstants } from 'src/app/services/global-constants';
import { ServicesService } from 'src/app/services/services.service';
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
    console.log('Here service request form values are :', formData);
    this.ngxService.start();
    const data = {
      name: formData.name,
      email: formData.email,
      category: formData.category,
      query: formData.query,
    }

    this.servicesService.create(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.responseMessage = response?.message;
      this.snackbarService.openSnackBar(this.responseMessage, "");
      this.router.navigate(['/service/list']);
    }, (error) => {
      this.ngxService.stop();
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }
}
