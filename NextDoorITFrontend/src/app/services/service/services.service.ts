import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Service } from '../../models/service.model';
import { ServiceCategory } from 'src/app/models/serviceCategory.model';

const baseUrl = 'http://localhost:8080/service';
const categoryBaseUrl = 'http://localhost:8080/categories';


@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Service[]> {
    return this.http.get<Service[]>(baseUrl);
  }

  getAllCategories(): Observable<ServiceCategory[]> {
    return this.http.get<ServiceCategory[]>(categoryBaseUrl + '/allCategories');
  }

  get(id: any): Observable<Service> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title: any): Observable<Service[]> {
    return this.http.get<Service[]>(`${baseUrl}?title=${title}`);
  }
}