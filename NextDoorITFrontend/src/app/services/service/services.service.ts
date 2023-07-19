import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Service } from 'src/app/models/service.model';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  url = `${environment.apiUrl}/service`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Service[]> {
    return this.http.get<Service[]>(`${this.url}/all`);
  }

  getAllByCategory(categoryId: string): Observable<Service[]> {
    return this.http.get<Service[]>(`${this.url}/category?categoryId=${categoryId}`);
  }

  get(id: any): Observable<Service> {
    return this.http.get(`${this.url}/serviceDetails?serviceId=${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(`${this.url}/requestService`, data);
  }
}