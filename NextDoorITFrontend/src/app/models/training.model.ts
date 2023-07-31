export class Training {
  id?: string;
  trainingName?: string;
  description?: string;
  price?: string;
  image?: string;
  reviewRatings?: {
    rating?: string,
    reviews?: any
  };
  category?: string;
}