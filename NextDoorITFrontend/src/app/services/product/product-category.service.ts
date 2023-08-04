import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ProductCategory } from 'src/app/models/productCategory.model';
import { LocalStorageService } from '../local-storage.service';

import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class ProductCategoryService {
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
    return this.http.get<ProductCategory[]>(`${this.url}/allCategory`, this.requestOptions);
  }
}
