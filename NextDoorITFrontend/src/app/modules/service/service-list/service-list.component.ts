import { Component, OnInit } from '@angular/core';
import { Service } from 'src/app/models/service.model';
import { ServicesService } from 'src/app/services/service/services.service';

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.scss']
})
export class ServiceListComponent implements OnInit {

  services?: Service[] = [];
  currentService?: Service;
  currentIndex = -1;
  title = '';

  constructor(private servicesService: ServicesService) { }

  ngOnInit(): void {
    this.retrieveServices();
  }

  retrieveServices(): void {
    this.servicesService.getAll()
      .subscribe((response: any) => {
        this.services = response.data;
      }, error => {
        console.log(error);
      });
  }

  setActiveService(service: Service, index: number): void {
    this.currentService = service;
    this.currentIndex = index;
  }

  filterByCategory(id: string) {
    if (id) {
      this.servicesService.getAllByCategory(id)
        .subscribe((response: any) => {
          this.services = response.data;
        }, error => {
          console.log(error);
        });
    } else {
      this.servicesService.getAll()
        .subscribe((response: any) => {
          this.services = response.data;
        }, error => {
          console.log(error);
        });
    }
  }
}
