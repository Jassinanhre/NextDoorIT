import { ReviewRating } from "./reviewRating.model";
import { ServiceCategory } from "./serviceCategory.model";

export class Service {
  id?: string;
  serviceName?: string;
  description?: string;
  category?: any = ServiceCategory;
  userOverallRating?: number;
  reviewRatings?: any = ReviewRating;
  image?: string;
  duration?: string;
  price?: string;
}