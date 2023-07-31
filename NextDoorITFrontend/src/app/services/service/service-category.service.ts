import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ServiceCategory } from 'src/app/models/serviceCategory.model';
import { LocalStorageService } from '../local-storage.service';

import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class ServiceCategoryService {
  url = `${environment.apiUrl}/categories`;

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
    return this.http.get<ServiceCategory[]>(`${this.url}/allCategories`, this.requestOptions);
  }
}
