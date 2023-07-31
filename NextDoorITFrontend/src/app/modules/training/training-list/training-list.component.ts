import { Component, OnInit } from '@angular/core';
import { Training } from 'src/app/models/training.model';
import { TrainingService } from 'src/app/services/training/training.service';

@Component({
  selector: 'app-training-list',
  templateUrl: './training-list.component.html',
  styleUrls: ['./training-list.component.scss']
})
export class TrainingListComponent implements OnInit {

  trainings?: Training[] = [
    {
      id: "1",
      trainingName: "Softwares",
      description: "Equipment that supports other equipment. For example, smartphone accessories include phone cases, chargers and cables. Examples of computer accessories are laptop bags, cables, screen cleaners and USB drives, although the latter may also be considered a peripheral device.",
      image: "assets/img/software.png",
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
    },
    {
      id: "2",
      trainingName: "Network Administration",
      description: "Laser printers are known for their fast print speeds and high-quality text prints. These printers use a laser to fuse toner onto the paper, creating sharp, crisp text ideal for documents and business materials.",
      image: "assets/img/network-admin.png",
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
    },
    {
      id: "3",
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
    },
    {
      id: "4",
      trainingName: "Database",
      description: "An audio/video entertainment center that has a large-screen TV and high fidelity audio system with at least three speakers in the front (left, right and center) and left and right speakers in the rear.",
      image: "assets/img/database.png",
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
    },
    {
      id: "5",
      trainingName: "Frontend",
      description: "A laptop is a personal computer that can be easily moved and used in a variety of locations. Most laptops are designed to have all of the functionality of a desktop computer, which means they can generally run the same software and open the same types of files.",
      image: "assets/img/frontend.png",
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
    },
    {
      id: "6",
      trainingName: "Web Developer",
      description: "HDMI stands for High Definition Multimedia Interface and is the most frequently used HD signal for transferring both high definition audio and video over a single cable.",
      image: "assets/img/web.png",
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
    },
  ];

  currentService?: Training;
  currentIndex = -1;
  title = '';

  constructor(private trainingService: TrainingService) { }

  ngOnInit(): void {
    // this.retrieveServices();
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
