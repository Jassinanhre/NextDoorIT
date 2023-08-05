import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Product } from 'src/app/models/product.model';
import { LocalStorageService } from '../local-storage.service';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  url = `${environment.apiUrl}/product`;

  constructor(
    private http: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  jwtToken: string = this.localStorageService.getItem('JWT')
  requestOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.jwtToken}`
    })
  }

  getAll() {
    return this.http.get<Product[]>(`${this.url}/all`, this.requestOptions)
  }

  getAllByCategory(categoryId: string) {
    return this.http.get<Product[]>(`${this.url}/category?categoryId=${categoryId}`, this.requestOptions);
  }

  get(id: any) {
    return this.http.get(`${this.url}/productDetails?productId=${id}`, this.requestOptions);
  }

  addToCart(data: any) {
    return this.http.post(`${environment.apiUrl}/cart/addProduct`, data, this.requestOptions);
  }

  findByTitle(title: any) {
    return this.http.get<Product[]>(`${this.url}?title=${title}`, this.requestOptions);
  }
}