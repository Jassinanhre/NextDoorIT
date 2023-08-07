import { TrainingCategory } from "./trainingCategory.model";

export class Training {
  id?: string;
  name?: string;
  description?: string;
  objective?: string;
  prerequisites?: string;
  syllabus?: string;

  price?: string;
  imageId?: string;
  duration?: string;
  reviewRatings?: {
    rating?: string,
    reviews?: any
  };
  trainingCategory?: TrainingCategory;
}