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
  currentService: Service = {};
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
        (response: any) => {
          this.currentService = response.data;
        },
        error => {
          console.log(error);
        });
  }
}