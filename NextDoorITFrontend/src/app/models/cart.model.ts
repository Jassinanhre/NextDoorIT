import { Product } from "./product.model";

export class Cart {
  id?: string;
  orderTotalPrice?: string;
  productDetails?: [{ product: Product, quantity: number }]
}