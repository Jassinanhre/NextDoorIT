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
    id: "3",
    trainingName: "Software Development",
    description: "Whether it's web development, software development, or any other development-related inquiries, we are here to assist you.",
    image: "assets/img/bulb.png",
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