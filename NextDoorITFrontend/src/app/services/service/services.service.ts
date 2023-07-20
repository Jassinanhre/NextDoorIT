import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Service } from 'src/app/models/service.model';
import { LocalStorageService } from '../local-storage.service';

import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})

export class ServicesService {
  url = `${environment.apiUrl}/service`;

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
    return this.http.get<Service[]>(`${this.url}/all`, this.requestOptions);
  }

  getAllByCategory(categoryId: string) {
    return this.http.get<Service[]>(`${this.url}/category?categoryId=${categoryId}`, this.requestOptions);
  }

  get(id: any) {
    return this.http.get(`${this.url}/serviceDetails?serviceId=${id}`, this.requestOptions);
  }

  create(data: any) {
    return this.http.post(`${this.url}/requestService`, data, this.requestOptions);
  }
}