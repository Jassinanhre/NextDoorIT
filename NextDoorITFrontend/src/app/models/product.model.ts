import { ReviewRating } from "./reviewRating.model";

export class Product {
  id?: string;
  productName?: string;
  productDescription?: string;
  price?: string;
  imageId?: string;
  specifications?: string;
  productCategory?: string;
  overallRating?: number;
  productReviewsAndRatings?: any = ReviewRating;

}