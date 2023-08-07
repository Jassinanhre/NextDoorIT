import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Training } from 'src/app/models/training.model';
import { TrainingService } from 'src/app/services/training/training.service';

@Component({
  selector: 'app-training-details',
  templateUrl: './training-details.component.html',
  styleUrls: ['./training-details.component.scss'],
})
export class TrainingDetailsComponent implements OnInit {
  currentTraining: Training = {}
  trainingReviews: any = {}
  // {
  //   id: "2",
  //   name: "Network Administration",
  //   description: "Laser printers are known for their fast print speeds and high-quality text prints. These printers use a laser to fuse toner onto the paper, creating sharp, crisp text ideal for documents and business materials.",
  //   image: "assets/img/network-admin.png",
  //   price: "1254",
  //   reviewRatings: {
  //     rating: "4.8",
  //     reviews: [{
  //       name: "Terrific purchase",
  //       summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
  //     }, {
  //       name: "Terrific purchase",
  //       summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
  //     }]
  //   },
  //   category: ""
  // };
  message = '';

  constructor(
    private trainingService: TrainingService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.getTraining();
    this.getTrainingReview();
  }

  getTraining(): void {
    const trainingId: string = this.route.snapshot.params.id;
    this.trainingService.get(trainingId)
      .subscribe(
        (response: any) => {
          this.currentTraining = response.data;
          console.log(response.data);
        },
        error => {
          console.log(error);
        });
  }

  getTrainingReview(): void {
    const trainingId: string = this.route.snapshot.params.id;
    this.trainingService.getReview(trainingId)
      .subscribe(
        (response: any) => {
          this.trainingReviews = response.data;
          console.log(response.data);
        },
        error => {
          console.log(error);
        });
  }

}