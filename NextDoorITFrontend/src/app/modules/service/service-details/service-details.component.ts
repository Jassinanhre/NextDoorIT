import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Service } from 'src/app/models/service.model';
import { ServicesService } from 'src/app/services/service/services.service';

@Component({
  selector: 'app-service-details',
  templateUrl: './service-details.component.html',
  styleUrls: ['./service-details.component.scss'],
})
export class ServiceDetailsComponent implements OnInit {
  currentService: Service = {
    id: "3",
    serviceName: "Software Development",
    description: "Whether it's web development, software development, or any other development-related inquiries, we are here to assist you.",
    image: "assets/img/bulb.png",
    category: ""
  };
  message = '';

  constructor(
    private servicesService: ServicesService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.getService(this.route.snapshot.params.id);
  }

  getService(id: string): void {
    this.servicesService.get(id)
      .subscribe(
        data => {
          this.currentService = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}