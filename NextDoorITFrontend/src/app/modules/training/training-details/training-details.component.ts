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
  currentTraining: Training = {
    id: "1",
    trainingName: "Programming Language",
    description: "A programming language is a language used to write computer programs, which instruct a computer to perform some kind of computation, and/or organize the flow of control between external devices (such as a printer, a robot, or any peripheral).",
    image: "assets/img/programming-language.png",
    price: "1254",
    reviewRatings: {
      rating: "4.8",
      reviews: [{
        trainingName: "Terrific purchase",
        summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
      }, {
        trainingName: "Terrific purchase",
        summary: "Its value for money for the price i paid for it. Suits my needs. Print quality is good for both monochrome and colour printing on A4 GSM 70 paper. Haven't tried printing pics yet so can't say how it'd be. Setup was easy. Just went through the manual and also the video."
      }]
    },
    category: ""
  };
  message = '';

  constructor(
    private trainingService: TrainingService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.getTraining(this.route.snapshot.params.id);
  }

  getTraining(id: string): void {
    this.trainingService.get(id)
      .subscribe(
        data => {
          this.currentTraining = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
}