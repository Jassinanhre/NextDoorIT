export class Order {
  id?: string;
  title?: string;
  description?: string;
  price?: string;
  image?: string;
  productCategory?: string;
  reviewRatings?: {
    rating?: string,
    reviews?: any
  };
}