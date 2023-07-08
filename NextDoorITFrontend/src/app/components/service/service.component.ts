import { Component, OnInit } from '@angular/core';

import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

import { ServiceService } from 'src/app/services/service.service';
import { Service } from 'src/app/models/service.model ';
import { LoginComponent } from '../auth/login/login.component';
import { SignupComponent } from '../auth/signup/signup.component';


@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.scss']
})
export class ServiceComponent implements OnInit {
  services?: Service[] = [
    {
      title: "Our Services",
      description: "We provide you with detailed lists of expenses.",
      image: "https://promo-theme.com/itera/wp-content/uploads/2018/12/side-img3.png"
    },
    {
      title: "Hardware Sales",
      description: "Our website offers a convenient online platform for individuals looking to purchase hardware parts.",
      image: "assets/img/bulb.png"
    },
    {
      title: "Home Services",
      description: "Our skilled team offers on-site hardware services to address any technical issues you may encounter at your home.",
      image: "assets/img/bulb.png"
    },
    {
      title: "Software Development",
      description: "Whether it's web development, software development, or any other development- related inquiries, we are here to assist you.",
      image: "assets/img/link.png"
    },
    {
      title: "Trouble shoot",
      description: "Our team is ready to provide assistance and troubleshooting support for any issues you may encounter.",
      image: "assets/img/link.png"
    },
    {
      title: "App Development",
      description: "Our team is skilled in developing custom applications tailored to your specific requirements.",
      image: "assets/img/bulb.png"
    },
    {
      title: "Trainings",
      description: "Our services extend to providing training and online courses focused on various aspects of development.",
      image: "assets/img/bulb.png"
    }
  ];
  currentService?: Service;
  currentIndex = -1;
  title = '';

  constructor(
    private dialog: MatDialog,
    private serviceService: ServiceService) { }

  ngOnInit(): void {
    this.retrieveServices();
  }

  retrieveServices(): void {
    this.serviceService.getAll().subscribe(data => {
      this.services = data;
      console.log("All services are : ", this.services)
    },
      error => {
        console.log(error);
      })
  }

  refreshList(): void {
    this.retrieveServices();
    this.currentService = undefined;
    this.currentIndex = -1;
  }

  setActiveService(service: Service, index: number): void {
    this.currentService = service;
    this.currentIndex = index;
  }

  removeAllServices(): void {
    this.serviceService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.serviceService.findByTitle(this.title)
      .subscribe(
        data => {
          this.services = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  handleSignupAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(SignupComponent, dialogConfig);
  }

  handleLoginAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(LoginComponent, dialogConfig);
  }

}
