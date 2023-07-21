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

  getRating(rating: number): number {
    return 5 - rating;
  }
}