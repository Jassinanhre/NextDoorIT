import { Component, OnInit } from '@angular/core';
import { Training } from 'src/app/models/training.model';
import { TrainingService } from 'src/app/services/training/training.service';

@Component({
  selector: 'app-training-list',
  templateUrl: './training-list.component.html',
  styleUrls: ['./training-list.component.scss']
})
export class TrainingListComponent implements OnInit {

  trainings?: Training[] = [];
  currentService?: Training;
  currentIndex = -1;
  title = '';

  constructor(private trainingService: TrainingService) { }

  ngOnInit(): void {
    this.retrieveServices();
  }

  retrieveServices(): void {
    this.trainingService.getAll()
      .subscribe((response: any) => {
        this.trainings = response.data;
      }, error => {
        console.log(error);
      });
  }

  setActiveService(training: Training, index: number): void {
    this.currentService = training;
    this.currentIndex = index;
  }

  filterByCategory(id: string) {
    if (id) {
      this.trainingService.getAllByCategory(id)
        .subscribe((response: any) => {
          this.trainings = response.data;
        }, error => {
          console.log(error);
        });
    } else {
      this.trainingService.getAll()
        .subscribe((response: any) => {
          this.trainings = response.data;
        }, error => {
          console.log(error);
        });
    }
  }
}
